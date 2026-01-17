package pl.bank.s33840bank.api;

import java.math.BigDecimal;

public class TransactionResult {
    private Status status;
    private BigDecimal balance;

    public TransactionResult(Status status, BigDecimal balance) {
        this.status = status;
        this.balance = balance;
    }

    public Status getStatus() { return status; }
    public BigDecimal getBalance() { return balance; }
}
