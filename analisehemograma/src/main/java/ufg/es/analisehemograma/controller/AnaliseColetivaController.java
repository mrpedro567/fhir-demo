package ufg.es.analisehemograma.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ufg.es.analisehemograma.model.AnaliseColetiva.AlertaMunicipioDTO;
import ufg.es.analisehemograma.model.AnaliseColetiva.ModelAlertaMunicipio;
import ufg.es.analisehemograma.repository.ModelAlertaMunicipioRepositorio;
import ufg.es.analisehemograma.servico.IServicoAnaliseColetiva;

@RestController
public class AnaliseColetivaController {

    private final IServicoAnaliseColetiva servicoAnaliseColetiva;
    private final ModelAlertaMunicipioRepositorio alertaRepositorio;

    public AnaliseColetivaController(IServicoAnaliseColetiva servicoAnaliseColetiva,
                                     ModelAlertaMunicipioRepositorio alertaRepositorio) {
        this.servicoAnaliseColetiva = servicoAnaliseColetiva;
        this.alertaRepositorio = alertaRepositorio;
    }

    @GetMapping("/analise-coletiva/alertas")
    public ResponseEntity<List<AlertaMunicipioDTO>> getAlertas(
            @RequestParam(name = "limiarPercentual", required = false, defaultValue = "5.0") double limiarPercentual) {
        if (limiarPercentual < 0.0) {
            return ResponseEntity.badRequest().build();
        }
        List<AlertaMunicipioDTO> alertas = servicoAnaliseColetiva.gerarAlertas(limiarPercentual);
        return ResponseEntity.ok(alertas);
    }

    @GetMapping("/analise-coletiva/alertas/mais-recentes")
    public ResponseEntity<List<ModelAlertaMunicipio>> getAlertasMaisRecentes() {
        List<ModelAlertaMunicipio> alertas = alertaRepositorio.findAllLatestPerMunicipio();
        return ResponseEntity.ok(alertas);
    }
}