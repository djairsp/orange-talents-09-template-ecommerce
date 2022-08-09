package com.zup.mercadolivre.negocio;

import com.zup.mercadolivre.model.OpniaoProduto;

import java.util.OptionalDouble;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Opnioes {

    private final Set<OpniaoProduto> opnioes;

    public Opnioes(Set<OpniaoProduto> opnioesProdutos) {
        this.opnioes = opnioesProdutos;
    }

    public <T> Set<T> mapeiaOpinioes(Function<OpniaoProduto, T> functionMap){
        return this.opnioes.stream().map(functionMap).collect(Collectors.toSet());
    }

    public double media(){
        Set<Integer> notas = mapeiaOpinioes(opniaoProduto -> opniaoProduto.getNota());
        OptionalDouble media = notas.stream().mapToInt(nota -> nota).average();
        return media.orElseGet(() -> 0.0);
    }

    public int total() {
        return this.opnioes.size();
    }
}
