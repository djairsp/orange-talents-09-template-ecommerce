package com.zup.mercadolivre.retornocompra;

public enum StatusRetornoPagSeguro {

    SUCESSO, ERROR;

    public StatusTransacao normaliza() {
        if(this.equals(SUCESSO)){
            return StatusTransacao.SUCESSO;
        }

        return StatusTransacao.ERROR;
    }
}
