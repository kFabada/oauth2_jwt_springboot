//package com.oauthjwt.fabada.configuration.auth.KeyCloak;
//
//
//import org.jspecify.annotations.Nullable;
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.security.oauth2.jwt.Jwt;
//import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthentication;
//import org.springframework.stereotype.Component;
//
//@Component
//public class JwtConverte implements Converter<Jwt, BearerTokenAuthentication> {
//
//    @Override
//    public @Nullable BearerTokenAuthentication convert(Jwt source) {
//        var claims = source.getClaims();
//        var header = source.getHeaders();
//
//
//        return null;
//    }
//}
