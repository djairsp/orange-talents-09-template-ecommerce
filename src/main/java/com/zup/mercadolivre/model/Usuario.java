package com.zup.mercadolivre.model;

import com.zup.mercadolivre.novousuario.SenhaLimpa;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @NotBlank
    private String email;
    @NotBlank
    @Length( min = 6)
    private String senha;

    @PastOrPresent
    private LocalDateTime criado;

    @Deprecated
    public Usuario() {}

    public Usuario(@Email @NotBlank String email,
                   @Valid @NotNull SenhaLimpa senha) {

        Assert.isTrue(StringUtils.hasLength(email), "Email não pode ser em branco");
        Assert.notNull(senha, "O objeto do tipo senha limpa nao pode ser nulo");

        this.email = email;
        this.senha = senha.hash();
        this.criado = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }
}
