package com.zup.mercadolivre.novacategoria;

import com.zup.mercadolivre.beanvalidation.ExistsId;
import com.zup.mercadolivre.beanvalidation.UniqueValue;
import com.zup.mercadolivre.model.Categoria;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class NovaCategoriaRequest {

    @NotBlank
    @UniqueValue(domainClass = Categoria.class, fieldName = "nome")
    private String nome;
    @Positive
    @ExistsId(domainClass = Categoria.class, fieldName = "id")
    private Long idCategoriaMae;

    public Categoria toModel(EntityManager entityManager){
        Categoria categoria = new Categoria(nome);
        if(idCategoriaMae != null){
            Categoria categoriaMae = entityManager.find(Categoria.class, idCategoriaMae);
            categoria.setCategoriaMae(categoriaMae);
        }
        return categoria;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdCategoriaMae(Long idCategoriaMae) {
        this.idCategoriaMae = idCategoriaMae;
    }
}