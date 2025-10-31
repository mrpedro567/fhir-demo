package ufg.es.analisehemograma.model.modelHemograma;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import ufg.es.analisehemograma.model.modelPaciente.ModelPaciente;
import ufg.es.analisehemograma.model.modelPontoDeAtencao.ModelPontoDeAtencao;

@Entity
@Table(name = "HEMOGRAMA")
public class ModelHemograma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "DATA_ANALISE")
    private LocalDate dataColeta;

    @Column(name = "CONTAGEM_LEUCOCITOS")
    private int contagemLeucocitos;

    @Column(name = "NEUTROFILOS")
    private BigDecimal porcentagemNeutrofilos;

    @Column(name = "LINFOCITOS")
    private BigDecimal porcentagemLinfocitos;

    @Column(name = "MONOCITOS")
    private BigDecimal porcentagemMonocitos;

    @Column(name = "EOSINOFILOS")
    private BigDecimal porcentagemEosinofilos;

    @Column(name = "BASOFILOS")
    private BigDecimal porcentagemBasofilos;

    @Column(name = "CONTAGEM_ERITROCITOS")
    private BigDecimal contagemEritrocitos;

    @Column(name = "HEMOGLOBINA")
    private BigDecimal hemoglobina;

    @Column(name = "HEMATOCRITO")
    private BigDecimal hematocrito;

    @Column(name = "VOLUME_CORPUSCULAR_MEDIO")
    private BigDecimal vcm;

    @Column(name = "HEMOGLOBINA_CORPUSCULAR_MEDIA")
    private BigDecimal hcm;

    @Column(name = "CONC_HEMOGLOBINA_CORPUSCULAR_MEDIA")
    private BigDecimal chcm;

    @Column(name = "AMPLITUDE_DIST_ERITROCITARIA")
    private BigDecimal amplitudeDistribuicaoEritrocitaria;

    @Column(name = "VOLUME_PLAQUETARIO_MEDIO")
    private BigDecimal vpm;

    @Column(name = "CONTAGEM_PLAQUETAS")
    private int contagemPlaquetas;

    @ManyToOne
    @JoinColumn(name = "CPF_PACIENTE")
    private ModelPaciente paciente;

    @ManyToMany(mappedBy = "hemogramas")
    private Set<ModelPontoDeAtencao> pontosDeAtencao = new HashSet<>();

    public Long getId() {
        return id;
    }

    public LocalDate getDataColeta() {
        return dataColeta;
    }

    public void setDataColeta(LocalDate dataColeta) {
        this.dataColeta = dataColeta;
    }

    public int getContagemLeucocitos() {
        return contagemLeucocitos;
    }

    public void setContagemLeucocitos(int contagemLeucocitos) {
        this.contagemLeucocitos = contagemLeucocitos;
    }

    public BigDecimal getPorcentagemNeutrofilos() {
        return porcentagemNeutrofilos;
    }

    public void setPorcentagemNeutrofilos(BigDecimal porcentagemNeutrofilos) {
        this.porcentagemNeutrofilos = porcentagemNeutrofilos;
    }

    public BigDecimal getPorcentagemLinfocitos() {
        return porcentagemLinfocitos;
    }

    public void setPorcentagemLinfocitos(BigDecimal porcentagemLinfocitos) {
        this.porcentagemLinfocitos = porcentagemLinfocitos;
    }

    public BigDecimal getPorcentagemMonocitos() {
        return porcentagemMonocitos;
    }

    public void setPorcentagemMonocitos(BigDecimal porcentagemMonocitos) {
        this.porcentagemMonocitos = porcentagemMonocitos;
    }

    public BigDecimal getPorcentagemEosinofilos() {
        return porcentagemEosinofilos;
    }

    public void setPorcentagemEosinofilos(BigDecimal porcentagemEosinofilos) {
        this.porcentagemEosinofilos = porcentagemEosinofilos;
    }

    public BigDecimal getPorcentagemBasofilos() {
        return porcentagemBasofilos;
    }

    public void setPorcentagemBasofilos(BigDecimal porcentagemBasofilos) {
        this.porcentagemBasofilos = porcentagemBasofilos;
    }

    public BigDecimal getContagemEritrocitos() {
        return contagemEritrocitos;
    }

    public void setContagemEritrocitos(BigDecimal contagemEritrocitos) {
        this.contagemEritrocitos = contagemEritrocitos;
    }

    public BigDecimal getHemoglobina() {
        return hemoglobina;
    }

    public void setHemoglobina(BigDecimal hemoglobina) {
        this.hemoglobina = hemoglobina;
    }

    public BigDecimal getHematocrito() {
        return hematocrito;
    }

    public void setHematocrito(BigDecimal hematocrito) {
        this.hematocrito = hematocrito;
    }

    public BigDecimal getVcm() {
        return vcm;
    }

    public void setVcm(BigDecimal vcm) {
        this.vcm = vcm;
    }

    public BigDecimal getHcm() {
        return hcm;
    }

    public void setHcm(BigDecimal hcm) {
        this.hcm = hcm;
    }

    public BigDecimal getChcm() {
        return chcm;
    }

    public void setChcm(BigDecimal chcm) {
        this.chcm = chcm;
    }

    public BigDecimal getAmplitudeDistribuicaoEritrocitaria() {
        return amplitudeDistribuicaoEritrocitaria;
    }

    public void setAmplitudeDistribuicaoEritrocitaria(BigDecimal amplitudeDistribuicaoEritrocitaria) {
        this.amplitudeDistribuicaoEritrocitaria = amplitudeDistribuicaoEritrocitaria;
    }

    public BigDecimal getVpm() {
        return vpm;
    }

    public void setVpm(BigDecimal vpm) {
        this.vpm = vpm;
    }

    public Set<ModelPontoDeAtencao> getPontosDeAtencao() {
        return pontosDeAtencao;
    }

    // addPontosDeAtencao, método auxiliar para adicionar um ponto de atenção.
    // Retorno: void.
    // Complexidade: O(1), usando HashSet para busca constante.
    public void addPontosDeAtencao(ModelPontoDeAtencao pontoDeAtencao) {
        if (pontosDeAtencao == null)
            pontosDeAtencao = new HashSet<>();

        if (pontosDeAtencao.contains(pontoDeAtencao))
            return;

        pontosDeAtencao.add(pontoDeAtencao);
        pontoDeAtencao.getHemogramas().add(this);
    }

    public ModelPaciente getPaciente() {
        return paciente;
    }

    public int getContagemPlaquetas() {
        return contagemPlaquetas;
    }
}
