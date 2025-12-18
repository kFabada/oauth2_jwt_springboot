package dto;

import exception.UserRegisterException;
import model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public record UserRegisterDTO(String username, String password, List<String> role) {

    public User UserMap(){
        if(username.isEmpty() || password.isEmpty() || role.isEmpty()){
            throw new UserRegisterException("payload incomplete", 400);
        }

        return new User(username, password, role);
    }

}
