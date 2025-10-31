package ufg.es.analisehemograma.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ufg.es.analisehemograma.servico.IServicoAnaliseHemograma;
import ufg.es.analisehemograma.servico.ServicoAnaliseHemograma;

@RestController
public class ControllerAnaliseIndividual {

    private final IServicoAnaliseHemograma servico;

    public ControllerAnaliseIndividual(ServicoAnaliseHemograma servicoAnaliseHemograma) {
        this.servico = servicoAnaliseHemograma;
    }

    @GetMapping("/AnaliseIndividual")
    public String CalculoAnaliseIndivual() {
        return servico.analiseIndividualHemograma();
    }
}