package ufg.es.analisehemograma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ufg.es.analisehemograma.model.Municipio.ModelMunicipio;

@Repository
public interface ModelMunicipioRepositorio extends JpaRepository<ModelMunicipio, String> {

    ModelMunicipio findByNome(String nome);
}