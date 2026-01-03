//package com.oauthjwt.fabada.configuration.auth.CustomOauth2;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
//import org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat;
//import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
//
//import java.time.Duration;
//
//@Configuration
//@EnableWebSecurity
//public class CustomTokenSetting {
//
//    @Bean
//    public TokenSettings tokenSettings(){
//        return TokenSettings
//                .builder()
//                .accessTokenFormat(OAuth2TokenFormat.SELF_CONTAINED)
//                .refreshTokenTimeToLive(Duration.ofMinutes(15))
//                .accessTokenTimeToLive(Duration.ofMinutes(5))
//                .build();
//    }
//}
