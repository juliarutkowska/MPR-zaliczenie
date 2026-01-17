package pl.bank.s33840bank.api;

import java.math.BigDecimal;

public class RegisterRequest {
    private BigDecimal initialBalance;

    public BigDecimal getInitialBalance() { return initialBalance; }
    public void setInitialBalance(BigDecimal initialBalance) {
        this.initialBalance = initialBalance;
    }
}
