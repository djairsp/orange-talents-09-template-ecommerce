package com.zup.mercadolivre.novousuario;

import com.zup.mercadolivre.model.Usuario;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class NovoUsuarioRequest {

    @Email
    @NotBlank
    private final String email;
    @NotBlank
    @Length( min = 6)
    private final String senha;

    public NovoUsuarioRequest(@Email @NotBlank String email,
                              @NotBlank @Length( min = 6) String senha) {

        this.email = email;
        this.senha =  senha;
    }

    public Usuario toModel(){
        return new Usuario(this.email, new SenhaLimpa(senha));
    }

    public String getEmail() {
        return email;
    }
}
