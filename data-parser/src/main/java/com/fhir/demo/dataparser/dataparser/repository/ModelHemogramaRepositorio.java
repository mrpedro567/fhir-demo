package com.fhir.demo.dataparser.dataparser.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fhir.demo.dataparser.dataparser.model.ModelHemograma;

public interface ModelHemogramaRepositorio extends JpaRepository<ModelHemograma, Long>{
    
}
