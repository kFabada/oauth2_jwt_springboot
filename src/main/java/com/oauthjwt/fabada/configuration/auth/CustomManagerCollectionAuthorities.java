package com.oauthjwt.fabada.configuration.auth;

import com.oauthjwt.fabada.model.User;
import com.oauthjwt.fabada.repository.UserRepository;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.*;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.util.*;

@EnableWebSecurity
@Configuration
public class CustomManagerCollectionAuthorities implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        CustomUserDetails userDetails = new CustomUserDetails();

        if(user.isEmpty()) throw new UsernameNotFoundException("username not found");

        userDetails.setUsername(username);
        userDetails.setPassword(user.get().getPassword());

        String[] role = user.get().getRole().split(" ");
        userDetails.getRoles().addAll(Arrays
                .stream(role)
                .map((data) -> new CustomListGrantedAuthority(data))
                .toList()
        );
        return userDetails;
    }

    @Bean
    public CustomUserDetails customUserDetails(){
        return new CustomUserDetails();
    }
}
