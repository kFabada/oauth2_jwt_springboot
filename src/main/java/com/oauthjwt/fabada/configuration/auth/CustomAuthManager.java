//package com.oauthjwt.fabada.configuration.auth;
//
//import jakarta.annotation.Nullable;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.GrantedAuthority;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@EnableWebSecurity
//@Configuration
//public class CustomAuthManager implements AuthenticationManager {
//    private final String USER = "user";
//    private final String PASS = "1bd3e210-7b01-4b92-874c-04536c5f0701";
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        if(authentication.getPrincipal() == null || authentication.getCredentials() == null) return null;
//
//        String username = authentication.getPrincipal().toString();
//        String password = authentication.getCredentials().toString();
//
//        var custom = new CustomManagerCollectionAuthorities();
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        authorities.addAll(List.of(
//                new CustomListGrantedAuthority("ADMIN"),
//                new CustomListGrantedAuthority("HOST")
//        ));
//        if(username.equals(USER)  && password.equals(PASS))
//            return authentication
//                .toBuilder()
//                    .authorities(e -> e.addAll(authorities))
//                .authenticated(true)
//                .build();
//
//        return null;
//    }
//}
