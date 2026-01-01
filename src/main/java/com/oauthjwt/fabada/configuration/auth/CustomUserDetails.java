//package com.oauthjwt.fabada.configuration.auth;
//
//import org.jspecify.annotations.Nullable;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//@Configuration
//@EnableWebSecurity
//public class CustomUserDetails implements UserDetails {
//    private String username;
//    private String password;
//    private List<GrantedAuthority> role = new ArrayList<>();
//
//    public List<GrantedAuthority> getList() {
//        return role;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return role;
//    }
//
//    @Override
//    public @Nullable String getPassword() {
//        return password;
//    }
//
//    @Override
//    public String getUsername() {
//        return username;
//    }
//}
