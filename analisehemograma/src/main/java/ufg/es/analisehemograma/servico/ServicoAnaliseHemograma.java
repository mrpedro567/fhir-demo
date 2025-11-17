package ufg.es.analisehemograma.servico;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ufg.es.analisehemograma.model.Hemograma.ModelHemograma;
import ufg.es.analisehemograma.model.Paciente.ModelPaciente;
import ufg.es.analisehemograma.repository.ModelPacienteRepositorio;
import ufg.es.analisehemograma.model.PontoDeAtencao.DTOPontoDeAtencao;
import ufg.es.analisehemograma.model.PontoDeAtencao.ModelPontoDeAtencao;
import ufg.es.analisehemograma.repository.ModelPontoDeAtencaoRepositorio;

@Service
public class ServicoAnaliseHemograma implements IServicoAnaliseHemograma {

    private final ModelPacienteRepositorio pacienteRepositorio;
    private final ModelPontoDeAtencaoRepositorio pontoDeAtencaoRepositorio;

    private int faixaDeHemoglobina = 0;
    private int faixaDeHematocrito = 0;
    private int faixaDeVcm = 0;

    public ServicoAnaliseHemograma(ModelPacienteRepositorio pacienteRepositorio,
            ModelPontoDeAtencaoRepositorio pontoDeAtencaoRepositorio) {
        this.pacienteRepositorio = pacienteRepositorio;
        this.pontoDeAtencaoRepositorio = pontoDeAtencaoRepositorio;
    }

    // analiseIndividualHemogramaTodosUltimosPontosDeAtencao, método que retorna
    // todos os pontos de atenção mais recentes para cada paciente.
    // Retorna: List<ModelPontoDeAtencao> - lista de pontos de atenção mais
    // recentes.
    public List<DTOPontoDeAtencao> analiseIndividualHemogramaTodosUltimosPontosDeAtencao() {
        ArrayList<DTOPontoDeAtencao> listaDTOPontoDeAtencao = new ArrayList<DTOPontoDeAtencao>();
        ArrayList<ModelPontoDeAtencao> listaPontoDeAtencao = pontoDeAtencaoRepositorio.findAllLastPontoDeAtencao();

        for (var pontoDeAtencao : listaPontoDeAtencao) {
            DTOPontoDeAtencao dtoPontoDeAtencao = new DTOPontoDeAtencao(pontoDeAtencao);
            listaDTOPontoDeAtencao.add(dtoPontoDeAtencao);
        }

        return listaDTOPontoDeAtencao;
    }

    // analiseIndividualHemograma, método principal para realizar a análise
    // individual do hemograma de todos os pacientes com hemogramas relevantes;
    // Retorna: String - mensagem indicando o sucesso ou falha da análise;
    // Complexidade Teórica: O(m * n), onde m é o número de pacientes e n é o número
    // de hemogramas por paciente.
    // Complexidade Prática: O número de hemogramas é uma constante, inferior a 5,
    // então a complexidade prática é O(m).
    @Transactional
    public String analiseIndividualHemograma() {
        List<ModelPaciente> pacientes = pacienteRepositorio.findHemogramasRelevantes(Pageable.ofSize(5));

        String statusMsg = "Hemogramas analisados. Detalhamento:";
        for (ModelPaciente paciente : pacientes) {
            if (!validaSePacienteTemHemogramaNaoAnalisado(paciente)) {
                statusMsg += "\nPaciente: " + paciente.getNome() + " CPF: "
                        + paciente.getCpf() + " --- Não há novos hemogramas a serem analisados";

                continue;
            }

            if (!validarHemogramas(paciente)) {
                statusMsg += "\nPaciente: " + paciente.getNome() + " CPF: "
                        + paciente.getCpf() + " --- Erro na validação do hemograma";

                continue;
            }

            statusMsg += analisarHemogramas(paciente);
        }

        return statusMsg;
    }

