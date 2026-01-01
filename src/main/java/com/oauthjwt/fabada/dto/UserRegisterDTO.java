package com.oauthjwt.fabada.dto;

import com.oauthjwt.fabada.exception.UserRegisterException;
import com.oauthjwt.fabada.model.User;

public record UserRegisterDTO(String username, String password, String role) {

    public User UserMap(){
        if(username.isEmpty() || password.isEmpty() || role.isEmpty()){
            throw new UserRegisterException("payload incomplete", 400);
        }

        return new User();
    }

}
