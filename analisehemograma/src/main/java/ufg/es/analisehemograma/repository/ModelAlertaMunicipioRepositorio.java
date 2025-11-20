package ufg.es.analisehemograma.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ufg.es.analisehemograma.model.AnaliseColetiva.ModelAlertaMunicipio;

public interface ModelAlertaMunicipioRepositorio extends JpaRepository<ModelAlertaMunicipio, Long> {

    @Query("SELECT a FROM ModelAlertaMunicipio a WHERE a.dataCriacao IN ( " +
           " SELECT MAX(a2.dataCriacao) FROM ModelAlertaMunicipio a2 GROUP BY a2.municipio ) " +
           "ORDER BY a.municipio.nome")
    List<ModelAlertaMunicipio> findAllLatestPerMunicipio();
}