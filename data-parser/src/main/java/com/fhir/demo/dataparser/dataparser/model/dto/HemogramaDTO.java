package com.fhir.demo.dataparser.dataparser.model.dto;

import java.math.BigDecimal;


public class HemogramaDTO {
    // 	6690-2
    private BigDecimal contagemLeucocitos;

    /**
     * 11	Metamielócitos	739-3	/uL
     * 12	Bastonetes	763-3	/uL
     * 13	Segmentados	768-2	/uL
     */
    private BigDecimal porcentagemNeutrofilos;

    private BigDecimal metamielocito;
    private BigDecimal bastonete;
    private BigDecimal segmentado;

    // 731-0
    private BigDecimal porcentagemLinfocitos;

    // 742-7
    private BigDecimal porcentagemMonocitos;

    // 711-2
    private BigDecimal porcentagemEosinofilos;

    // 704-7    
    private BigDecimal porcentagemBasofilos;

    // 	789-8 
    private BigDecimal contagemEritrocitos;

    // 718-7
    private BigDecimal hemoglobina;

    // 4544-3    
    private BigDecimal hematocrito;

    // 787-2
    private BigDecimal vcm;

    // 785-6
    private BigDecimal hcm;

    // 786-4
    private BigDecimal chcm;

    // RDW - 788-0
    private BigDecimal amplitudeDistribuicaoEritrocitaria;

    // 32623-1	
    private BigDecimal vpm;

    // 777-3
    private int contagemPlaquetas;

    /**
     * @return the contagemLeucocitos
     */
    public BigDecimal getContagemLeucocitos() {
        return contagemLeucocitos;
    }

    /**
     * @param contagemLeucocitos the contagemLeucocitos to set
     */
    public void setContagemLeucocitos(BigDecimal contagemLeucocitos) {
        this.contagemLeucocitos = contagemLeucocitos;
    }

    /**
     * @return the porcentagemNeutrofilos
     */
    public BigDecimal getPorcentagemNeutrofilos() {
        return porcentagemNeutrofilos;
    }

    /**
     * @param porcentagemNeutrofilos the porcentagemNeutrofilos to set
     */
    public void setPorcentagemNeutrofilos(BigDecimal porcentagemNeutrofilos) {
        this.porcentagemNeutrofilos = porcentagemNeutrofilos;
    }

    /**
     * @return the metamielocito
     */
    public BigDecimal getMetamielocito() {
        return metamielocito;
    }

    /**
     * @param metamielocito the metamielocito to set
     */
    public void setMetamielocito(BigDecimal metamielocito) {
        this.metamielocito = metamielocito;
    }

    /**
     * @return the bastonete
     */
    public BigDecimal getBastonete() {
        return bastonete;
    }

    /**
     * @param bastonete the bastonete to set
     */
    public void setBastonete(BigDecimal bastonete) {
        this.bastonete = bastonete;
    }

    /**
     * @return the segmentado
     */
    public BigDecimal getSegmentado() {
        return segmentado;
    }

    /**
     * @param segmentado the segmentado to set
     */
    public void setSegmentado(BigDecimal segmentado) {
        this.segmentado = segmentado;
    }

    /**
     * @return the porcentagemLinfocitos
     */
    public BigDecimal getPorcentagemLinfocitos() {
        return porcentagemLinfocitos;
    }

    /**
     * @param porcentagemLinfocitos the porcentagemLinfocitos to set
     */
    public void setPorcentagemLinfocitos(BigDecimal porcentagemLinfocitos) {
        this.porcentagemLinfocitos = porcentagemLinfocitos;
    }

    /**
     * @return the porcentagemMonocitos
     */
    public BigDecimal getPorcentagemMonocitos() {
        return porcentagemMonocitos;
    }

    /**
     * @param porcentagemMonocitos the porcentagemMonocitos to set
     */
    public void setPorcentagemMonocitos(BigDecimal porcentagemMonocitos) {
        this.porcentagemMonocitos = porcentagemMonocitos;
    }

