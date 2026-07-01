package com.paygo.wallet_api.auth.controller;

import com.paygo.wallet_api.auth.dto.LoginRequest;
import com.paygo.wallet_api.auth.dto.LoginResponse;
import com.paygo.wallet_api.auth.dto.RegisterRequest;
import com.paygo.wallet_api.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@Valid @RequestBody RegisterRequest request){
        authService.register(request);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.CREATED)
    public LoginResponse register(@Valid @RequestBody LoginRequest request){
        return authService.login(request);
    }

    @GetMapping("/me")
    public String me(Authentication authentication){
        System.out.println("asd");
        return authentication.getName();
    }
}
