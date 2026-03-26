package com.dev.ecommerce.Controller;

import com.dev.ecommerce.Service.UserService;
import com.dev.ecommerce.dto.LoginRequest;
import com.dev.ecommerce.dto.LoginResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        String token = userService.loginUser(
                request.getEmail(),
                request.getPassword()
        );

        return new LoginResponse(token);
    }
}
