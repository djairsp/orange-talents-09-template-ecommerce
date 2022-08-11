package com.zup.mercadolivre.service;

import com.zup.mercadolivre.model.Compra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class EventosNovaCompra {

    @Autowired
    private Set<EventoCompraSucesso> eventoCompraSucesso;

    public void processa(Compra compra){
        if(compra.processadoComSucesso()){
            eventoCompraSucesso.forEach(evento -> evento.processa(compra));
        }else{
            //enviar e-mail
        }
    }
}
