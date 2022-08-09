package com.zup.mercadolivre.novacompra;

import com.zup.mercadolivre.model.Compra;
import com.zup.mercadolivre.model.Produto;
import com.zup.mercadolivre.model.Usuario;
import com.zup.mercadolivre.negocio.GatewayPagamento;
import com.zup.mercadolivre.util.security.UsuarioLogado;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
@RequestMapping("compras")
public class NovaCompraController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping
    @Transactional
    public String novaCompra(@RequestBody @Valid NovaCompraRequest request,
                             @AuthenticationPrincipal UsuarioLogado usuarioLogado,
                             ServletUriComponentsBuilder uriComponentsBuilder) throws BindException {
        Produto produtoParaCompra = manager.find(Produto.class, request.getIdProduto());

        if(produtoParaCompra.abaterEstoque(request.getQuantidade())){
            int quantidade = request.getQuantidade();
            Usuario comprador = usuarioLogado.getUsuario();
            GatewayPagamento gatewayPagamento = request.getGatewayPagamento();
            Compra compra = new Compra(produtoParaCompra, quantidade, comprador, gatewayPagamento);
            manager.persist(compra);

            if(this.equals(GatewayPagamento.pagseguro)){
                return GatewayPagamento.pagseguro.urlGateway(compra, uriComponentsBuilder);
            }else{
                return GatewayPagamento.paypal.urlGateway(compra, uriComponentsBuilder);
            }

        }
        BindException problemaEstoque = new BindException(request, "novaCompraRequest");
        problemaEstoque.reject(null, "Não foi possível realizar a compra, sem estoque!");
        throw problemaEstoque;

    }
}
