package com.zup.mercadolivre.novoproduto;

import com.zup.mercadolivre.model.Produto;
import com.zup.mercadolivre.util.security.UsuarioLogado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
@RequestMapping("produtos")
public class NovoProdutoController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private ProibeCaracteristicasNomeIguaisValidator proibeCaracteristicasNomeIguaisValidator;


    @InitBinder
    public void init(WebDataBinder binder){
        binder.addValidators(proibeCaracteristicasNomeIguaisValidator);
    }


    @PostMapping
    @Transactional
    public String create(@RequestBody @Valid NovoProdutoRequest request, @AuthenticationPrincipal UsuarioLogado usuarioLogado){
        Produto produto = request.toModel(manager, usuarioLogado);
        manager.persist(produto);
        return produto.toString();
        //return request.toString();
    }
}
