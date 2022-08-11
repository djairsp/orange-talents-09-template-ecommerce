package com.zup.mercadolivre.retornocompra;

import com.zup.mercadolivre.model.Compra;
import com.zup.mercadolivre.model.RetornoGatewayPagamento;
import com.zup.mercadolivre.service.EventoCompraSucesso;
import com.zup.mercadolivre.service.EventosNovaCompra;
import com.zup.mercadolivre.service.NotaFiscal;
import com.zup.mercadolivre.service.Ranking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.Set;

@RestController
public class RetornoCompraController {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    EventosNovaCompra eventosNovaCompra;

    @PostMapping("/retorno-pagseguro/{id}")
    @Transactional
    public String processarPagamentoPagSeguro(@PathVariable("id") Long idCompra, @Valid RetornoPagSeguroRequest request) {
        return processa(idCompra, request);
    }

    @PostMapping("/retorno-paypal/{id}")
    @Transactional
    public String processarPagamentoPaypal(@PathVariable("id") Long idCompra, @Valid RetornoPayPalRequest request) {
        return processa(idCompra, request);
    }

    public String processa(Long idCompra, RetornoGatewayPagamento retornoGatewayPagamento) {
        Compra compra = entityManager.find(Compra.class, idCompra);
        compra.adicionaTransacao(retornoGatewayPagamento);
        entityManager.merge(compra);
        eventosNovaCompra.processa(compra);
        return compra.toString();
    }
}