    /**
     * @return the porcentagemEosinofilos
     */
    public BigDecimal getPorcentagemEosinofilos() {
        return porcentagemEosinofilos;
    }

    /**
     * @param porcentagemEosinofilos the porcentagemEosinofilos to set
     */
    public void setPorcentagemEosinofilos(BigDecimal porcentagemEosinofilos) {
        this.porcentagemEosinofilos = porcentagemEosinofilos;
    }

    /**
     * @return the porcentagemBasofilos
     */
    public BigDecimal getPorcentagemBasofilos() {
        return porcentagemBasofilos;
    }

    /**
     * @param porcentagemBasofilos the porcentagemBasofilos to set
     */
    public void setPorcentagemBasofilos(BigDecimal porcentagemBasofilos) {
        this.porcentagemBasofilos = porcentagemBasofilos;
    }

    /**
     * @return the contagemEritrocitos
     */
    public BigDecimal getContagemEritrocitos() {
        return contagemEritrocitos;
    }

    /**
     * @param contagemEritrocitos the contagemEritrocitos to set
     */
    public void setContagemEritrocitos(BigDecimal contagemEritrocitos) {
        this.contagemEritrocitos = contagemEritrocitos;
    }

    /**
     * @return the hemoglobina
     */
    public BigDecimal getHemoglobina() {
        return hemoglobina;
    }

    /**
     * @param hemoglobina the hemoglobina to set
     */
    public void setHemoglobina(BigDecimal hemoglobina) {
        this.hemoglobina = hemoglobina;
    }

    /**
     * @return the hematocrito
     */
    public BigDecimal getHematocrito() {
        return hematocrito;
    }

    /**
     * @param hematocrito the hematocrito to set
     */
    public void setHematocrito(BigDecimal hematocrito) {
        this.hematocrito = hematocrito;
    }

    /**
     * @return the vcm
     */
    public BigDecimal getVcm() {
        return vcm;
    }

    /**
     * @param vcm the vcm to set
     */
    public void setVcm(BigDecimal vcm) {
        this.vcm = vcm;
    }

    /**
     * @return the hcm
     */
    public BigDecimal getHcm() {
        return hcm;
    }

    /**
     * @param hcm the hcm to set
     */
    public void setHcm(BigDecimal hcm) {
        this.hcm = hcm;
    }

    /**
     * @return the chcm
     */
    public BigDecimal getChcm() {
        return chcm;
    }

    /**
     * @param chcm the chcm to set
     */
    public void setChcm(BigDecimal chcm) {
        this.chcm = chcm;
    }

    /**
     * @return the amplitudeDistribuicaoEritrocitaria
     */
    public BigDecimal getAmplitudeDistribuicaoEritrocitaria() {
        return amplitudeDistribuicaoEritrocitaria;
    }

    /**
     * @param amplitudeDistribuicaoEritrocitaria the amplitudeDistribuicaoEritrocitaria to set
     */
    public void setAmplitudeDistribuicaoEritrocitaria(BigDecimal amplitudeDistribuicaoEritrocitaria) {
        this.amplitudeDistribuicaoEritrocitaria = amplitudeDistribuicaoEritrocitaria;
    }

    /**
     * @return the vpm
     */
    public BigDecimal getVpm() {
        return vpm;
    }

    /**
     * @param vpm the vpm to set
     */
    public void setVpm(BigDecimal vpm) {
        this.vpm = vpm;
    }

    /**
     * @return the contagemPlaquetas
     */
    public int getContagemPlaquetas() {
        return contagemPlaquetas;
    }

    /**
     * @param contagemPlaquetas the contagemPlaquetas to set
     */
    public void setContagemPlaquetas(int contagemPlaquetas) {
        this.contagemPlaquetas = contagemPlaquetas;
    }

    public HemogramaDTO() {}

    
} 
