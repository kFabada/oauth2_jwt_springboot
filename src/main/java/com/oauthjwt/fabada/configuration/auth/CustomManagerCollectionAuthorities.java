//package com.oauthjwt.fabada.configuration.auth;
//
//import org.jspecify.annotations.Nullable;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.*;
//import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
//
//import java.util.*;
//
//@EnableWebSecurity
//@Configuration
//public class CustomManagerCollectionAuthorities implements UserDetailsService {
//
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        CustomUserDetails user = new CustomUserDetails();
//        user.setUsername(username);
//
//
//        String idForEncode = "{bcrypt}".concat(encoder.encode("1bd3e210-7b01-4b92-874c-04536c5f0701"));
//
//
//        user.setPassword(idForEncode);
//        user.getList().addAll(
//                List.of(
//                        new CustomListGrantedAuthority("ADMIN"),
//                        new CustomListGrantedAuthority("HOST")
//                )
//        );
//
//        return user;
//    }
//
//    @Bean
//    public CustomUserDetails customUserDetails(){
//        return new CustomUserDetails();
//    }
//}