    // validarHemogramas, método auxiliar para validar o hemograma de um paciente;
    // Parametro: ModelPaciente paciente - o paciente cujo hemograma será validado;
    // Retorna: boolean - true se o hemograma for válido, false caso contrário;
    // Complexidade Teorica: O(n), onde n é o número de hemogramas do paciente.
    // Complexidade Pratica: O(1), pois o número de hemogramas é uma constante,
    // inferior a 5.
    private boolean validarHemogramas(ModelPaciente paciente) {

        if (paciente.getHemogramas() == null || paciente.getHemogramas().isEmpty()) {
            return false;
        }

        ModelPontoDeAtencao pontoAtencao = new ModelPontoDeAtencao();

        Boolean status = true;
        LocalDate dataCorte = LocalDate.now().minusMonths(1);
        if (paciente.getHemogramas().size() > 0) {
            dataCorte = paciente.getHemogramas().get(0).getDataColeta().minusMonths(1);
            pontoAtencao.setDataCriacao(paciente.getHemogramas().get(0).getDataColeta());
        }

        for (var hemograma : paciente.getHemogramas()) {
            if (hemograma.getDataColeta().isBefore(dataCorte)) {
                continue;
            }

            Boolean localStatus = true;
            String msglocalStatus = "Hemograma " + hemograma.getDataColeta().toString();

            if (hemograma.getContagemLeucocitos() < 0) {
                localStatus = false;
                msglocalStatus += "\nInválido, Contagem de Leucocitos negativa.";
            }

            if (hemograma.getPorcentagemBasofilos().compareTo(BigDecimal.ZERO) < 0) {
                localStatus = false;
                msglocalStatus += "\nInválido, Porcentagem de Basófilos negativa.";
            }

            if (hemograma.getPorcentagemEosinofilos().compareTo(BigDecimal.ZERO) < 0) {
                localStatus = false;
                msglocalStatus += "\nInválido, Porcentagem de Eosinófilos negativa.";
            }

            if (hemograma.getPorcentagemLinfocitos().compareTo(BigDecimal.ZERO) < 0) {
                localStatus = false;
                msglocalStatus += "\nInválido, Porcentagem de Linfócitos negativa.";
            }

            if (hemograma.getPorcentagemMonocitos().compareTo(BigDecimal.ZERO) < 0) {
                localStatus = false;
                msglocalStatus += "\nInválido, Porcentagem de Monócitos negativa.";
            }

            if (hemograma.getPorcentagemNeutrofilos().compareTo(BigDecimal.ZERO) < 0) {
                localStatus = false;
                msglocalStatus += "\nInválido, Porcentagem de Neutrófilos negativa.";
            }

            if (hemograma.getPorcentagemBasofilos()
                    .add(hemograma.getPorcentagemEosinofilos())
                    .add(hemograma.getPorcentagemLinfocitos())
                    .add(hemograma.getPorcentagemMonocitos())
                    .add(hemograma.getPorcentagemNeutrofilos())
                    .compareTo(new BigDecimal("100")) != 0) {
                localStatus = false;
                msglocalStatus += "\nInválido, Soma das porcentagens dos tipos de leucócitos diferente de 100%.";
            }

            if (hemograma.getContagemEritrocitos().compareTo(BigDecimal.ZERO) < 0) {
                localStatus = false;
                msglocalStatus += "\nInválido, Contagem de Eritrócitos negativa.";
            }

            if (hemograma.getHemoglobina().compareTo(BigDecimal.ZERO) < 0) {
                localStatus = false;
                msglocalStatus += "\nInválido, Hemoglobina negativa.";
            }

            if (hemograma.getHematocrito().compareTo(BigDecimal.ZERO) < 0) {
                localStatus = false;
                msglocalStatus += "\nInválido, Hematócrito negativo.";
            }

            if (hemograma.getVcm().compareTo(BigDecimal.ZERO) < 0) {
                localStatus = false;
                msglocalStatus += "\nInválido, VCM negativo.";
            }

            if (hemograma.getHcm().compareTo(BigDecimal.ZERO) < 0) {
                localStatus = false;
                msglocalStatus += "\nInválido, HCM negativo.";
            }

            if (hemograma.getChcm().compareTo(BigDecimal.ZERO) < 0) {
                localStatus = false;
                msglocalStatus += "\nInválido, CHCM negativo.";
            }

            if (hemograma.getVpm().compareTo(BigDecimal.ZERO) < 0) {
                localStatus = false;
                msglocalStatus += "\nInválido, VPM negativo.";
            }

            if (hemograma.getAmplitudeDistribuicaoEritrocitaria().compareTo(BigDecimal.ZERO) < 0) {
                localStatus = false;
                msglocalStatus += "\nInválido, Amplitude de Distribuição Eritrocitária negativa.";
            }

            if (hemograma.getContagemPlaquetas() < 0) {
                localStatus = false;
                msglocalStatus += "\nInválido, Contagem de Plaquetas negativa.";
            }

            if (!localStatus) {
                status = false;
                pontoAtencao.setHipotese(pontoAtencao.getHipotese() + "\n" + msglocalStatus);
                pontoAtencao.setDescricao(pontoAtencao.getDescricao() + "\n" +
                        "Erro: Hemograma inválido encontrado na validação do paciente " + paciente.getCpf());

                pontoAtencao.addHemogramas(hemograma);
            }
        }

        if (!status) {
            paciente.addPontosDeAtencao(pontoAtencao);
            pacienteRepositorio.save(paciente);
        }

        return status;
    }

