package ufg.es.analisehemograma.model.AnaliseColetiva;

public class AlertaMunicipioDTO {
    private String nomeMunicipio;
    private double percentual; // 0..100

    public AlertaMunicipioDTO(String nomeMunicipio, double percentual) {
        this.nomeMunicipio = nomeMunicipio;
        this.percentual = percentual;
    }

    public String getNomeMunicipio() { return nomeMunicipio; }
    public double getPercentual() { return percentual; }
}