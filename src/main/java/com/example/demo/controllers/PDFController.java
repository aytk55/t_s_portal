package com.example.demo.controllers;

import com.example.demo.entitites.PDF;
import com.example.demo.jwt.config.JwtService;
import com.example.demo.jwt.user.User;
import com.example.demo.jwt.user.UserRepository;
import com.example.demo.services.PDFService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/pdf")
public class PDFController {

    @Autowired
    private PDFService pdfService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/student/{userId}")
    public ResponseEntity<List<PDF>> getPDFByStudentId(@PathVariable Integer userId,
                                                       @RequestHeader("Authorization") String jwtToken) {
        if (jwtToken != null && jwtToken.startsWith("Bearer ")) {
            // Extract the token value after "Bearer "
            String token = jwtToken.substring(7);

            // Rest of your code remains the same
            String username = jwtService.extractUsername(token);
            Optional<User> userOptional = userRepository.findById(userId);

            if (userOptional.isPresent()) {
                User user = userOptional.get();
                String check = user.getUsername();

                if (username.equals(check)) {
                    List<PDF> pdfList = pdfService.findByStudentId(userId);
                    return ResponseEntity.ok(pdfList);
                } else {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
                }
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } else {
            // Handle case where Authorization header is missing or doesn't start with "Bearer "
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }



    @PostMapping("/add")
    public void addPDFtoStudent(

            @RequestParam String filename,
            @RequestParam String content,
            @RequestParam Integer userId) {
        pdfService.addPDFtoUser(filename, content, userId);
    }
}