    // analisarHemogramas, método auxiliar para analisar o hemograma de um paciente;
    // Parametro: ModelPaciente paciente - o paciente cujo hemograma será analisado;
    // Retorno: String, a mensagem especificando o status da análise.
    // Complexidade Teórica: O(n), onde n é o número de hemogramas do paciente;
    private String analisarHemogramas(ModelPaciente paciente) {

        ModelPontoDeAtencao pontoAtencao = new ModelPontoDeAtencao();
        pontoAtencao.setHipotese("Sugestões:");
        pontoAtencao
                .setDescricao("Pacitente: " + paciente.getCpf() + " " + paciente.getNome() + " " + paciente.getSexo());

        LocalDate dataCorte = LocalDate.now().minusMonths(1);
        if (!paciente.getHemogramas().isEmpty()) {
            dataCorte = paciente.getHemogramas().get(0).getDataColeta().minusMonths(1);
            pontoAtencao.setDataCriacao(paciente.getHemogramas().get(0).getDataColeta());
        }

        for (var hemograma : paciente.getHemogramas()) {
            if (hemograma.getDataColeta().isBefore(dataCorte)) {
                continue;
            }

            pontoAtencao
                    .setHipotese(pontoAtencao.getHipotese() + "\n\nHemograma " + hemograma.getDataColeta().toString());

            validacaoQtdLeucocitos(hemograma, pontoAtencao);
            validacaoLeucocitosNeutrofilos(hemograma, pontoAtencao);
            validacaoLeucocitosLinfocitos(hemograma, pontoAtencao);
            validacaoLeucocitosBasofilos(hemograma, pontoAtencao);
            validacaoLeucocitosMonocitos(hemograma, pontoAtencao);
            validacaoLeucocitosEosinofilos(hemograma, pontoAtencao);
            validacaoQtdEritrocitos(paciente.getSexo(), hemograma, pontoAtencao);
            validacaoQtdHemoglobina(paciente.getSexo(), hemograma, pontoAtencao);
            validacaoQtdHematocrito(paciente.getSexo(), hemograma, pontoAtencao);
            validacaoVcm(hemograma, pontoAtencao);
            validacaoHcm(hemograma, pontoAtencao);
            validacaoChcm(hemograma, pontoAtencao);
            validacaoVpm(hemograma, pontoAtencao);
            validacaoAmplitudeDistribuicaoEritrocitaria(hemograma, pontoAtencao);
            validacaoQtdPlaquetas(hemograma, pontoAtencao);

            ValidacaoAnemia(pontoAtencao);

            pontoAtencao.addHemogramas(hemograma);
        }

        ModelHemograma ultimo = !paciente.getHemogramas().isEmpty() ? paciente.getHemogramas().get(0) : null;
        atualizarPossivelFumantePorHemograma(paciente, ultimo);

        paciente.addPontosDeAtencao(pontoAtencao);
        pacienteRepositorio.save(paciente);

        return "\nPaciente: " + paciente.getNome() + " CPF: "
                + paciente.getCpf() + " --- Hemogramas analisados com sucesso";
    }

    // validaSePacienteTemHemogramaNaoAnalisado, método auxiliar para verificar se o
    // paciente tem hemogramas não analisados;
    // Retorno: boolean - true se o paciente tem hemogramas não analisados, false
    // caso contrário;
    // Complexidade Teórica: O(n), pois percorre-se uma lista de hemogramas.
    // Complexidade Pratica: O(1), pois o número de hemogramas na lista é
    // constante, inferior a 5.
    private boolean validaSePacienteTemHemogramaNaoAnalisado(ModelPaciente paciente) {

        Optional<ModelPontoDeAtencao> pontoAtencaoOpt = pontoDeAtencaoRepositorio
                .findByLastPontoDeAtencao(paciente.getCpf());

        return paciente.getHemogramas().isEmpty() || (!pontoAtencaoOpt.isEmpty()
                && pontoAtencaoOpt.get().getHemogramas().contains(paciente.getHemogramas().get(0))) ? false : true;

    }

    // validacaoQtdLeucocitos, método auxiliar para validar a quantidade de
    // leucócitos em um hemograma, 4500 a 11.000 células/µL;
    // Parâmetros:
    // ModelHemograma hemograma - o hemograma a ser validado
    // ModelPontoDeAtencao pontoAtencao - o ponto de atenção onde é feito o registro
    // Retorno: void
    // Complexidade: O(1), apenas algumas comparações e atribuições são feitas.
    private void validacaoQtdLeucocitos(ModelHemograma hemograma,
            ModelPontoDeAtencao pontoAtencao) {
        if (hemograma.getContagemLeucocitos() < 4500) {
            pontoAtencao.setHipotese(pontoAtencao.getHipotese() + "\nLeucopenia");

            pontoAtencao.setDescricao(pontoAtencao.getDescricao() + "\nPaciente com contagem de leucócitos baixa - "
                    + hemograma.getContagemLeucocitos() + " células/µL.");

        } else if (hemograma.getContagemLeucocitos() > 11000) {
            pontoAtencao.setHipotese(pontoAtencao.getHipotese() + "\nLeucocitose - Risco de infecção bacteriana");

            pontoAtencao.setDescricao(pontoAtencao.getDescricao() + "\nPaciente com contagem de leucócitos alta - "
                    + hemograma.getContagemLeucocitos() + " células/µL.");
        }

        else {
            pontoAtencao.setDescricao(pontoAtencao.getDescricao() + "\nContagem de leucócitos normal - "
                    + hemograma.getContagemLeucocitos() + " células/µL.");
        }
    }

    // validacaoLeucocitosNeutrofilos, método auxiliar para validar a porcentagem de
    // neutrófilos em um hemograma, 40% a 75%;
    // Parâmetros:
    // ModelHemograma hemograma - o hemograma a ser validado
    // ModelPontoDeAtencao pontoAtencao - o ponto de atenção onde é feito o registro
    // Retorno: void
    // Complexidade: O(1), apenas algumas comparações e atribuições são feitas.
    private void validacaoLeucocitosNeutrofilos(ModelHemograma hemograma,
            ModelPontoDeAtencao pontoAtencao) {
        if (hemograma.getPorcentagemNeutrofilos().compareTo(new BigDecimal("40")) < 0) {
            pontoAtencao.setHipotese(pontoAtencao.getHipotese() + "\nNeutropenia");

            pontoAtencao.setDescricao(pontoAtencao.getDescricao() + "\nPaciente com porcentagem de neutrófilos baixa - "
                    + hemograma.getPorcentagemNeutrofilos() + "%.");

        } else if (hemograma.getPorcentagemNeutrofilos().compareTo(new BigDecimal("75")) > 0) {
            pontoAtencao.setHipotese(pontoAtencao.getHipotese() + "\nNeutrofilia");

            pontoAtencao.setDescricao(pontoAtencao.getDescricao() + "\nPaciente com porcentagem de neutrófilos alta - "
                    + hemograma.getPorcentagemNeutrofilos() + "%.");
        }

        else {
            pontoAtencao.setDescricao(pontoAtencao.getDescricao() + "\nPorcentagem de neutrófilos normal - "
                    + hemograma.getPorcentagemNeutrofilos() + "%.");
        }
    }

