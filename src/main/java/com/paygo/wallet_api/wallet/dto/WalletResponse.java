package com.paygo.wallet_api.wallet.dto;

import java.math.BigDecimal;

public record WalletResponse (BigDecimal balance) {
}
