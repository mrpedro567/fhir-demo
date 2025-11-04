package ufg.es.analisehemograma.servico;

import java.util.List;

import ufg.es.analisehemograma.model.PontoDeAtencao.DTOPontoDeAtencao;
import ufg.es.analisehemograma.model.PontoDeAtencao.ModelPontoDeAtencao;

public interface IServicoAnaliseHemograma {

    // analiseIndividualHemograma, método principal para realizar a análise
    // individual do hemograma de todos os pacientes com hemogramas relevantes;
    // Retorna: String - mensagem indicando o sucesso ou falha da análise;
    // Complexidade Teórica: O(m * n), onde m é o número de pacientes e n é o número
    // de hemogramas por paciente.
    // Complexidade Prática: O número de hemogramas é uma constante, inferior a 5,
    // então a complexidade prática é O(m).
    public String analiseIndividualHemograma();

    // analiseIndividualHemogramaTodosUltimosPontosDeAtencao, método que retorna
    // todos os pontos de atenção mais recentes para cada paciente.
    // Retorna: List<ModelPontoDeAtencao> - lista de pontos de atenção mais
    // recentes.
    // Complexidade Teórica: O(m), onde m é o número de pacientes.
    public List<DTOPontoDeAtencao> analiseIndividualHemogramaTodosUltimosPontosDeAtencao();

}
