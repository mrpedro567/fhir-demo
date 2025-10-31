package ufg.es.analisehemograma.model.PontoDeAtencao;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import ufg.es.analisehemograma.model.Hemograma.ModelHemograma;
import ufg.es.analisehemograma.model.Paciente.ModelPaciente;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "PONTOS_DE_ATENCAO")
public class ModelPontoDeAtencao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "DESCRICAO", columnDefinition = "TEXT")
    private String descricao = "";

    @Column(name = "HIPOTESE", columnDefinition = "TEXT")
    private String hipotese = "";

    @Column(name = "DATA_CRIACAO")
    private LocalDate dataCriacao;

    @ManyToOne
    @JoinColumn(name = "CPF_PACIENTE")
    private ModelPaciente paciente;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "PATENCAO_HEMOGRAMA", joinColumns = @JoinColumn(name = "ID_PONTO_ATENCAO"), inverseJoinColumns = @JoinColumn(name = "ID_HEMOGRAMA"))
    private List<ModelHemograma> hemogramas;

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getHipotese() {
        return hipotese;
    }

    public void setHipotese(String hipotese) {
        this.hipotese = hipotese;
    }

    public List<ModelHemograma> getHemogramas() {
        return hemogramas;
    }

    // addHemogramas, Adiciona um hemograma à lista de hemogramas associados a este
    // ponto de atenção e também adiciona este ponto de atenção à lista de pontos de
    // atenção para manter a consistência bidirecional.
    // Retorno: void;
    // Complexidade Teórica: O(n), n sendo a quantidade de hemogramas no ponto de
    // atencao.
    // Complexidade Prática: O(1), pois o número de hemogramas num ponto de atencao
    // é uma constante, inferior a 5.
    public void addHemogramas(ModelHemograma hemograma) {
        if (hemogramas == null)
            hemogramas = new java.util.ArrayList<>();

        if (hemogramas.contains(hemograma))
            return;

        hemogramas.add(hemograma);
        hemograma.getPontosDeAtencao().add(this);
    }

    public ModelPaciente getPaciente() {
        return paciente;
    }

    public ModelPaciente setPaciente(ModelPaciente paciente) {
        return this.paciente = paciente;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

}