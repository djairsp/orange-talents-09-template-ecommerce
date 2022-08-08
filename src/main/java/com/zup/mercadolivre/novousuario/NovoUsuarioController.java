package com.zup.mercadolivre.novousuario;

import com.zup.mercadolivre.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class NovoUsuarioController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private ProibeUsuarioComEmailDuplicadoValidator proibeUsuarioComEmailDuplicadoValidator;

    @InitBinder
    public void init(WebDataBinder binder){
        binder.addValidators(proibeUsuarioComEmailDuplicadoValidator);
    }

    @PostMapping
    @Transactional
    public String create(@RequestBody @Valid NovoUsuarioRequest request){
        Usuario usuario = request.toModel();
        manager.persist(usuario);
        return usuario.toString();
    }
}
