package com.zup.mercadolivre.service;

import com.zup.mercadolivre.model.Compra;

public interface EventoCompraSucesso {

    void processa(Compra compra);
}
