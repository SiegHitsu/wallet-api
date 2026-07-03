package com.paygo.wallet_api.wallet.service;

import com.paygo.wallet_api.transaction.entity.Transaction;
import com.paygo.wallet_api.transaction.entity.TransactionType;
import com.paygo.wallet_api.transaction.repository.TransactionRepository;
import com.paygo.wallet_api.wallet.dto.DepositRequest;
import com.paygo.wallet_api.wallet.dto.WalletResponse;
import com.paygo.wallet_api.wallet.entity.Wallet;
import com.paygo.wallet_api.wallet.repository.WalletRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class WalletService {
    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;

    @Transactional
    public WalletResponse deposit (Authentication authentication, DepositRequest depositRequest){
        String username = authentication.getName();
        Wallet wallet = walletRepository
                .findByUser_Username(username)
                .orElseThrow(() -> new RuntimeException("Wallet not found"));

        BigDecimal amount = depositRequest.amount();

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException(
                    "Amount must be greater than zero");
        }

        LocalDateTime updateAt = LocalDateTime.now();

        wallet.setBalance(wallet.getBalance().add(amount));
        wallet.setUpdatedAt(updateAt);

        Transaction transaction = Transaction.builder()
                .wallet(wallet)
                .amount(amount)
                .type(TransactionType.DEPOSIT)
                .description(depositRequest.description())
                .createdAt(updateAt)
                .build();

        transactionRepository.save(transaction);
        //walletRepository.save(wallet);

        return new WalletResponse(wallet.getBalance());

    }

}
