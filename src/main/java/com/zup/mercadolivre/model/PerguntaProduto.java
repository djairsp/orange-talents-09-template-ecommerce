package com.zup.mercadolivre.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class PerguntaProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String titulo;
    @NotBlank
    @Size(max = 500)
    private String pergunta;
    @ManyToOne
    private Produto produto;
    @ManyToOne
    private Usuario consumidor;
    private LocalDate criadoCriado;

    public PerguntaProduto() {
    }

    public PerguntaProduto(String titulo, String pergunta, Produto produto, Usuario consumidor) {
        this.titulo = titulo;
        this.pergunta = pergunta;
        this.produto = produto;
        this.consumidor = consumidor;
        this.criadoCriado = LocalDate.now();
    }

    @Override
    public String toString() {
        return "PerguntaProduto{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", pergunta='" + pergunta + '\'' +
                ", produto=" + produto +
                ", consumidor=" + consumidor +
                '}';
    }
}
