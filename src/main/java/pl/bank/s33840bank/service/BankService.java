package pl.bank.s33840bank.service;

import org.springframework.stereotype.Service;
import pl.bank.s33840bank.api.TransactionResult;
import pl.bank.s33840bank.api.Status;
import pl.bank.s33840bank.model.Client;
import pl.bank.s33840bank.store.ClientStore;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class BankService {

    private final ClientStore store;
    private long nextId = 1;

    public BankService(ClientStore store) {
        this.store = store;
    }

    public Client register(BigDecimal initialBalance) {
        BigDecimal start = initialBalance == null ? BigDecimal.ZERO : initialBalance;
        Client c = new Client(nextId++, start);
        return store.save(c);
    }

    public Optional<Client> getClient(long id) {
        return store.findById(id);
    }

    public TransactionResult deposit(long id, BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            return new TransactionResult(Status.DECLINED, null);
        }

        Optional<Client> opt = store.findById(id);
        if (opt.isEmpty()) {
            return new TransactionResult(Status.DECLINED, null);
        }

        Client c = opt.get();
        c.setBalance(c.getBalance().add(amount));
        store.save(c);
        return new TransactionResult(Status.ACCEPTED, c.getBalance());
    }

    public TransactionResult transfer(long id, BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            return new TransactionResult(Status.DECLINED, null);
        }

        Optional<Client> opt = store.findById(id);
        if (opt.isEmpty()) {
            return new TransactionResult(Status.DECLINED, null);
        }

        Client c = opt.get();
        if (c.getBalance().compareTo(amount) < 0) {
            return new TransactionResult(Status.DECLINED, c.getBalance());
        }

        c.setBalance(c.getBalance().subtract(amount));
        store.save(c);
        return new TransactionResult(Status.ACCEPTED, c.getBalance());
    }
}
