package com.zup.mercadolivre.novoproduto;

import com.zup.mercadolivre.model.Produto;
import com.zup.mercadolivre.model.Usuario;
import com.zup.mercadolivre.util.security.UsuarioLogado;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("produtos")
public class NovoProdutoController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private Uploader uploadFake;

    @Autowired
    private ProibeCaracteristicasNomeIguaisValidator proibeCaracteristicasNomeIguaisValidator;


    @InitBinder(value = "NovoProdutoRequest")
    public void init(WebDataBinder binder){
        binder.addValidators(proibeCaracteristicasNomeIguaisValidator);
    }


    @PostMapping
    @Transactional
    public String create(@RequestBody @Valid NovoProdutoRequest request, @AuthenticationPrincipal UsuarioLogado usuarioLogado){
        Produto produto = request.toModel(manager, usuarioLogado);
        manager.persist(produto);
        return produto.toString();
    }

    @PostMapping("/{id}/imagens")
    @Transactional
    public String uploadImagem(@PathVariable("id") Long id,  @Valid NovasImagemResquest  resquest,
                               @AuthenticationPrincipal UsuarioLogado usuarioLogado){

        Assert.isTrue(id.intValue() >= 1, "O id não foi informado ");
        Usuario usuario = usuarioLogado.getUsuario();
        Produto produto = manager.find(Produto.class, id);
        Assert.isTrue(produto != null, "Não foi encontrado Produto com o Id informado");

        if (!produto.pertenceAoUsuario(usuario)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        Set<String> imagens = uploadFake.envia(resquest.getImagens());
        produto.associarImagens(imagens);
        manager.merge(produto);
        return produto.toString();

    }
}
