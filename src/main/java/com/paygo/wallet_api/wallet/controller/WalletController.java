package com.paygo.wallet_api.wallet.controller;

import com.paygo.wallet_api.wallet.dto.DepositRequest;
import com.paygo.wallet_api.wallet.dto.WalletResponse;
import com.paygo.wallet_api.wallet.service.WalletService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wallet")
@RequiredArgsConstructor
public class WalletController {
    private final WalletService walletService;

    @PostMapping("/deposit")
    public WalletResponse deposit(Authentication authentication, @Valid @RequestBody DepositRequest depositRequest){
        return walletService.deposit(authentication, depositRequest);
    }
}
