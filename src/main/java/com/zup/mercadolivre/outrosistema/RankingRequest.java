package com.zup.mercadolivre.outrosistema;

import javax.validation.constraints.NotNull;

public class RankingRequest {

    @NotNull
    private Long idCompra;
    @NotNull
    private Long idDono;

    public RankingRequest(Long idCompra, Long idDono) {
        this.idCompra = idCompra;
        this.idDono = idDono;
    }

    @Override
    public String toString() {
        return "RankingRequest{" +
                "idCompra=" + idCompra +
                ", idDono=" + idDono +
                '}';
    }

    public Long getIdCompra() {
        return idCompra;
    }

    public Long getIdDono() {
        return idDono;
    }
}