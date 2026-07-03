package com.paygo.wallet_api.auth.service;

import com.paygo.wallet_api.auth.dto.LoginRequest;
import com.paygo.wallet_api.auth.dto.AuthResponse;
import com.paygo.wallet_api.auth.dto.RegisterRequest;
import com.paygo.wallet_api.auth.security.JwtService;
import com.paygo.wallet_api.user.entity.Role;
import com.paygo.wallet_api.user.repository.UserRepository;
import com.paygo.wallet_api.wallet.entity.Wallet;
import com.paygo.wallet_api.wallet.repository.WalletRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.paygo.wallet_api.user.entity.User;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final WalletRepository walletRepository;

    /*public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }*/

    @Transactional
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.username())) {
            throw new RuntimeException("Username already exists");
        }

        if (request.email() != null && userRepository.existsByEmail(request.email())) {
            throw new RuntimeException("Email already exists");
        }

        User user = User.builder()
                .username(request.username())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .fullName(request.fullName())
                .role(Role.USER)
                .createdAt(LocalDateTime.now())
                .build();

        /*
        User user = new User();

        user.setUsername(request.username());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setFullName(request.fullName());
        user.setRole(Role.USER);
        user.setCreatedAt(LocalDateTime.now());

         */

        User savedUser = userRepository.save(user);

        LocalDateTime localDateTime = LocalDateTime.now();
        Wallet wallet = Wallet.builder()
                .balance(BigDecimal.ZERO)
                .user(savedUser)
                .createdAt(localDateTime)
                .updatedAt(localDateTime)
                .build();

        walletRepository.save(wallet);

        String token = jwtService.generateToken(savedUser);

        return new AuthResponse(token);
    }

    public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        boolean passwordMatches = passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        );

        if (!passwordMatches) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtService.generateToken(user);

        return new AuthResponse(token);
    }
}
