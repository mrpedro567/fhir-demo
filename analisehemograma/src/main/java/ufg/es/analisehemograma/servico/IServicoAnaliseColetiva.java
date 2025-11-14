package ufg.es.analisehemograma.servico;

import java.util.List;

import ufg.es.analisehemograma.model.AnaliseColetiva.AlertaMunicipioDTO;

public interface IServicoAnaliseColetiva {
    List<AlertaMunicipioDTO> gerarAlertas(double limiarPercentual); // limiarPercentual em 0..100 (ex: 5.0)
}