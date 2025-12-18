package controller;

import dto.UserRegisterDTO;
import dto.UserResponseDTO;
import exception.UserRegisterException;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import service.UserService;

@Controller
@RequestMapping("/auth")
public class UserController {
    @Autowired
    private UserService userService;


   @PostMapping("/register")
    public ResponseEntity<?> userRegister(UserRegisterDTO userDTO){
       try{
           User user = userDTO.UserMap();
           User userRegister = userService.registerUser(user);

           return ResponseEntity.ok(
                   new UserResponseDTO(
                           userRegister.getUsername(),
                           userRegister.getRole())
           );
       }catch (UserRegisterException e){
            return ResponseEntity.badRequest().body(e.getMessage());
       } catch (Exception e) {
           return ResponseEntity.internalServerError().build();
       }
   }

}
