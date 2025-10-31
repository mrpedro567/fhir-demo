package ufg.es.analisehemograma.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ufg.es.analisehemograma.model.Hemograma.ModelHemograma;

public interface ModelHemogramaRepositorio extends JpaRepository<ModelHemograma, Long> {

}