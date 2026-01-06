package com.oauthjwt.fabada.configuration.auth.jwt;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.oauthjwt.fabada.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Clock;
import java.time.Instant;
import java.util.Date;

public class JWTEncoder implements IJWTEncoder{
    @Value("${key-public}")
    private RSAPublicKey rsaPublicKey;
    @Value("${key-private}")
    private RSAPrivateKey rsaPrivateKey;
    @Value("${second.time.token.jwt}")
    private Long expSecondTime;
    @Value("${token.jwt.issuer}")
    private String tokenIssuer;

    @Override
    public String generateTokenJWT(User user) throws JOSEException {
        RSAKey rsaKey = setRsaKey();
        JWTClaimsSet claim = setClaims(user);

        JWSSigner signer = new RSASSASigner(rsaKey.toPrivateKey());

        SignedJWT signedJWT = new SignedJWT(
                new JWSHeader.Builder(JWSAlgorithm.RS256)
                        .type(JOSEObjectType.JWT)
                        .keyID(rsaKey.getKeyID()).build(),
                claim
        );
        signedJWT.sign(signer);
        return signedJWT.serialize();
    }

    private RSAKey setRsaKey(){
        return new RSAKey
                .Builder(rsaPublicKey)
                .privateKey(rsaPrivateKey)
                .build();
    }

    private JWTClaimsSet setClaims(User user){
        Instant date = Instant.now(Clock.systemUTC());
        Instant exp =  Instant.now(Clock.systemUTC()).plusSeconds(expSecondTime);

       return new JWTClaimsSet.Builder()
                .claim("role", user.getRole())
                .claim("user_id", user.getId())
                .claim("scope", user.getRole())
                .expirationTime(Date.from(exp))
                .issueTime(Date.from(date))
                .issuer(tokenIssuer)
                .subject(user.getUsername())
                .build();
    }
}
