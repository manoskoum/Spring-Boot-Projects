package com.manos.userloginform.service;

import com.manos.userloginform.dto.AuthResponse;
import com.manos.userloginform.dto.LoginRequest;
import com.manos.userloginform.dto.RegisterRequest;
import com.manos.userloginform.model.User;
import com.manos.userloginform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwt;


    public AuthResponse register(RegisterRequest request){

        System.out.println("RegisterRequest received: " + request);

        if (userRepository.findByEmail(request.getEmail()).isPresent()){
            throw new RuntimeException("User already exists");
        }

        User user=new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);

        System.out.println("Generating token for user: " + user.getEmail());
        String token = jwt.generateToken(user);
        System.out.println("Token: " + token);

        return new AuthResponse(token);
    }

    public AuthResponse login(LoginRequest request) {

        Optional<User> userOptional=userRepository.findByEmail(request.getEmail());

        if(userOptional.isEmpty()){
            throw new RuntimeException("Invalid email or password");
        }

        User user=userOptional.get();

        if(!passwordEncoder.matches(request.getPassword(),user.getPassword())){
            throw new RuntimeException("Invalid email or password");
        }

        String token= jwt.generateToken(user);

        return new AuthResponse(token);
    }
}
