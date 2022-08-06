package com.zup.mercadolivre.novousuario;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;

public class SenhaLimpa {

    private String senha;

    public SenhaLimpa(String senha) {
        Assert.hasLength(senha, "A senha não pode ser vazia");
        Assert.isTrue(senha.length() >= 6, "A senha não pode ser menor que 6");
        this.senha = senha;
    }

    public String hash(){
        return new BCryptPasswordEncoder().encode(senha);
    }
}
