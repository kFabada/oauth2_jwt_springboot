package com.oauthjwt.fabada.service;

import com.oauthjwt.fabada.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.oauthjwt.fabada.repository.UserRepository;
import org.springframework.stereotype.Service;


@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User registerUser(User user){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
        String encoderPassword = encoder.encode(user.getPassword());
        user.setPassword(encoderPassword);
        return userRepository.save(user);
    }

}
