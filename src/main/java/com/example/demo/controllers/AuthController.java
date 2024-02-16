package com.example.demo.controllers;

import com.example.demo.requests.LoginRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login/teacher")
    public ResponseEntity<String> loginAsTeacher(@RequestBody LoginRequest loginRequest) {
        if (authService.loginAsTeacher(loginRequest)) {
            return new ResponseEntity<>("Teacher logged in successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/login/student")
    public ResponseEntity<String> loginAsStudent(@RequestBody LoginRequest loginRequest) {
        // Your implementation for student login
        // ...

        return new ResponseEntity<>("Student logged in successfully", HttpStatus.OK);
    }

    @PostMapping("/register/student")
    public ResponseEntity<String> registerAsStudent(@RequestBody RegisterRequest registerRequest) {
        // Your implementation for student registration
        // ...

        return new ResponseEntity<>("Student registered successfully", HttpStatus.OK);
    }
}
