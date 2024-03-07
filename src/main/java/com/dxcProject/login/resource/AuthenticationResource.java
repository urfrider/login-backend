package com.dxcProject.login.resource;

import com.dxcProject.login.entity.AuthenticationResponse;
import com.dxcProject.login.entity.LoginRequest;
import com.dxcProject.login.entity.RegisterRequest;
import com.dxcProject.login.entity.User;
import com.dxcProject.login.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/api/auth")
@RequiredArgsConstructor
public class AuthenticationResource {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ){
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody LoginRequest request
    ){
        return ResponseEntity.ok(authenticationService.login(request));
    }

    @GetMapping("/account")
    public ResponseEntity<User> returnUser(@RequestHeader("Authorization") String token){
        String jwt = token.substring(7);
        User user = authenticationService.findUser(jwt);

        return ResponseEntity.ok(user);
    }

}
