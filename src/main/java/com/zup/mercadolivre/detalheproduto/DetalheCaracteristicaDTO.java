package com.zup.mercadolivre.detalheproduto;

import com.zup.mercadolivre.model.CaracteristicaProduto;

public class DetalheCaracteristicaDTO {

    private String nome;
    private String descricao;

    public DetalheCaracteristicaDTO(CaracteristicaProduto caracteristicaProduto) {
        this.nome = caracteristicaProduto.getNome();
        this.descricao = caracteristicaProduto.getDescricao();
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
