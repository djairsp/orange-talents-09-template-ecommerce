package com.zup.mercadolivre.outrosistema;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class OutroSistemaController {

    @PostMapping("/notas-fiscais")
    public void criarNota(@Valid @RequestBody NotaFiscalRequest request){
        System.out.println("Criando nota para " + request.toString());
    }

    @PostMapping("/ranking")
    public void criarRanking(@Valid @RequestBody RankingRequest request){
        System.out.println("Criando ranking " + request.toString());
    }
}