    // validacaoLeucocitosLinfocitos, método auxiliar para validar a porcentagem de
    // linfócitos em um hemograma, 20% a 45%;
    // Parâmetros:
    // ModelHemograma hemograma - o hemograma a ser validado
    // ModelPontoDeAtencao pontoAtencao - o ponto de atenção onde é feito o registro
    // Retorno: void
    // Complexidade: O(1), apenas algumas comparações e atribuições são feitas.
    private void validacaoLeucocitosLinfocitos(ModelHemograma hemograma,
            ModelPontoDeAtencao pontoAtencao) {
        if (hemograma.getPorcentagemLinfocitos().compareTo(new BigDecimal("20")) < 0) {
            pontoAtencao.setHipotese(pontoAtencao.getHipotese() + "\nLinfopenia");

            pontoAtencao.setDescricao(pontoAtencao.getDescricao() + "\nPaciente com porcentagem de linfócitos baixa - "
                    + hemograma.getPorcentagemLinfocitos() + "%.");

        } else if (hemograma.getPorcentagemLinfocitos().compareTo(new BigDecimal("45")) > 0) {
            pontoAtencao.setHipotese(pontoAtencao.getHipotese() + "\nLinfocitose");

            pontoAtencao.setDescricao(pontoAtencao.getDescricao() + "\nPaciente com porcentagem de linfócitos alta - "
                    + hemograma.getPorcentagemLinfocitos() + "%.");
        }

        else {
            pontoAtencao.setDescricao(pontoAtencao.getDescricao() + "\nPorcentagem de linfócitos normal - "
                    + hemograma.getPorcentagemLinfocitos() + "%.");
        }
    }

    // validacaoLeucocitosBasofilos, método auxiliar para validar a porcentagem de
    // basófilos em um hemograma, 0% a 2%;
    // Parâmetros:
    // ModelHemograma hemograma - o hemograma a ser validado
    // ModelPontoDeAtencao pontoAtencao - o ponto de atenção onde é feito o registro
    // Retorno: void
    // Complexidade: O(1), apenas algumas comparações e atribuições são feitas.
    private void validacaoLeucocitosBasofilos(ModelHemograma hemograma,
            ModelPontoDeAtencao pontoAtencao) {
        if (hemograma.getPorcentagemBasofilos().compareTo(BigDecimal.ZERO) < 0) {
            pontoAtencao.setHipotese(pontoAtencao.getHipotese() + "\nBasopenia");

            pontoAtencao.setDescricao(pontoAtencao.getDescricao() + "\nPaciente com porcentagem de basófilos baixa - "
                    + hemograma.getPorcentagemBasofilos() + "%.");

        } else if (hemograma.getPorcentagemBasofilos().compareTo(new BigDecimal("2")) > 0) {
            pontoAtencao.setHipotese(pontoAtencao.getHipotese() + "\nBasofilia");

            pontoAtencao.setDescricao(pontoAtencao.getDescricao() + "\nPaciente com porcentagem de basófilos alta - "
                    + hemograma.getPorcentagemBasofilos() + "%.");
        }

        else {
            pontoAtencao.setDescricao(pontoAtencao.getDescricao() + "\nPorcentagem de basófilos normal - "
                    + hemograma.getPorcentagemBasofilos() + "%.");
        }
    }

    // validacaoLeucocitosMonocitos, método auxiliar para validar a porcentagem de
    // monócitos em um hemograma, 2% a 10%;
    // Parâmetros:
    // ModelHemograma hemograma - o hemograma a ser validado
    // ModelPontoDeAtencao pontoAtencao - o ponto de atenção onde é feito o registro
    // Retorno: void
    // Complexidade: O(1), apenas algumas comparações e atribuições são feitas.
    private void validacaoLeucocitosMonocitos(ModelHemograma hemograma,
            ModelPontoDeAtencao pontoAtencao) {
        if (hemograma.getPorcentagemMonocitos().compareTo(new BigDecimal("2")) < 0) {
            pontoAtencao.setHipotese(pontoAtencao.getHipotese() + "\nMonocitopenia");

            pontoAtencao.setDescricao(pontoAtencao.getDescricao() + "\nPaciente com porcentagem de monócitos baixa - "
                    + hemograma.getPorcentagemMonocitos() + "%.");

        } else if (hemograma.getPorcentagemMonocitos().compareTo(new BigDecimal("10")) > 0) {
            pontoAtencao.setHipotese(pontoAtencao.getHipotese() + "\nMonocitose");

            pontoAtencao.setDescricao(pontoAtencao.getDescricao() + "\nPaciente com porcentagem de monócitos alta - "
                    + hemograma.getPorcentagemMonocitos() + "%.");
        }

        else {
            pontoAtencao.setDescricao(pontoAtencao.getDescricao() + "\nPorcentagem de monócitos normal - "
                    + hemograma.getPorcentagemMonocitos() + "%.");
        }
    }

