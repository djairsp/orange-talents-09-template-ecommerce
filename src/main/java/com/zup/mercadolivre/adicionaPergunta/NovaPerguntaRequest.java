package com.zup.mercadolivre.adicionaPergunta;

import com.zup.mercadolivre.model.PerguntaProduto;
import com.zup.mercadolivre.model.Produto;
import com.zup.mercadolivre.model.Usuario;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class NovaPerguntaRequest {

    @NotBlank
    private String titulo;
    @NotBlank
    @Size(max = 500)
    private String pergunta;

    public NovaPerguntaRequest(String titulo, String pergunta) {
        this.titulo = titulo;
        this.pergunta = pergunta;
    }

    public PerguntaProduto toModel(Produto produto, Usuario consumidor) {
        return new PerguntaProduto(titulo, pergunta, produto, consumidor);
    }
}
