package com.zup.mercadolivre.novoproduto;

import com.zup.mercadolivre.model.Categoria;
import com.zup.mercadolivre.model.Produto;
import com.zup.mercadolivre.util.beanvalidation.ExistsId;
import com.zup.mercadolivre.util.beanvalidation.UniqueValue;
import com.zup.mercadolivre.util.security.UsuarioLogado;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class NovoProdutoRequest {

    @NotBlank
    @UniqueValue(domainClass = Produto.class, fieldName = "nome")
    private String nome;
    @NotNull
    @Min(20)
    private BigDecimal valorProduto;
    @NotNull
    private int quantidadeDisponivel;
    @NotNull
    @NotBlank
    private String descricao;
    @ExistsId(domainClass = Categoria.class, fieldName = "id")
    private Long idCategoria;
    //min 3
    @Size(min = 3)
    @Valid
    private List<NovoCaracteristicasRequest> caracteristicas = new ArrayList<>();


    public NovoProdutoRequest(String nome, BigDecimal valorProduto, int quantidadeDisponivel, String descricao,
                              Long idCategoria, List<NovoCaracteristicasRequest> caracteristicas) {
        this.nome = nome;
        this.valorProduto = valorProduto;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.descricao = descricao;
        this.idCategoria = idCategoria;
        this.caracteristicas.addAll(caracteristicas);
    }

    public Produto toModel(EntityManager manager, UsuarioLogado usuarioLogado){

        Categoria categoria = manager.find(Categoria.class, idCategoria);
        Assert.isTrue(categoria != null, "Categoria não encontrada!");

        return new Produto(nome, valorProduto, quantidadeDisponivel, descricao,
                categoria, usuarioLogado.getUsuario(), caracteristicas);
    }


    @Override
    public String toString() {
        return "NovoProdutoRequest{" +
                "nome='" + nome + '\'' +
                ", valorProduto=" + valorProduto +
                ", quantidadeDisponivel=" + quantidadeDisponivel +
                ", descricao='" + descricao + '\'' +
                ", idCategoria=" + idCategoria +
                ", caracteristicas=" + caracteristicas.toString() +
                '}';
    }

    public List<NovoCaracteristicasRequest> getCaracteristicas() {
        return caracteristicas;
    }

    public boolean verifyCarateristicasIguais(){
        HashSet<String> nomesIguais = new HashSet<>();
        for (NovoCaracteristicasRequest caracteristica: this.caracteristicas) {
            if(!nomesIguais.add(caracteristica.getNome())){
                return true;
            }
        }
        return false;
    }
}
