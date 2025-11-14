package ufg.es.analisehemograma.model.AnaliseColetiva;

public class ResultadoColetivoDTO {
    private String nomeMunicipio;
    private Long contagemFumantes10a15;
    private Integer totalHabitantes10a15;

    public ResultadoColetivoDTO(String nomeMunicipio, Long contagemFumantes10a15, Integer totalHabitantes10a15) {
        this.nomeMunicipio = nomeMunicipio;
        this.contagemFumantes10a15 = contagemFumantes10a15;
        this.totalHabitantes10a15 = totalHabitantes10a15;
    }

    public String getNomeMunicipio() { return nomeMunicipio; }
    public Long getContagemFumantes10a15() { return contagemFumantes10a15; }
    public Integer getTotalHabitantes10a15() { return totalHabitantes10a15; }
}