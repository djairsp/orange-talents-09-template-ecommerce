package com.zup.mercadolivre.util.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;


    @PostMapping
    public ResponseEntity<Object> autenticar(@RequestBody @Valid LoginRequeset loginRequeset){
        try{
            Authentication authentication = authManager.authenticate(loginRequeset.converter());
            String token = tokenService.generateToken(authentication);
            return ResponseEntity.ok(new TokenDto(token, "Bearer"));
        }catch (AuthenticationException e){
            logger.error("Autenticacao {}", e);
            return ResponseEntity.badRequest().build();
        }
    }
}
