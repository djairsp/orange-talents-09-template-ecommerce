package com.zup.mercadolivre.novousuario;

import com.zup.mercadolivre.model.Usuario;
import io.opentracing.Span;
import io.opentracing.Tracer;
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

    @Autowired
    Tracer tracer;


    @InitBinder
    public void init(WebDataBinder binder){
        binder.addValidators(proibeUsuarioComEmailDuplicadoValidator);
    }

    @PostMapping
    @Transactional
    public String create(@RequestBody @Valid NovoUsuarioRequest request){
        Usuario usuario = request.toModel();

        Span activateSpan = tracer.activeSpan();
        activateSpan.setTag("usuario.email", usuario.getEmail());


        manager.persist(usuario);
        return usuario.toString();
    }
}
