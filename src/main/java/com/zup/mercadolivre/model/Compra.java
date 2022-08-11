package com.zup.mercadolivre.model;

import com.zup.mercadolivre.negocio.GatewayPagamento;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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
    private Usuario donoProduto;
    @NotNull
    @Valid
    @Enumerated
    private GatewayPagamento gatewayPagamento;
    @OneToMany(mappedBy = "compra", cascade = CascadeType.MERGE)
    private Set<Transacao> transacoes = new HashSet<>();

    @Deprecated
    public Compra() {   }

    public Compra(@NotNull @Valid Produto produtoParaCompra, @Positive int quantidade,
                  @NotNull @Valid Usuario usuarioLogado, GatewayPagamento gatewayPagamento) {
        this.produtoParaCompra = produtoParaCompra;
        this.quantidade = quantidade;
        this.donoProduto = usuarioLogado;
        this.gatewayPagamento = gatewayPagamento;

    }

    @Override
    public String toString() {
        return "Compra{" +
                "id=" + id +
                ", quantidade=" + quantidade +
                ", comprador=" + donoProduto +
                ", gatewayPagamento=" + gatewayPagamento +
                ", transacoes=" + transacoes +
                '}';
    }

    public Long getId() {
        return id;
    }

    public Usuario getDonoProduto() {
        return donoProduto;
    }

    public void adicionaTransacao(RetornoGatewayPagamento request) {
        Transacao novaTransacao = request.toTransacao(this);
        Assert.isTrue(!this.transacoes.contains(novaTransacao), "Já existe uma transacao igual a essa!");
        Assert.isTrue(transacoesConcluidasComSucesso().isEmpty(), "Essa compra já foi concluida com Sucesso!");
        this.transacoes.add(novaTransacao);
    }

    private Set<Transacao> transacoesConcluidasComSucesso(){
        Set<Transacao> transacoesConcluidaComSucesso =  this.transacoes.stream()
                .filter(Transacao::concluidaComSucesso)
                .collect(Collectors.toSet());

        Assert.isTrue(transacoesConcluidaComSucesso.size() <= 1, "Existe mais de uma transacao para a compra!");
        return transacoesConcluidaComSucesso;
    }

    public boolean processadoComSucesso(){
        return !transacoesConcluidasComSucesso().isEmpty();
    }

}
