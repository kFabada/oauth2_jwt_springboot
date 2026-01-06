package com.oauthjwt.fabada.controller;


import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.gen.RSAKeyGenerator;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.oauthjwt.fabada.model.Client;
import com.oauthjwt.fabada.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;

import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Clock;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@RestController
@EnableMethodSecurity
@RequestMapping("/cliente")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Value("${key-public}")
    private RSAPublicKey rsaPublicKey;
    @Value("${key-private}")
    private RSAPrivateKey rsaPrivateKey;

    @PostMapping("/register")
    public ResponseEntity<?> registerClient(@RequestBody Client client){
        Client newClient = clientService.registerClient(client);
        return ResponseEntity.ok(newClient);
    }

    @PostMapping("jwt-acess")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public  ResponseEntity<?> endpointJWTAcess(){
        return ResponseEntity.ok("funcionando jwt auth");
    }

    @PostMapping("get-key")
    public ResponseEntity<?> teste() {

        RSAKey rsaKey = new RSAKey.Builder(rsaPublicKey)
                .privateKey(rsaPrivateKey)
                .build();

        JWSSigner signer;

        try{
           signer = new RSASSASigner(rsaKey.toPrivateKey());
        }catch (JOSEException e){
            return ResponseEntity.ok("vazio");
        }


        long expSecondTime = 3600L;
        Instant date = Instant.now(Clock.systemUTC());
        Instant exp =  Instant.now(Clock.systemUTC()).plusSeconds(expSecondTime);

        JWTClaimsSet claim = new JWTClaimsSet.Builder()
                .claim("role", "ADMIN")
                .claim("id", "123")
                .claim("scope", "ADMIN")
                .expirationTime(Date.from(exp))
                .issueTime(Date.from(date))
                .issuer("jwt-client")
                .subject("fabada")
                .build();

        SignedJWT signedJWT = new SignedJWT(
                new JWSHeader.Builder(JWSAlgorithm.RS256)
                        .type(JOSEObjectType.JWT)
                        .keyID(rsaKey.getKeyID()).build(),
                claim
        );

        try {
            signedJWT.sign(signer);
        } catch (JOSEException e) {
            return ResponseEntity.ok("vazio");
        }

        String tokenSerialize = signedJWT.serialize();

        return ResponseEntity.ok(tokenSerialize);
    }
}
