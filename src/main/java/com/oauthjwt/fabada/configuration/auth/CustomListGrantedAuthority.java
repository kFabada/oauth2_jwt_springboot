package com.oauthjwt.fabada.configuration.auth;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;

public class CustomListGrantedAuthority implements GrantedAuthority {

    private final String role;

    public CustomListGrantedAuthority(String role) {
        this.role = role;
    }

    @Override
    public @Nullable String getAuthority() {
        return role;
    }
}
