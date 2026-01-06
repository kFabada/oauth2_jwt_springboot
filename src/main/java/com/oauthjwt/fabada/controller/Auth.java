package com.oauthjwt.fabada.controller;


import com.nimbusds.jose.JOSEException;
import com.oauthjwt.fabada.configuration.auth.jwt.IJWTEncoder;
import com.oauthjwt.fabada.dto.ErroMessageDTO;
import com.oauthjwt.fabada.dto.TokenResponseDTO;
import com.oauthjwt.fabada.model.User;
import com.oauthjwt.fabada.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class Auth {
    @Autowired
    private IJWTEncoder jwtEncoder;
    @Value("${second.time.token.jwt}")
    private Long expSecondTime;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/token")
    public ResponseEntity<?> tokenGenerate(Authentication authentication){
       Optional<User> user = userRepository.findByUsername(authentication.getPrincipal().toString());

       if(user.isEmpty()) return ResponseEntity.notFound().build();

       try{
         String token = jwtEncoder.generateTokenJWT(user.get());
         return ResponseEntity.ok(new TokenResponseDTO(expSecondTime, token));
       }catch (JOSEException e){
            return ResponseEntity
                    .internalServerError()
                    .body(new ErroMessageDTO(e.getMessage()));
       }
    }
}
