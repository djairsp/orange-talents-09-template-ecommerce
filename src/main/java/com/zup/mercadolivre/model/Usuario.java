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

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario)) return false;

        Usuario usuario = (Usuario) o;

        if (email != null ? !email.equals(usuario.email) : usuario.email != null) return false;
        return senha != null ? senha.equals(usuario.senha) : usuario.senha == null;
    }

    @Override
    public int hashCode() {
        int result = email != null ? email.hashCode() : 0;
        result = 31 * result + (senha != null ? senha.hashCode() : 0);
        return result;
    }
}
