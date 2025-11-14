package ufg.es.analisehemograma.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ufg.es.analisehemograma.model.AnaliseColetiva.AlertaMunicipioDTO;
import ufg.es.analisehemograma.servico.IServicoAnaliseColetiva;

@RestController
@CrossOrigin(origins = "*") // Ajuste para suas políticas de CORS (em produção não use "*")
public class AnaliseColetivaController {

    private final IServicoAnaliseColetiva servicoAnaliseColetiva;

    public AnaliseColetivaController(IServicoAnaliseColetiva servicoAnaliseColetiva) {
        this.servicoAnaliseColetiva = servicoAnaliseColetiva;
    }

    /**
     * Retorna a lista de municípios cujo percentual de "possivel fumante" na faixa
     * de 10-15 anos excede o limiar fornecido.
     *
     * Ex.: GET /analise-coletiva/alertas?limiarPercentual=5.0
     *
     * @param limiarPercentual percentual em 0..100 (padrão 5.0)
     * @return 200 OK + lista de alertas (pode ser vazia)
     */
    @GetMapping("/analise-coletiva/alertas")
    public ResponseEntity<List<AlertaMunicipioDTO>> getAlertas(
            @RequestParam(name = "limiarPercentual", required = false, defaultValue = "5.0") double limiarPercentual) {
        if (limiarPercentual < 0.0) {
            return ResponseEntity.badRequest().build();
        }

        List<AlertaMunicipioDTO> alertas = servicoAnaliseColetiva.gerarAlertas(limiarPercentual);
        return ResponseEntity.ok(alertas);
    }
}
