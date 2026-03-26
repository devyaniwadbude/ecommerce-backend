package com.dev.ecommerce.Service;

import com.dev.ecommerce.model.User;
import com.dev.ecommerce.Repository.UserRepository;
import com.dev.ecommerce.security.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserService(UserRepository userRepository,
                       BCryptPasswordEncoder passwordEncoder,JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }
    // Register User
    public User registerUser(User user) {

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        return userRepository.save(user);
    }

    // Get All Users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Get User By Id
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    // Delete User
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    public String loginUser(String email, String password) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if(passwordEncoder.matches(password, user.getPassword())){
            return jwtUtil.generateToken(user.getEmail());
        } else {
            throw new RuntimeException("Invalid password");
        }
    }
}
