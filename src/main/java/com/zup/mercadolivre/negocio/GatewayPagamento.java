package com.zup.mercadolivre.negocio;

import com.zup.mercadolivre.model.Compra;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public enum GatewayPagamento {

    pagseguro{
        public String urlGateway(Compra compra, ServletUriComponentsBuilder uriComponentsBuilder){
                String urlRetornoPagSeguro = uriComponentsBuilder.path("/retorno-pagseguro/{id}")
                        .buildAndExpand(compra.getId()).toString();
                return "pagseguro.com/"+ compra.getId() + "?redirectURL="+urlRetornoPagSeguro;
            }
    }, paypal{
        public String urlGateway(Compra compra, ServletUriComponentsBuilder uriComponentsBuilder){
            String urlRetornoPaypal = uriComponentsBuilder.path("/retorno-paypal/{id}")
                    .buildAndExpand(compra.getId()).toString();
            return "paypal.com/"+ compra.getId() + "?redirectURL="+urlRetornoPaypal;
        }
    };

    public abstract String urlGateway(Compra compra, ServletUriComponentsBuilder uriComponentsBuilder);

}
