package com.manos.userloginform.controller;


import com.manos.userloginform.dto.AuthResponse;
import com.manos.userloginform.dto.LoginRequest;
import com.manos.userloginform.dto.RegisterRequest;
import com.manos.userloginform.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request){
        //System.out.println("----> REGISTER CALLED");
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
       return ResponseEntity.ok(authService.login(request));
    }


}
