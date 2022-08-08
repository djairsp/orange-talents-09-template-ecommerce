package com.zup.mercadolivre.adicionaPergunta;

import com.zup.mercadolivre.model.PerguntaProduto;
import com.zup.mercadolivre.model.Produto;
import com.zup.mercadolivre.model.Usuario;
import com.zup.mercadolivre.util.security.UsuarioLogado;
import io.jsonwebtoken.lang.Assert;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
@RequestMapping("produtos")
public class AdicionarPerguntaController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping("/{id}/perguntas")
    @Transactional
    public String adicionarPergunta(@PathVariable("id") Long id, @RequestBody @Valid NovaPerguntaRequest request,
                                    @AuthenticationPrincipal UsuarioLogado usuarioLogado){

        Assert.isTrue(id.intValue() >= 1, "O id não foi informado ");
        Usuario consumidor = usuarioLogado.getUsuario();
        Produto produto = manager.find(Produto.class, id);

        PerguntaProduto novaPergunta = request.toModel(produto, consumidor);
        manager.persist(novaPergunta);

        RestTemplate restTemplate = new RestTemplate();
        //restTemplate.postForEntity()

        return novaPergunta.toString();
    }
}
