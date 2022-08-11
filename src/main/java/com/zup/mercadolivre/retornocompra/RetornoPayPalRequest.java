package com.zup.mercadolivre.retornocompra;

import com.zup.mercadolivre.model.Compra;
import com.zup.mercadolivre.model.RetornoGatewayPagamento;
import com.zup.mercadolivre.model.Transacao;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RetornoPayPalRequest implements RetornoGatewayPagamento {

    @Min(0)
    @Max(1)
    private int status;
    @NotBlank
    private String idTransacao;

    public RetornoPayPalRequest(String idTransacao, int status) {
        this.idTransacao = idTransacao;
        this.status = status;
    }

    @Override
    public String toString() {
        return "RetornoPayPalRequest{" +
                "status=" + status +
                ", idTransacao='" + idTransacao + '\'' +
                '}';
    }

    public Transacao toTransacao(Compra compra) {
        StatusTransacao statusTransacao = status == 0 ? StatusTransacao.ERROR : StatusTransacao.SUCESSO;
        return new Transacao(statusTransacao, idTransacao, compra);
    }

}
