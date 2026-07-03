package com.paygo.wallet_api.wallet.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record DepositRequest (
        @NotNull
        @DecimalMin(value = "0.01")
        BigDecimal amount,
        String description
){}
