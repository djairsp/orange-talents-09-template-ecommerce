package com.zup.mercadolivre.adicionaropniao;

import com.zup.mercadolivre.model.OpniaoProduto;
import com.zup.mercadolivre.model.Produto;
import com.zup.mercadolivre.model.Usuario;
import com.zup.mercadolivre.util.security.UsuarioLogado;
import io.jsonwebtoken.lang.Assert;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
@RequestMapping("produtos")
public class AdicionarOpniaoController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping("/{id}/opniao")
    @Transactional
    public String adicionarOpniaoProduto(@PathVariable Long id, @RequestBody @Valid AdicionarOpniaoRequest request,
                                         @AuthenticationPrincipal UsuarioLogado usuarioLogado){

        Assert.isTrue(id.intValue() >= 1, "O id não foi informado ");
        Usuario consumidor = usuarioLogado.getUsuario();
        Produto produto = manager.find(Produto.class, id);
        Assert.isTrue(produto != null, "Não foi encontrado Produto com o Id informado");
        OpniaoProduto opniaoProduto = request.toModel(produto, consumidor);
        manager.persist(opniaoProduto);
        return opniaoProduto.toString();

    }
}
