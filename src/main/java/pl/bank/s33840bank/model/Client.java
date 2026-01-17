package pl.bank.s33840bank.model;

import java.math.BigDecimal;

public class Client {
    private final long id;
    private BigDecimal balance;

    public Client(long id, BigDecimal balance) {
        this.id = id;
        this.balance = balance;
    }

    public long getId() { return id; }
    public BigDecimal getBalance() { return balance; }
    public void setBalance(BigDecimal balance) { this.balance = balance; }
}