    // validacaoLeucocitosEosinofilos, método auxiliar para validar a porcentagem de
    // eosinófilos em um hemograma, 1% a 6%;
    // Parâmetros:
    // ModelHemograma hemograma - o hemograma a ser validado
    // ModelPontoDeAtencao pontoAtencao - o ponto de atenção onde é feito o registro
    // Retorno: void
    // Complexidade: O(1), apenas algumas comparações e atribuições são feitas.
    private void validacaoLeucocitosEosinofilos(ModelHemograma hemograma,
            ModelPontoDeAtencao pontoAtencao) {
        if (hemograma.getPorcentagemEosinofilos().compareTo(new BigDecimal("1")) < 0) {
            pontoAtencao.setHipotese(pontoAtencao.getHipotese() + "\nEosinopenia");

            pontoAtencao.setDescricao(pontoAtencao.getDescricao() + "\nPaciente com porcentagem de eosinófilos baixa - "
                    + hemograma.getPorcentagemEosinofilos() + "%.");

        } else if (hemograma.getPorcentagemEosinofilos().compareTo(new BigDecimal("6")) > 0) {
            pontoAtencao.setHipotese(pontoAtencao.getHipotese() + "\nEosinofilia");

            pontoAtencao.setDescricao(pontoAtencao.getDescricao() + "\nPaciente com porcentagem de eosinófilos alta - "
                    + hemograma.getPorcentagemEosinofilos() + "%.");
        }

        else {
            pontoAtencao.setDescricao(pontoAtencao.getDescricao() + "\nPorcentagem de eosinófilos normal - "
                    + hemograma.getPorcentagemEosinofilos() + "%.");
        }
    }

    // validacaoQtdEritrocitos, método auxiliar para validar a quantidade de
    // eritrócitos em um hemograma, valores variam conforme o sexo do paciente.
    // Parâmetros:
    // String sexo - o sexo do paciente ("M" para masculino, "F" para feminino)
    // ModelHemograma hemograma - o hemograma a ser validado
    // ModelPontoDeAtencao pontoAtencao - o ponto de atenção onde é feito o registro
    // Retorno: void
    // Complexidade: O(1), apenas algumas comparações e atribuições são feitas.
    private void validacaoQtdEritrocitos(String sexo, ModelHemograma hemograma,
            ModelPontoDeAtencao pontoAtencao) {

        BigDecimal[][] limites = {
                { new BigDecimal("4.7"), new BigDecimal("6.1") }, // Masculino
                { new BigDecimal("4.2"), new BigDecimal("5.4") } // Feminino
        };

        int sexoIndex = sexo.equalsIgnoreCase("M") ? 0 : 1;

        if (hemograma.getContagemEritrocitos().compareTo(limites[sexoIndex][0]) < 0) {
            pontoAtencao.setHipotese(pontoAtencao.getHipotese() + "\nEritropenia");

            pontoAtencao.setDescricao(pontoAtencao.getDescricao() + "\nPaciente com contagem de eritrócitos baixa - "
                    + hemograma.getContagemEritrocitos() + " milhões/µL.");

        } else if (hemograma.getContagemEritrocitos().compareTo(limites[sexoIndex][1]) > 0) {
            pontoAtencao.setHipotese(pontoAtencao.getHipotese() + "\nEritrocitose");

            pontoAtencao.setDescricao(pontoAtencao.getDescricao() + "\nPaciente com contagem de eritrócitos alta - "
                    + hemograma.getContagemEritrocitos() + " milhões/µL.");
        }

        else {
            pontoAtencao.setDescricao(pontoAtencao.getDescricao() + "\nContagem de eritrócitos normal - "
                    + hemograma.getContagemEritrocitos() + " milhões/µL.");
        }
    }

    // validacaoQtdHemoglobina, método auxiliar para validar a quantidade de
    // hemoglobina em um hemograma, valores variam conforme o sexo do paciente.
    // Parâmetros:
    // String sexo - o sexo do paciente ("M" para masculino, "F" para feminino)
    // ModelHemograma hemograma - o hemograma a ser validado
    // ModelPontoDeAtencao pontoAtencao - o ponto de atenção onde é feito o registro
    // Retorno: void
    // Complexidade: O(1), apenas algumas comparações e atribuições são feitas.
    private void validacaoQtdHemoglobina(String sexo, ModelHemograma hemograma,
            ModelPontoDeAtencao pontoAtencao) {
        BigDecimal[][] limites = {
                { new BigDecimal("13.8"), new BigDecimal("17.2") }, // Masculino
                { new BigDecimal("12.1"), new BigDecimal("15.1") } // Feminino
        };

        int sexoIndex = sexo.equalsIgnoreCase("M") ? 0 : 1;

        if (hemograma.getHemoglobina().compareTo(limites[sexoIndex][0]) < 0) {
            pontoAtencao.setHipotese(pontoAtencao.getHipotese() + "\nAnemia");

            pontoAtencao.setDescricao(pontoAtencao.getDescricao() + "\nPaciente com hemoglobina baixa - "
                    + hemograma.getHemoglobina() + " g/dL.");

            faixaDeHemoglobina = -1;

        } else if (hemograma.getHemoglobina().compareTo(limites[sexoIndex][1]) > 0) {
            pontoAtencao.setHipotese(pontoAtencao.getHipotese() + "\nPolicitemia");

            pontoAtencao.setDescricao(pontoAtencao.getDescricao() + "\nPaciente com hemoglobina alta - "
                    + hemograma.getHemoglobina() + " g/dL.");

            faixaDeHemoglobina = 1;
        }

        else {
            pontoAtencao.setDescricao(pontoAtencao.getDescricao() + "\nHemoglobina normal - "
                    + hemograma.getHemoglobina() + " g/dL.");

            faixaDeHemoglobina = 0;
        }
    }

