package com.zup.mercadolivre.novoproduto;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@Primary
public class UploadFake implements Uploader{

    public Set<String> envia(Set<MultipartFile> imagens){
        return imagens.stream()
                .map(imagem -> "http://bucket.io/"+ imagem.getOriginalFilename())
                .collect(Collectors.toSet());
    }
}
