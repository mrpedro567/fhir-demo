package ufg.es.analisehemograma.model.Municipio;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import ufg.es.analisehemograma.model.Paciente.ModelPaciente;

@Entity
@Table(name = "MUNICIPIO")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "nome")
public class ModelMunicipio {

    @Id
    @Column(name = "NOME", nullable = false)
    private String nome;

    @Column(name = "POPULACAO_10_15")
    private Integer populacao1015;

    @OneToMany(mappedBy = "municipio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ModelPaciente> pacientes = new ArrayList<>();

    public ModelMunicipio() {
    }

    public ModelMunicipio(String nome, Integer populacao1015) {
        this.nome = nome;
        this.populacao1015 = populacao1015;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getPopulacao1015() {
        return populacao1015;
    }

    public void setPopulacao1015(Integer populacao1015) {
        this.populacao1015 = populacao1015;
    }

    public List<ModelPaciente> getPacientes() {
        return pacientes;
    }

    public void setPacientes(List<ModelPaciente> pacientes) {
        this.pacientes = pacientes;
    }

    public void addPaciente(ModelPaciente paciente) {
        this.pacientes.add(paciente);
        paciente.setMunicipio(this);
    }

    public void removePaciente(ModelPaciente paciente) {
        this.pacientes.remove(paciente);
        paciente.setMunicipio(null);
    }

    @Override
    public String toString() {
        return "ModelMunicipio [nome=" + nome + ", populacao1015=" + populacao1015 + "]";
    }
}