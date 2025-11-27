package ufg.es.analisehemograma.servico;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ufg.es.analisehemograma.model.AnaliseColetiva.AlertaMunicipioDTO;
import ufg.es.analisehemograma.model.AnaliseColetiva.ModelAlertaMunicipio;
import ufg.es.analisehemograma.model.AnaliseColetiva.ResultadoColetivoDTO;
import ufg.es.analisehemograma.model.Municipio.ModelMunicipio;
import ufg.es.analisehemograma.repository.ModelAlertaMunicipioRepositorio;
import ufg.es.analisehemograma.repository.ModelMunicipioRepositorio;
import ufg.es.analisehemograma.repository.ModelPacienteRepositorio;

@Service
public class ServicoAnaliseColetiva implements IServicoAnaliseColetiva {

    private final ModelPacienteRepositorio pacienteRepositorio;
    private final ModelAlertaMunicipioRepositorio alertaRepositorio;
    private final ModelMunicipioRepositorio municipioRepositorio;

    public ServicoAnaliseColetiva(ModelPacienteRepositorio pacienteRepositorio,
                                  ModelAlertaMunicipioRepositorio alertaRepositorio,
                                  ModelMunicipioRepositorio municipioRepositorio) {
        this.pacienteRepositorio = pacienteRepositorio;
        this.alertaRepositorio = alertaRepositorio;
        this.municipioRepositorio = municipioRepositorio;
    }

    @Override
    @Transactional
    public List<AlertaMunicipioDTO> gerarAlertas(double limiarPercentual) {
        LocalDate hoje = LocalDate.now();
        LocalDate ldMaior = hoje.minusYears(15);
        LocalDate ldMenor = hoje.minusYears(10);

        Date dataMaior = Date.from(ldMaior.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date dataMenor = Date.from(ldMenor.atStartOfDay(ZoneId.systemDefault()).toInstant());

        List<ResultadoColetivoDTO> resultados = pacienteRepositorio
                .contarFumantesPorMunicipioFaixaEtaria(dataMaior, dataMenor);

        List<AlertaMunicipioDTO> alertas = new ArrayList<>();
        for (ResultadoColetivoDTO r : resultados) {
            Integer populacao = r.getTotalHabitantes10a15();
            long contagem = r.getContagemFumantes10a15() == null ? 0L : r.getContagemFumantes10a15();

            if (populacao == null || populacao == 0) {
                continue;
            }

            double percentual = ((double) contagem / (double) populacao) * 100.0;
            if (percentual > limiarPercentual) {
                alertas.add(new AlertaMunicipioDTO(r.getNomeMunicipio(), percentual));

                ModelMunicipio m = municipioRepositorio.findByNome(r.getNomeMunicipio());
                if (m != null) {
                    ModelAlertaMunicipio a = new ModelAlertaMunicipio();
                    a.setMunicipio(m);
                    a.setPercentual(percentual);
                    a.setContagemFumantes10a15(contagem);
                    a.setPopulacao10a15(populacao);
                    a.setDataCriacao(Instant.now());
                    alertaRepositorio.save(a);
                }
            }
        }
        return alertas;
    }
}