package com.oauthjwt.fabada.controller;

import com.oauthjwt.fabada.dto.UserRegisterDTO;
import com.oauthjwt.fabada.dto.UserResponseDTO;
import com.oauthjwt.fabada.exception.UserRegisterException;
import com.oauthjwt.fabada.model.User;
import com.oauthjwt.fabada.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.oauthjwt.fabada.service.UserService;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserController {
    @Autowired
    private IUserService userService;

   @PostMapping("/register")
    public ResponseEntity<?> userRegister(@RequestBody UserRegisterDTO userDTO){
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
