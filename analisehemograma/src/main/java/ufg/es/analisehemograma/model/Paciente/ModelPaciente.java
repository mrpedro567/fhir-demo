package ufg.es.analisehemograma.model.Paciente;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import ufg.es.analisehemograma.model.Hemograma.ModelHemograma;
import ufg.es.analisehemograma.model.Municipio.ModelMunicipio;
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

    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_NASCIMENTO")
    private Date dataNascimento;

    @ManyToOne
    @JoinColumn(name = "MUNICIPIO_NOME")
    private ModelMunicipio municipio;

    @Column(name = "POSSIVEL_FUMANTE")
    private Boolean possivelFumante = false;

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

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public ModelMunicipio getMunicipio() {
        return municipio;
    }
    
    public void setMunicipio(ModelMunicipio municipio) {
        this.municipio = municipio;
    }

    public Boolean getPossivelFumante() {
        return possivelFumante;
    }
    
    public void setPossivelFumante(Boolean possivelFumante) {
        this.possivelFumante = possivelFumante;
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