package com.fhir.demo.dataparser.dataparser.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import com.fhir.demo.dataparser.dataparser.model.dto.HemogramaDTO;
import com.ibm.icu.text.Normalizer.Mode;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "HEMOGRAMA")
public class ModelHemograma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    // Data
    @Column(name = "DATA_ANALISE")
    private LocalDate dataColeta;

    // 	6690-2
    @Column(name = "CONTAGEM_LEUCOCITOS")
    private BigDecimal contagemLeucocitos;

    /**
     * 11	Metamielócitos	739-3	/uL
     * 12	Bastonetes	763-3	/uL
     * 13	Segmentados	768-2	/uL
     */
    @Column(name = "NEUTROFILOS")
    private BigDecimal porcentagemNeutrofilos;

    // 731-0
    @Column(name = "LINFOCITOS")
    private BigDecimal porcentagemLinfocitos;

    // 742-7
    @Column(name = "MONOCITOS")
    private BigDecimal porcentagemMonocitos;

    // 711-2
    @Column(name = "EOSINOFILOS")
    private BigDecimal porcentagemEosinofilos;

    // 704-7    
    @Column(name = "BASOFILOS")
    private BigDecimal porcentagemBasofilos;

    // 	789-8 
    @Column(name = "CONTAGEM_ERITROCITOS")
    private BigDecimal contagemEritrocitos;

    // 718-7
    @Column(name = "HEMOGLOBINA")
    private BigDecimal hemoglobina;

    // 4544-3    
    @Column(name = "HEMATOCRITO")
    private BigDecimal hematocrito;

    // 787-2
    @Column(name = "VOLUME_CORPUSCULAR_MEDIO")
    private BigDecimal vcm;

    // 785-6
    @Column(name = "HEMOGLOBINA_CORPUSCULAR_MEDIA")
    private BigDecimal hcm;

    // 786-4
    @Column(name = "CONC_HEMOGLOBINA_CORPUSCULAR_MEDIA")
    private BigDecimal chcm;

    // RDW - 788-0
    @Column(name = "AMPLITUDE_DIST_ERITROCITARIA")
    private BigDecimal amplitudeDistribuicaoEritrocitaria;

    // 32623-1	
    @Column(name = "VOLUME_PLAQUETARIO_MEDIO")
    private BigDecimal vpm;

    // 777-3
    @Column(name = "CONTAGEM_PLAQUETAS")
    private int contagemPlaquetas;

    @ManyToOne
    @JoinColumn(name = "CPF_PACIENTE")
    private ModelPaciente paciente;


    public ModelHemograma() {}

    public ModelPaciente getPaciente() {
        return paciente;
    }

    public void setPaciente(ModelPaciente paciente) {
        this.paciente = paciente;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDataColeta() {
        return dataColeta;
    }

    public void setDataColeta(LocalDate dataColeta) {
        this.dataColeta = dataColeta;
    }

    public BigDecimal getContagemLeucocitos() {
        return contagemLeucocitos;
    }

    public void setContagemLeucocitos(BigDecimal contagemLeucocitos) {
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

    // public Set<ModelPontoDeAtencao> getPontosDeAtencao() {
    //     return pontosDeAtencao;
    // }

    // // addPontosDeAtencao, método auxiliar para adicionar um ponto de atenção.
    // // Retorno: void.
    // // Complexidade: O(1), usando HashSet para busca constante.
    // public void addPontosDeAtencao(ModelPontoDeAtencao pontoDeAtencao) {
    //     if (pontosDeAtencao == null)
    //         pontosDeAtencao = new HashSet<>();

    //     if (pontosDeAtencao.contains(pontoDeAtencao))
    //         return;

    //     pontosDeAtencao.add(pontoDeAtencao);
    //     pontoDeAtencao.getHemogramas().add(this);
    // }

    // public ModelPaciente getPaciente() {
    //     return paciente;
    // }

    public int getContagemPlaquetas() {
        return contagemPlaquetas;
    }

    public ModelHemograma(HemogramaDTO dto){
        // this.dataColeta = dto.getDataColeta();
        this.contagemLeucocitos = dto.getContagemLeucocitos();
        this.porcentagemLinfocitos = dto.getPorcentagemLinfocitos();
        this.porcentagemMonocitos = dto.getPorcentagemMonocitos();
        this.porcentagemEosinofilos = dto.getPorcentagemEosinofilos();
        this.porcentagemBasofilos = dto.getPorcentagemBasofilos();
        this.contagemEritrocitos = dto.getContagemEritrocitos();
        this.hemoglobina = dto.getHemoglobina();
        this.hematocrito = dto.getHematocrito();
        this.vcm = dto.getVcm();
        this.hcm = dto.getHcm();
        this.chcm = dto.getChcm();
        this.amplitudeDistribuicaoEritrocitaria = dto.getAmplitudeDistribuicaoEritrocitaria();
        this.vpm = dto.getVpm();
        this.contagemPlaquetas = dto.getContagemPlaquetas();
        if(this.contagemLeucocitos != null && this.contagemLeucocitos.compareTo(BigDecimal.ZERO) != 0)
            this.porcentagemNeutrofilos = dto.getMetamielocito().add(dto.getBastonete()).add(dto.getSegmentado()).divide(this.contagemLeucocitos, 4, RoundingMode.HALF_UP);
    }

    public void fromDto(HemogramaDTO dto){
        this.contagemLeucocitos = dto.getContagemLeucocitos();
        this.porcentagemLinfocitos = dto.getPorcentagemLinfocitos();
        this.porcentagemMonocitos = dto.getPorcentagemMonocitos();
        this.porcentagemEosinofilos = dto.getPorcentagemEosinofilos();
        this.porcentagemBasofilos = dto.getPorcentagemBasofilos();
        this.contagemEritrocitos = dto.getContagemEritrocitos();
        this.hemoglobina = dto.getHemoglobina();
        this.hematocrito = dto.getHematocrito();
        this.vcm = dto.getVcm();
        this.hcm = dto.getHcm();
        this.chcm = dto.getChcm();
        this.amplitudeDistribuicaoEritrocitaria = dto.getAmplitudeDistribuicaoEritrocitaria();
        this.vpm = dto.getVpm();
        this.contagemPlaquetas = dto.getContagemPlaquetas();
        if(this.contagemLeucocitos != null && this.contagemLeucocitos.compareTo(BigDecimal.ZERO) != 0)
            this.porcentagemNeutrofilos = dto.getMetamielocito().add(dto.getBastonete()).add(dto.getSegmentado()).divide(this.contagemLeucocitos, 4, RoundingMode.HALF_UP);
    }
}