    // validacaoQtdHematocrito, método auxiliar para validar a quantidade de
    // hematócrito em um hemograma, valores variam conforme o sexo do paciente.
    // Parâmetros:
    // String sexo - o sexo do paciente ("M" para masculino, "F" para feminino)
    // ModelHemograma hemograma - o hemograma a ser validado
    // ModelPontoDeAtencao pontoAtencao - o ponto de atenção onde é feito o registro
    // Retorno: void
    // Complexidade: O(1), apenas algumas comparações e atribuições são feitas.
    private void validacaoQtdHematocrito(String sexo, ModelHemograma hemograma,
            ModelPontoDeAtencao pontoAtencao) {
        BigDecimal[][] limites = {
                { new BigDecimal("40.7"), new BigDecimal("50.3") }, // Masculino
                { new BigDecimal("36.1"), new BigDecimal("44.3") } // Feminino
        };

        int sexoIndex = sexo.equalsIgnoreCase("M") ? 0 : 1;

        if (hemograma.getHematocrito().compareTo(limites[sexoIndex][0]) < 0) {
            pontoAtencao.setHipotese(pontoAtencao.getHipotese() + "\nHematócrito baixo");

            pontoAtencao.setDescricao(pontoAtencao.getDescricao() + "\nPaciente com hematócrito baixo - "
                    + hemograma.getHematocrito() + "%.");

            faixaDeHematocrito = -1;

        } else if (hemograma.getHematocrito().compareTo(limites[sexoIndex][1]) > 0) {
            pontoAtencao.setHipotese(pontoAtencao.getHipotese() + "\nHematócrito alto");

            pontoAtencao.setDescricao(pontoAtencao.getDescricao() + "\nPaciente com hematócrito alto - "
                    + hemograma.getHematocrito() + "%.");

            faixaDeHematocrito = 1;
        }

        else {
            pontoAtencao.setDescricao(pontoAtencao.getDescricao() + "\nHematócrito normal - "
                    + hemograma.getHematocrito() + "%.");

            faixaDeHematocrito = 0;
        }
    }

    // validacaoVcm, método auxiliar para validar o volume corpuscular médio (VCM)
    // em um hemograma, 80 a 100 fL;
    // Parâmetros:
    // ModelHemograma hemograma - o hemograma a ser validado
    // ModelPontoDeAtencao pontoAtencao - o ponto de atenção onde é feito o registro
    // Retorno: void
    // Complexidade: O(1), apenas algumas comparações e atribuições são feitas.
    private void validacaoVcm(ModelHemograma hemograma,
            ModelPontoDeAtencao pontoAtencao) {
        if (hemograma.getVcm().compareTo(new BigDecimal("80")) < 0) {
            pontoAtencao.setHipotese(pontoAtencao.getHipotese() + "\nMicrocitose");

            pontoAtencao.setDescricao(pontoAtencao.getDescricao() + "\nPaciente com VCM baixo - "
                    + hemograma.getVcm() + " fL.");

            faixaDeVcm = -1;

        } else if (hemograma.getVcm().compareTo(new BigDecimal("100")) > 0) {
            pontoAtencao.setHipotese(pontoAtencao.getHipotese() + "\nMacrocitose");

            pontoAtencao.setDescricao(pontoAtencao.getDescricao() + "\nPaciente com VCM alto - "
                    + hemograma.getVcm() + " fL.");

            faixaDeVcm = 1;
        }

        else {
            pontoAtencao.setDescricao(pontoAtencao.getDescricao() + "\nVCM normal - "
                    + hemograma.getVcm() + " fL.");

            faixaDeVcm = 0;
        }
    }

    // validacaoHcm, método auxiliar para validar o hemoglobina corpuscular média
    // (HCM) em um hemograma, 27 a 33 pg;
    // Parâmetros:
    // ModelHemograma hemograma - o hemograma a ser validado
    // ModelPontoDeAtencao pontoAtencao - o ponto de atenção onde é feito o registro
    // Retorno: void
    // Complexidade: O(1), apenas algumas comparações e atribuições são feitas.
    private void validacaoHcm(ModelHemograma hemograma,
            ModelPontoDeAtencao pontoAtencao) {
        if (hemograma.getHcm().compareTo(new BigDecimal("27")) < 0) {
            pontoAtencao.setHipotese(pontoAtencao.getHipotese() + "\nHemoglobina Corpuscular Média baixa");

            pontoAtencao.setDescricao(pontoAtencao.getDescricao() + "\nPaciente com HCM baixo - "
                    + hemograma.getHcm() + " pg.");

        } else if (hemograma.getHcm().compareTo(new BigDecimal("31")) > 0) {
            pontoAtencao.setHipotese(pontoAtencao.getHipotese() + "\nHemoglobina Corpuscular Média alta");

            pontoAtencao.setDescricao(pontoAtencao.getDescricao() + "\nPaciente com HCM alto - "
                    + hemograma.getHcm() + " pg.");
        }

        else {
            pontoAtencao.setDescricao(pontoAtencao.getDescricao() + "\nHCM normal - "
                    + hemograma.getHcm() + " pg.");
        }
    }

