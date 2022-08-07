package com.zup.mercadolivre.novousuario;

import com.zup.mercadolivre.model.Usuario;
import com.zup.mercadolivre.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class ProibeUsuarioComEmailDuplicadoValidator implements Validator {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return NovoUsuarioRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        if(errors.hasErrors()){
            return;
        }

        NovoUsuarioRequest request = (NovoUsuarioRequest) target;
        Optional<Usuario> possivelUsuario = usuarioRepository.findByEmail(request.getEmail());
        if(possivelUsuario.isPresent()){
            errors.rejectValue("email", "01", "Já existe este email na base de dados");
        }
    }
}
