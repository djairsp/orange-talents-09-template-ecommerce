package com.zup.mercadolivre.util.security;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class LoginRequeset {

    @NotBlank
    @Email
    private final String email;
    @NotBlank
    @Length(min = 6)
    private final String senha;

    public LoginRequeset(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public UsernamePasswordAuthenticationToken converter() {
        return new UsernamePasswordAuthenticationToken(email, senha);
    }


    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }
}
