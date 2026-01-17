package pl.bank.s33840bank.api;

import java.math.BigDecimal;

public class AmountRequest {
    private BigDecimal amount;

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
}
