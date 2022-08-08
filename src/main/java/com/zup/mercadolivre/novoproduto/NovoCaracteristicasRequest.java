package com.zup.mercadolivre.novoproduto;

import com.zup.mercadolivre.model.CaracteristicaProduto;
import com.zup.mercadolivre.model.Produto;

import javax.validation.constraints.NotBlank;

public class NovoCaracteristicasRequest {

    @NotBlank
    private String nome;
    @NotBlank
    private String descricao;

    public NovoCaracteristicasRequest() {
    }

    public NovoCaracteristicasRequest(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "NovoCaracteristicasRequest{" +
                "nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public CaracteristicaProduto toModel(Produto produto) {
        return new CaracteristicaProduto(nome, descricao, produto);
    }
}
