package com.zup.mercadolivre.novousuario;

import com.zup.mercadolivre.model.Usuario;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class NovoUsuarioController {

    @PersistenceContext
    private EntityManager manager;


    @PostMapping
    @Transactional
    public String criarUsuario(@RequestBody @Valid NovoUsuarioRequest request){
        Usuario usuario = request.toModel();
        manager.persist(usuario);
        return usuario.toString();
    }
}
