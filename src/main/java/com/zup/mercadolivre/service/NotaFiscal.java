package com.zup.mercadolivre.service;

import com.zup.mercadolivre.model.Compra;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class NotaFiscal implements EventoCompraSucesso{

    public void processa(Compra compra) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> request = Map.of("idCompra", compra.getId(), "idComprador", compra.getDonoProduto().getId());
        restTemplate.postForEntity("http://localhost:8080/notas-fiscais", request, String.class);
    }
}