    // validacaoChcm, método auxiliar para validar a concentração de hemoglobina
    // corpuscular média (CHCM) em um hemograma, 32 a 36 g/dL;
    // Parâmetros:
    // ModelHemograma hemograma - o hemograma a ser validado
    // ModelPontoDeAtencao pontoAtencao - o ponto de atenção onde é feito o registro
    // Retorno: void
    // Complexidade: O(1), apenas algumas comparações e atribuições são feitas.
    private void validacaoChcm(ModelHemograma hemograma,
            ModelPontoDeAtencao pontoAtencao) {
        if (hemograma.getChcm().compareTo(new BigDecimal("32")) < 0) {
            pontoAtencao
                    .setHipotese(pontoAtencao.getHipotese() + "\nConcentração de Hemoglobina Corpuscular Média baixa");

            pontoAtencao.setDescricao(pontoAtencao.getDescricao() + "\nPaciente com CHCM baixa - "
                    + hemograma.getChcm() + " g/dL.");

        } else if (hemograma.getChcm().compareTo(new BigDecimal("36")) > 0) {
            pontoAtencao
                    .setHipotese(pontoAtencao.getHipotese() + "\nConcentração de Hemoglobina Corpuscular Média alta");

            pontoAtencao.setDescricao(pontoAtencao.getDescricao() + "\nPaciente com CHCM alta - "
                    + hemograma.getChcm() + " g/dL.");
        }

        else {
            pontoAtencao.setDescricao(pontoAtencao.getDescricao() + "\nCHCM normal - "
                    + hemograma.getChcm() + " g/dL.");
        }
    }

    // validacaoVpm, método auxiliar para validar o volume plaquetário médio (VPM)
    // em um hemograma, 7.5 a 11.5 fL;
    // Parâmetros:
    // ModelHemograma hemograma - o hemograma a ser validado
    // ModelPontoDeAtencao pontoAtencao - o ponto de atenção onde é feito o registro
    // Retorno: void
    // Complexidade: O(1), apenas algumas comparações e atribuições são feitas.
    private void validacaoVpm(ModelHemograma hemograma,
            ModelPontoDeAtencao pontoAtencao) {
        if (hemograma.getVpm().compareTo(new BigDecimal("7.5")) < 0) {
            pontoAtencao.setHipotese(pontoAtencao.getHipotese() + "\nVolume Plaquetário Médio baixo");

            pontoAtencao.setDescricao(pontoAtencao.getDescricao() + "\nPaciente com VPM baixo - "
                    + hemograma.getVpm() + " fL.");

        } else if (hemograma.getVpm().compareTo(new BigDecimal("11.5")) > 0) {
            pontoAtencao.setHipotese(pontoAtencao.getHipotese() + "\nVolume Plaquetário Médio alto");

            pontoAtencao.setDescricao(pontoAtencao.getDescricao() + "\nPaciente com VPM alto - "
                    + hemograma.getVpm() + " fL.");
        }

        else {
            pontoAtencao.setDescricao(pontoAtencao.getDescricao() + "\nVPM normal - "
                    + hemograma.getVpm() + " fL.");
        }
    }

    // validacaoAmplitudeDistribuicaoEritrocitaria, método auxiliar para validar a
    // amplitude de distribuição eritrocitária (ADE) em um hemograma, 12% a 15%;
    // Parâmetros:
    // ModelHemograma hemograma - o hemograma a ser validado
    // ModelPontoDeAtencao pontoAtencao - o ponto de atenção onde é feito o registro
    // Retorno: void
    // Complexidade: O(1), apenas algumas comparações e atribuições são feitas.
    private void validacaoAmplitudeDistribuicaoEritrocitaria(ModelHemograma hemograma,
            ModelPontoDeAtencao pontoAtencao) {
        if (hemograma.getAmplitudeDistribuicaoEritrocitaria().compareTo(new BigDecimal("12")) < 0) {
            pontoAtencao.setHipotese(pontoAtencao.getHipotese() + "\nAmplitude de Distribuição Eritrocitária baixa");

            pontoAtencao.setDescricao(pontoAtencao.getDescricao() + "\nPaciente com ADE baixa - "
                    + hemograma.getAmplitudeDistribuicaoEritrocitaria() + "%.");

        } else if (hemograma.getAmplitudeDistribuicaoEritrocitaria().compareTo(new BigDecimal("15")) > 0) {
            pontoAtencao.setHipotese(pontoAtencao.getHipotese() + "\nAmplitude de Distribuição Eritrocitária alta");

            pontoAtencao.setDescricao(pontoAtencao.getDescricao() + "\nPaciente com ADE alta - "
                    + hemograma.getAmplitudeDistribuicaoEritrocitaria() + "%.");
        }

        else {
            pontoAtencao.setDescricao(pontoAtencao.getDescricao() + "\nADE normal - "
                    + hemograma.getAmplitudeDistribuicaoEritrocitaria() + "%.");
        }
    }

