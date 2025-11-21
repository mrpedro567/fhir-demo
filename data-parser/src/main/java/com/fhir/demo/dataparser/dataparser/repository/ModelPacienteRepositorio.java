package com.fhir.demo.dataparser.dataparser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fhir.demo.dataparser.dataparser.model.ModelPaciente;
import java.util.List;


public interface ModelPacienteRepositorio extends JpaRepository<ModelPaciente, String>{
    @Query("SELECT p FROM ModelPaciente p WHERE p.cpf = ?1")
    List<ModelPaciente> findByCpf(String cpf);
}
