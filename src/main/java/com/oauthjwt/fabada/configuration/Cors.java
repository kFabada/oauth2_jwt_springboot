package com.oauthjwt.fabada.configuration;


import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import com.oauthjwt.fabada.configuration.auth.CustomOauth2.CustomOauth2Register;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class Cors {
    @Autowired
    private CustomOauth2Register customOauth2Register;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http){
       return http
               .csrf(AbstractHttpConfigurer::disable)
               .oauth2AuthorizationServer(
                       auth -> auth
                               .registeredClientRepository(customOauth2Register)
                               .oidc(Customizer.withDefaults())

               )
               .authorizeHttpRequests(httpRequest -> httpRequest.anyRequest().authenticated())
               .build();
    }


}
