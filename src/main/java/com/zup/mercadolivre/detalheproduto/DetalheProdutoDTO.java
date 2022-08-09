package com.zup.mercadolivre.detalheproduto;

import com.zup.mercadolivre.model.Produto;
import com.zup.mercadolivre.negocio.Opnioes;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

public class DetalheProdutoDTO {


    private final String descricao;
    private final String nome;
    private final BigDecimal preco;
    private final Set<DetalheCaracteristicaDTO> caracteristicas;
    private final Set<String> linksImagens;
    private final Set<String> perguntas;
    private final Set<Object> opinioes;
    private double mediaNotas;
    private final int totalOpniacao;


    public DetalheProdutoDTO(Produto produto) {
        this.descricao = produto.getDescricao();
        this.nome = produto.getNome();
        this.preco = produto.getValorProduto();
        this.caracteristicas = produto.mapeiaCaracteristicas(DetalheCaracteristicaDTO::new);
        this.linksImagens = produto.mapeiaImages(imagemProduto -> imagemProduto.getLink());
        this.perguntas = produto.mapeiaPerguntas(perguntaProduto -> perguntaProduto.getTitulo());

        Opnioes opnioes = produto.getOpnioes();
        this.opinioes = opnioes.mapeiaOpinioes( opniaoProduto -> {
            return Map.of("titulo", opniaoProduto.getTitulo(), "descricao", opniaoProduto.getDescricao());
        });
        this.mediaNotas = opnioes.media();
        this.totalOpniacao = opnioes.total();


    }

    public String getDescricao() {
        return descricao;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Set<DetalheCaracteristicaDTO> getCaracteristicas() {
        return caracteristicas;
    }

    public Set<String> getLinksImagens() {
        return linksImagens;
    }

    public Set<String> getPerguntas() {
        return perguntas;
    }

    public Set<Object> getOpinioes() {
        return opinioes;
    }

    public double getMediaNotas() {
        return mediaNotas;
    }

    public int getTotalOpniacao() {
        return totalOpniacao;
    }
}
