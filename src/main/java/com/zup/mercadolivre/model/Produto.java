package com.zup.mercadolivre.model;


import com.zup.mercadolivre.novoproduto.NovoCaracteristicasRequest;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nome;
    @NotNull
    @Min(20)
    private BigDecimal valorProduto;
    @NotNull
    private int quantidadeDisponivel;
    @NotNull
    private String descricao;
    @NotNull
    @ManyToOne
    private Categoria categoria;
    @NotNull
    @ManyToOne
    private Usuario dono;
    @PastOrPresent
    private LocalDateTime criado;
    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
    private Set<CaracteristicaProduto> caracteristicaProdutos = new HashSet<>();

    public Produto() {}

    public Produto(@NotBlank String nome, @NotNull @Min(20) BigDecimal valorProduto,
                   @NotNull int quantidadeDisponivel, @NotNull @NotBlank String descricao,
                   Categoria categoria, Usuario usuario,
                   @Size(min = 3) @Valid List<NovoCaracteristicasRequest> caracteristicas) {
        this.nome = nome;
        this.valorProduto = valorProduto;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.descricao = descricao;
        this.categoria = categoria;
        this.dono = usuario;
        this.criado = LocalDateTime.now();
        Set<CaracteristicaProduto> caracteristicaProduto =
                caracteristicas.stream().map(caracteristica -> caracteristica.toModel(this))
                .collect(Collectors.toSet());
        this.caracteristicaProdutos.addAll(caracteristicaProduto);

        Assert.isTrue(this.caracteristicaProdutos.size() >=3, "Produto precisa ter tres ou mais caracteristicas");
    }

    @Override
    public String toString() {
        return "Produto{" +
                "nome='" + nome + '\'' +
                ", valorProduto=" + valorProduto +
                ", quantidadeDisponivel=" + quantidadeDisponivel +
                ", descricao='" + descricao + '\'' +
                ", categoria=" + categoria +
                ", dono=" + dono +
                ", criado=" + criado +
                ", caracteristicaProdutos=" + caracteristicaProdutos +
                '}';
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValorProduto() {
        return valorProduto;
    }

    public int getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public String getDescricao() {
        return descricao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Usuario getDono() {
        return dono;
    }

    public LocalDateTime getCriado() {
        return criado;
    }

    public Set<CaracteristicaProduto> getCaracteristicaProdutos() {
        return caracteristicaProdutos;
    }
}
