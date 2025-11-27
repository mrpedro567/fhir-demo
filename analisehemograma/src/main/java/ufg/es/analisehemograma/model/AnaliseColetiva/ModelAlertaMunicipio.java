package ufg.es.analisehemograma.model.AnaliseColetiva;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import ufg.es.analisehemograma.model.Municipio.ModelMunicipio;

@Entity
@Table(name = "ALERTA_MUNICIPIO")
public class ModelAlertaMunicipio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MUNICIPIO_NOME")
    @JsonIgnoreProperties({"pacientes","populacao1015"})
    private ModelMunicipio municipio;

    @Column(name = "PERCENTUAL", nullable = false)
    private Double percentual;

    @Column(name = "CONTAGEM_FUMANTES")
    private Long contagemFumantes;

    @Column(name = "POPULACAO_10_15")
    private Integer populacao10a15;

    @Column(name = "DATA_CRIACAO", nullable = false)
    private Instant dataCriacao;

    public Long getId() {
        return id;
    }

    public ModelMunicipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(ModelMunicipio municipio) {
        this.municipio = municipio;
    }

    public Double getPercentual() {
        return percentual;
    }

    public void setPercentual(Double percentual) {
        this.percentual = percentual;
    }

    public Long getContagemFumantes10a15() {
        return contagemFumantes;
    }

    public void setContagemFumantes10a15(Long contagemFumantes) {
        this.contagemFumantes = contagemFumantes;
    }

    public Integer getPopulacao10a15() {
        return populacao10a15;
    }

    public void setPopulacao10a15(Integer populacao10a15) {
        this.populacao10a15 = populacao10a15;
    }

    public Instant getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Instant dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}