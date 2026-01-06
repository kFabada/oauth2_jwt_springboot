package com.oauthjwt.fabada.configuration;


import com.nimbusds.jose.Algorithm;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.gen.RSAKeyGenerator;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
//import com.oauthjwt.fabada.configuration.auth.CustomOauth2.CustomOauth2Register;
//import com.oauthjwt.fabada.configuration.auth.KeyCloak.JwtConverte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.core.OAuth2Token;
import org.springframework.security.oauth2.core.oidc.endpoint.OidcParameterNames;

//import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
//import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
//import org.springframework.security.oauth2.server.authorization.token.*;
import org.springframework.security.oauth2.jwt.*;
//import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
//import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
//import org.springframework.security.oauth2.server.authorization.token.*;
import org.springframework.security.web.SecurityFilterChain;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Configuration
@EnableWebSecurity
public class Cors {
//   @Autowired
//    private CustomOauth2Register customOauth2Register;

//    @Value("${jwk-set-uri}")
//    private String jwkSetURI;
//    @Autowired
//    private JwtConverte jwtConverte;

    @Value("${key-public}")
    private RSAPublicKey rsaPublicKey;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http){
       return http
               .csrf(AbstractHttpConfigurer::disable)
               .httpBasic(Customizer.withDefaults())
               .oauth2ResourceServer(
                       resource -> resource.jwt(Customizer.withDefaults()
                       ))
//                .oauth2ResourceServer(resourceServer -> resourceServer
//                      .jwt(jwt -> jwt
//                              .jwkSetUri(jwkSetURI)
//                              //.jwtAuthenticationConverter(jwtConverte)
//                      )
//
//                )
              // .formLogin(Customizer.withDefaults())
//               .oauth2AuthorizationServer(
//                       auth -> auth
//                               .registeredClientRepository(customOauth2Register)
//                               .oidc(Customizer.withDefaults())
//                               .tokenGenerator(tokenGenerator())
//
//               )
               .authorizeHttpRequests(httpRequest -> httpRequest
                       .requestMatchers(
//                          "/realms/oauth2_resource_server/protocol/openid-connect/token",
//                          "/oauth2/token",
                            "/auth/register"
                       ).permitAll()
                               .anyRequest()
                               .authenticated()
               )
               .build();
    }


    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(rsaPublicKey).build();
    }

//    @Bean
//    public JWKSource<SecurityContext> jwkSource() {
//
//        KeyPair keyPair = generateRsaKey();
//        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
//        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
//        RSAKey rsaKey = new RSAKey.Builder(publicKey)
//                .privateKey(privateKey)
//                .keyID(UUID.randomUUID().toString())
//                .build();
//        JWKSet jwkSet = new JWKSet(rsaKey);
//        return new ImmutableJWKSet<>(jwkSet);
//    }
//
//    private static KeyPair generateRsaKey() {
//        KeyPair keyPair;
//        try {
//            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
//            keyPairGenerator.initialize(2048);
//            keyPair = keyPairGenerator.generateKeyPair();
//        }
//        catch (Exception ex) {
//            throw new IllegalStateException(ex);
//        }
//        return keyPair;
//    }
//
//    @Bean
//    public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
//        return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
//    }
//
//    @Bean
//    public AuthorizationServerSettings authorizationServerSettings() {
//        return AuthorizationServerSettings.builder().build();
//    }
//
//    @Bean
//    public OAuth2TokenGenerator<OAuth2Token> tokenGenerator() {
//        JwtEncoder jwtEncoder = new NimbusJwtEncoder(jwkSource());
//        JwtGenerator jwtGenerator = new JwtGenerator(jwtEncoder);
//
//        OAuth2AccessTokenGenerator accessTokenGenerator = new OAuth2AccessTokenGenerator();
//        OAuth2RefreshTokenGenerator refreshTokenGenerator = new OAuth2RefreshTokenGenerator();
//        return new DelegatingOAuth2TokenGenerator(
//                jwtGenerator, accessTokenGenerator, refreshTokenGenerator);
//    }
//
//    @Bean
//    public OAuth2TokenCustomizer<JwtEncodingContext> jwtCustomizer() {
//        return context -> {
//            JwsHeader.Builder headers = context.getJwsHeader();
//            JwtClaimsSet.Builder claims = context.getClaims();
//
//            headers.header("typ", "JWT");
//            headers.build();
//        };
//    }
}
