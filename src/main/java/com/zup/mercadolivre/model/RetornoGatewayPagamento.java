package com.zup.mercadolivre.model;

public interface RetornoGatewayPagamento {

    Transacao toTransacao(Compra compra);
}