    // validacaoQtdPlaquetas, método auxiliar para validar a quantidade de
    // plaquetas em um hemograma, 150.000 a 450.000 células
    // Parâmetros:
    // ModelHemograma hemograma - o hemograma a ser validado
    // ModelPontoDeAtencao pontoAtencao - o ponto de atenção onde é feito o registro
    // Retorno: void
    // Complexidade: O(1), apenas algumas comparações e atribuições são feitas.
    private void validacaoQtdPlaquetas(ModelHemograma hemograma,
            ModelPontoDeAtencao pontoAtencao) {
        if (hemograma.getContagemPlaquetas() < 150000) {
            pontoAtencao.setHipotese(pontoAtencao.getHipotese() + "\nTrombocitopenia - Risco de sangramento");

            pontoAtencao.setDescricao(pontoAtencao.getDescricao() + "\nPaciente com contagem de plaquetas baixa - "
                    + hemograma.getContagemPlaquetas() + " células/µL.");

        } else if (hemograma.getContagemPlaquetas() > 450000) {
            pontoAtencao.setHipotese(pontoAtencao.getHipotese() + "\nTrombocitose");

            pontoAtencao.setDescricao(pontoAtencao.getDescricao() + "\nPaciente com contagem de plaquetas alta - "
                    + hemograma.getContagemPlaquetas() + " células/µL.");
        }

        else {
            pontoAtencao.setDescricao(pontoAtencao.getDescricao() + "\nContagem de plaquetas normal - "
                    + hemograma.getContagemPlaquetas() + " células/µL.");
        }
    }

    private void ValidacaoAnemia(ModelPontoDeAtencao pontoAtencao) {
        if ((faixaDeHemoglobina == -1 || faixaDeHematocrito == -1) || faixaDeVcm == 1) {
            pontoAtencao.setHipotese(pontoAtencao.getHipotese() + "\nAnemia macrocítica");
        } else if ((faixaDeHemoglobina == -1 || faixaDeHematocrito == -1) && faixaDeVcm == -1) {
            pontoAtencao.setHipotese(pontoAtencao.getHipotese() + "\nAnemia microcítica");
        }
    }

// Avalia o último hemograma analisado do paciente e atualiza paciente.possivelFumante.
// Critério (heurística inicial, configurável):
//  - Hemoglobina acima do limite superior (M:17.2 / F:15.1) -> +2 pontos
//  - Hematócrito acima do limite superior (M:50.3 / F:44.3) -> +2 pontos
//  - Contagem de eritrócitos acima do limite superior (M:6.1 / F:5.4) -> +1 ponto
//  - Contagem de leucócitos > 11000 -> +1 ponto
//  - % de neutrófilos > 75 -> +1 ponto
// Se pontos >= 3 => possivelFumante = true, senão false.
private void atualizarPossivelFumantePorHemograma(ModelPaciente paciente, ModelHemograma hemograma) {
    if (paciente == null || hemograma == null) {
        return;
    }

    int pontos = 0;
    String sexo = paciente.getSexo() != null ? paciente.getSexo().trim().toUpperCase() : "M";

    try {
        java.math.BigDecimal hb = hemograma.getHemoglobina();
        java.math.BigDecimal ht = hemograma.getHematocrito();
        java.math.BigDecimal er = hemograma.getContagemEritrocitos();
        Integer leucocitos = hemograma.getContagemLeucocitos();
        java.math.BigDecimal pctNeutro = hemograma.getPorcentagemNeutrofilos();

        // Limites por sexo
        java.math.BigDecimal hbLimite = sexo.equals("F") ? new java.math.BigDecimal("15.1")
                                                       : new java.math.BigDecimal("17.2");
        java.math.BigDecimal htLimite = sexo.equals("F") ? new java.math.BigDecimal("44.3")
                                                       : new java.math.BigDecimal("50.3");
        java.math.BigDecimal erLimite = sexo.equals("F") ? new java.math.BigDecimal("5.4")
                                                       : new java.math.BigDecimal("6.1");

        if (hb != null && hb.compareTo(hbLimite) > 0) {
            pontos += 2;
        }

        if (ht != null && ht.compareTo(htLimite) > 0) {
            pontos += 2;
        }

        if (er != null && er.compareTo(erLimite) > 0) {
            pontos += 1;
        }

        if (leucocitos != null && leucocitos > 11000) {
            pontos += 1;
        }

        if (pctNeutro != null && pctNeutro.compareTo(new java.math.BigDecimal("75")) > 0) {
            pontos += 1;
        }
    } catch (Exception e) {
        // Não queremos quebrar o fluxo de análise por causa de um valor inválido.
        pontos = 0;
    }

    boolean ehPossivelFumante = pontos >= 3;
    paciente.setPossivelFumante(ehPossivelFumante);
    // Salva imediatamente para garantir que o estado reflete o último hemograma.
    pacienteRepositorio.save(paciente);
}


}
