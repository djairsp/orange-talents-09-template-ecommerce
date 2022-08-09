package com.zup.mercadolivre.model;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
public class ImagemProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull @Valid
    @ManyToOne
    private Produto produto;
    @NotNull
    private String link;

    @Deprecated
    public ImagemProduto() {
    }

    public ImagemProduto(@NotNull @Valid Produto produto, @NotNull  String linkImagem) {
        this.produto = produto;
        this.link = linkImagem;
    }

    @Override
    public String toString() {
        return "ImagemProduto{" +
                "id=" + id +
                ", link='" + link + '\'' +
                '}';
    }

    public String getLink() {
        return link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ImagemProduto)) return false;

        ImagemProduto that = (ImagemProduto) o;

        if (!produto.equals(that.produto)) return false;
        return link.equals(that.link);
    }

    @Override
    public int hashCode() {
        int result = produto.hashCode();
        result = 31 * result + link.hashCode();
        return result;
    }
}
