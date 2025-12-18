package service;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import repository.UserRepository;

public class UserService {
    @Autowired
    private UserRepository userRepository;


    public User registerUser(User user){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
        String encoderPassword = encoder.encode(user.getPassword());
        user.setPassword(encoderPassword);
        return userRepository.save(user);
    }
}
