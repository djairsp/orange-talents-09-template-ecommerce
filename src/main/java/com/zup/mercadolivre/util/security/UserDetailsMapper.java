package com.zup.mercadolivre.util.security;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailsMapper {

    UserDetails map(Object shouldBeASystemUser);
}
