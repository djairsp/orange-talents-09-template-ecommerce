package com.zup.mercadolivre.model;


import com.zup.mercadolivre.negocio.Opnioes;
import com.zup.mercadolivre.novoproduto.NovoCaracteristicasRequest;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.net.BindException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
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
    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private Set<ImagemProduto> imagens = new HashSet<>();
    @OneToMany(mappedBy = "produto")
    private Set<PerguntaProduto> perguntas = new HashSet<>();
    @OneToMany(mappedBy = "produto")
    private Set<OpniaoProduto> opnioesProdutos = new HashSet<>();

    @Deprecated
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
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", valorProduto=" + valorProduto +
                ", quantidadeDisponivel=" + quantidadeDisponivel +
                ", descricao='" + descricao + '\'' +
                ", categoria=" + categoria +
                ", dono=" + dono +
                ", criado=" + criado +
                ", caracteristicaProdutos=" + caracteristicaProdutos +
                ", imagens=" + imagens +
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produto)) return false;
        Produto produto = (Produto) o;
        return nome.equals(produto.nome) && caracteristicaProdutos.equals(produto.caracteristicaProdutos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, caracteristicaProdutos);
    }

    public void associarImagens(Set<String> links) {
        Set<ImagemProduto> imagens = links.stream()
                .map(linkImagem -> new ImagemProduto(this, linkImagem))
                .collect(Collectors.toSet());

        this.imagens.addAll(imagens);
    }

    public boolean pertenceAoUsuario(Usuario possivelDono) {
        return this.dono.equals(possivelDono);
    }

    public <T> Set<T> mapeiaCaracteristicas(Function<CaracteristicaProduto, T> functionMap){
        return this.caracteristicaProdutos.stream().map(functionMap).collect(Collectors.toSet());
    }

    public <T> Set<T> mapeiaImages(Function<ImagemProduto, T> functionMap){
        return this.imagens.stream().map(functionMap).collect(Collectors.toSet());
    }

    public <T> Set<T> mapeiaPerguntas(Function<PerguntaProduto, T> functionMap){
        return this.perguntas.stream().map(functionMap).collect(Collectors.toSet());
    }

    public Opnioes getOpnioes(){
        return new Opnioes(this.opnioesProdutos);
    }

    public boolean abaterEstoque(@Positive int quantidade) {
        Assert.isTrue(quantidade > 0, "A quantidade de produtos no estoque deve ser maior que zero!!!");
        if(quantidade <= this.quantidadeDisponivel){
            this.quantidadeDisponivel-=quantidade;
            return true;
        }
        return false;
    }
}
