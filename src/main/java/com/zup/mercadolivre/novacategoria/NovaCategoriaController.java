package com.zup.mercadolivre.novacategoria;

import com.zup.mercadolivre.model.Categoria;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
@RequestMapping("categorias")
public class NovaCategoriaController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping
    @Transactional
    public String create(@RequestBody @Valid NovaCategoriaRequest request) {
        Categoria categoria = request.toModel(entityManager);
        entityManager.persist(categoria);
        return categoria.toString();
    }
}
