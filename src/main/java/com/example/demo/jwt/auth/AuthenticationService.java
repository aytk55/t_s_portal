package com.example.demo.jwt.auth;



import com.example.demo.jwt.config.JwtService;
import com.example.demo.jwt.user.Role;
import com.example.demo.jwt.user.User;
import com.example.demo.jwt.user.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(PasswordEncoder passwordEncoder,UserRepository userRepository,  JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder=passwordEncoder;
    }

    public AuthenticationResponse register(AuthenticationRequest request) {
        User user =new User();
        user.setFirstname("demented");
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setUsername(request.getUsername());
        user.setRole(Role.USER);
        userRepository.save(user);
        var jwtToken=jwtService.generateToken(user);
        return new AuthenticationResponse(jwtToken);



    }


    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword())
        );
        User user= userRepository.findByUsername(request.getUsername()).orElseThrow();
        var jwtToken=jwtService.generateToken(user);
        return new AuthenticationResponse(jwtToken);

    }
}
