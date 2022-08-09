package com.zup.mercadolivre.detalheproduto;

import com.zup.mercadolivre.model.Produto;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RestController
@RequestMapping("produtos")
public class DetalheProdutoController {

    @PersistenceContext
    private EntityManager manager;

    @GetMapping("/{id}")
    @Transactional
    public DetalheProdutoDTO getDetalheProduto(@PathVariable("id") Long id){
        Produto produtoDetalhe = manager.find(Produto.class, id);
         return new DetalheProdutoDTO(produtoDetalhe);

    }
}
