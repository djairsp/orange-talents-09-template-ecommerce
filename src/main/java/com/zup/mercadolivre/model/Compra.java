package com.zup.mercadolivre.model;

import com.zup.mercadolivre.negocio.GatewayPagamento;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Valid
    @ManyToOne
    private Produto produtoParaCompra;
    @Positive
    private int quantidade;
    @ManyToOne
    @NotNull
    @Valid
    private Usuario comprador;
    @NotNull
    @Valid
    @Enumerated
    private GatewayPagamento gatewayPagamento;

    @Deprecated
    public Compra() {   }

    public Compra(@NotNull @Valid Produto produtoParaCompra, @Positive int quantidade,
                  @NotNull @Valid Usuario usuarioLogado, GatewayPagamento gatewayPagamento) {
        this.produtoParaCompra = produtoParaCompra;
        this.quantidade = quantidade;
        this.comprador = usuarioLogado;
        this.gatewayPagamento = gatewayPagamento;

    }

    @Override
    public String toString() {
        return "Compra{" +
                "produtoParaCompra=" + produtoParaCompra +
                ", quantidade=" + quantidade +
                ", comprador=" + comprador +
                '}';
    }

    public Long getId() {
        return id;
    }
}
