package ufg.es.analisehemograma.model.Paciente;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import ufg.es.analisehemograma.model.Hemograma.ModelHemograma;
import ufg.es.analisehemograma.model.PontoDeAtencao.ModelPontoDeAtencao;

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
    private List<ModelPontoDeAtencao> pontosDeAtencao;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ModelHemograma> hemogramas;

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public List<ModelPontoDeAtencao> getPontosDeAtencao() {
        return pontosDeAtencao;
    }

    public List<ModelHemograma> getHemogramas() {
        return hemogramas;
    }

    public void addPontosDeAtencao(ModelPontoDeAtencao pontoDeAtencao) {
        this.pontosDeAtencao.add(pontoDeAtencao);
        pontoDeAtencao.setPaciente(this);
    }
}