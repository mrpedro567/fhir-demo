package ufg.es.analisehemograma.model.Hemograma;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DTOPHemograma {

    private LocalDate dataColeta;
    private int contagemLeucocitos;
    private BigDecimal porcentagemNeutrofilos;
    private BigDecimal porcentagemLinfocitos;
    private BigDecimal porcentagemMonocitos;
    private BigDecimal porcentagemEosinofilos;
    private BigDecimal porcentagemBasofilos;
    private BigDecimal contagemEritrocitos;
    private BigDecimal hemoglobina;
    private BigDecimal hematocrito;
    private BigDecimal vcm;
    private BigDecimal hcm;
    private BigDecimal chcm;
    private BigDecimal amplitudeDistribuicaoEritrocitaria;
    private BigDecimal vpm;
    private int contagemPlaquetas;

    public DTOPHemograma(ModelHemograma hemograma) {
        dataColeta = hemograma.getDataColeta();
        contagemLeucocitos = hemograma.getContagemLeucocitos();
        porcentagemNeutrofilos = hemograma.getPorcentagemNeutrofilos();
        porcentagemLinfocitos = hemograma.getPorcentagemLinfocitos();
        porcentagemMonocitos = hemograma.getPorcentagemMonocitos();
        porcentagemEosinofilos = hemograma.getPorcentagemEosinofilos();
        porcentagemBasofilos = hemograma.getPorcentagemBasofilos();
        contagemEritrocitos = hemograma.getContagemEritrocitos();
        hemoglobina = hemograma.getHemoglobina();
        hematocrito = hemograma.getHematocrito();
        vcm = hemograma.getVcm();
        hcm = hemograma.getHcm();
        chcm = hemograma.getChcm();
        amplitudeDistribuicaoEritrocitaria = hemograma.getAmplitudeDistribuicaoEritrocitaria();
        contagemPlaquetas = hemograma.getContagemPlaquetas();
        vpm = hemograma.getVpm();
    }

    public LocalDate getDataColeta() {
        return dataColeta;
    }

    public int getContagemLeucocitos() {
        return contagemLeucocitos;
    }

    public BigDecimal getPorcentagemNeutrofilos() {
        return porcentagemNeutrofilos;
    }

    public BigDecimal getPorcentagemLinfocitos() {
        return porcentagemLinfocitos;
    }

    public BigDecimal getPorcentagemMonocitos() {
        return porcentagemMonocitos;
    }

    public BigDecimal getPorcentagemEosinofilos() {
        return porcentagemEosinofilos;
    }

    public BigDecimal getPorcentagemBasofilos() {
        return porcentagemBasofilos;
    }

    public BigDecimal getContagemEritrocitos() {
        return contagemEritrocitos;
    }

    public BigDecimal getHemoglobina() {
        return hemoglobina;
    }

    public BigDecimal getHematocrito() {
        return hematocrito;
    }

    public BigDecimal getVcm() {
        return vcm;
    }

    public BigDecimal getHcm() {
        return hcm;
    }

    public BigDecimal getChcm() {
        return chcm;
    }

    public BigDecimal getAmplitudeDistribuicaoEritrocitaria() {
        return amplitudeDistribuicaoEritrocitaria;
    }

    public BigDecimal getVpm() {
        return vpm;
    }

    public int getContagemPlaquetas() {
        return contagemPlaquetas;
    }

    public void setDataColeta(LocalDate dataColeta) {
        this.dataColeta = dataColeta;
    }

    public void setContagemLeucocitos(int contagemLeucocitos) {
        this.contagemLeucocitos = contagemLeucocitos;
    }

    public void setPorcentagemNeutrofilos(BigDecimal porcentagemNeutrofilos) {
        this.porcentagemNeutrofilos = porcentagemNeutrofilos;
    }

    public void setPorcentagemLinfocitos(BigDecimal porcentagemLinfocitos) {
        this.porcentagemLinfocitos = porcentagemLinfocitos;
    }

    public void setPorcentagemMonocitos(BigDecimal porcentagemMonocitos) {
        this.porcentagemMonocitos = porcentagemMonocitos;
    }

    public void setPorcentagemEosinofilos(BigDecimal porcentagemEosinofilos) {
        this.porcentagemEosinofilos = porcentagemEosinofilos;
    }

    public void setPorcentagemBasofilos(BigDecimal porcentagemBasofilos) {
        this.porcentagemBasofilos = porcentagemBasofilos;
    }

    public void setContagemEritrocitos(BigDecimal contagemEritrocitos) {
        this.contagemEritrocitos = contagemEritrocitos;
    }

    public void setHemoglobina(BigDecimal hemoglobina) {
        this.hemoglobina = hemoglobina;
    }

    public void setHematocrito(BigDecimal hematocrito) {
        this.hematocrito = hematocrito;
    }

    public void setVcm(BigDecimal vcm) {
        this.vcm = vcm;
    }

    public void setHcm(BigDecimal hcm) {
        this.hcm = hcm;
    }

    public void setChcm(BigDecimal chcm) {
        this.chcm = chcm;
    }

    public void setAmplitudeDistribuicaoEritrocitaria(BigDecimal amplitudeDistribuicaoEritrocitaria) {
        this.amplitudeDistribuicaoEritrocitaria = amplitudeDistribuicaoEritrocitaria;
    }

    public void setVpm(BigDecimal vpm) {
        this.vpm = vpm;
    }

    public void setContagemPlaquetas(int contagemPlaquetas) {
        this.contagemPlaquetas = contagemPlaquetas;
    }

}
