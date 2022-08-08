package com.zup.mercadolivre.novoproduto;

import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

public interface Uploader {

    public Set<String> envia(Set<MultipartFile> imagens);
}
