package ufg.es.analisehemograma.model.PontoDeAtencao;

import java.time.LocalDate;
import java.util.ArrayList;

import ufg.es.analisehemograma.model.Hemograma.DTOPHemograma;

public class DTOPontoDeAtencao {

    private String descricao;
    private String hipotese;
    private LocalDate dataCriacao;
    private String cpfPaciente;
    private String nomePaciente;
    private ArrayList<DTOPHemograma> hemogramas;

    public DTOPontoDeAtencao(ModelPontoDeAtencao pontoDeAtencao) {
        this.descricao = pontoDeAtencao.getDescricao();
        this.hipotese = pontoDeAtencao.getHipotese();
        this.dataCriacao = pontoDeAtencao.getDataCriacao();

        this.cpfPaciente = pontoDeAtencao.getPaciente().getCpf();
        this.nomePaciente = pontoDeAtencao.getPaciente().getNome();

        this.hemogramas = new ArrayList<DTOPHemograma>();
        for (var hemograma : pontoDeAtencao.getHemogramas()) {
            this.hemogramas.add(new DTOPHemograma(hemograma));
        }
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

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getCpfPaciente() {
        return cpfPaciente;
    }

    public void setCpfPaciente(String cpfPaciente) {
        this.cpfPaciente = cpfPaciente;
    }

    public String getNomePaciente() {
        return nomePaciente;
    }

    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }

    public ArrayList<DTOPHemograma> getHemogramas() {
        return hemogramas;
    }

    public void setHemogramas(ArrayList<DTOPHemograma> hemogramas) {
        this.hemogramas = hemogramas;
    }

}
