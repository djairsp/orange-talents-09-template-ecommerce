package com.zup.mercadolivre.novoproduto;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ProibeCaracteristicasNomeIguaisValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return NovoProdutoRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(errors.hasErrors()){
            return;
        }

        NovoProdutoRequest request = (NovoProdutoRequest) target;
        if(request.verifyCarateristicasIguais()){
            errors.rejectValue("caracteristicas", null, "Existe categorias com o mesmo nome!");
        }
    }
}
