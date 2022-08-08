package com.zup.mercadolivre.adicionaropniao;

import com.zup.mercadolivre.model.OpniaoProduto;
import com.zup.mercadolivre.model.Produto;
import com.zup.mercadolivre.model.Usuario;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AdicionarOpniaoRequest {

    @Min(1)
    @Max(5)
    private int nota;
    @NotBlank
    private String titulo;
    @NotBlank
    @Size(max = 500)
    private String descricao;

    public AdicionarOpniaoRequest(int nota, String titulo, String descricao) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public OpniaoProduto toModel(Produto produto, Usuario consumidor) {
        return new OpniaoProduto(nota, titulo, descricao, produto, consumidor);
    }
}
