package com.fhir.demo.dataparser.dataparser.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "PACIENTE")
public class ModelPaciente {
    @Id
    @Column(name = "CPF")
    private String cpf;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "SEXO")
    private String sexo;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ModelHemograma> hemogramas;

    

    /**
     * @param cpf
     * @param nome
     * @param sexo
     */
    public ModelPaciente(String cpf, String nome, String sexo) {
        this.cpf = cpf;
        this.nome = nome;
        this.sexo = sexo;
    }

    public ModelPaciente() {}   

    /**
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @param cpf the cpf to set
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the sexo
     */
    public String getSexo() {
        return sexo;
    }

    /**
     * @param sexo the sexo to set
     */
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    /**
     * @return the hemogramas
     */
    public List<ModelHemograma> getHemogramas() {
        return hemogramas;
    }

    /**
     * @param hemogramas the hemogramas to set
     */
    public void setHemogramas(List<ModelHemograma> hemogramas) {
        this.hemogramas = hemogramas;
    }

    
}
