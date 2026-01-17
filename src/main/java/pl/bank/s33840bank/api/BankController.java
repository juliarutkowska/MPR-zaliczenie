package pl.bank.s33840bank.api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.bank.s33840bank.model.Client;
import pl.bank.s33840bank.service.BankService;

import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class BankController {

    private final BankService service;

    public BankController(BankService service) {
        this.service = service;
    }

    @PostMapping
    public Client register(@RequestBody RegisterRequest req) {
        return service.register(req.getInitialBalance());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> get(@PathVariable long id) {
        Optional<Client> c = service.getClient(id);
        return c.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/deposit")
    public TransactionResult deposit(@PathVariable long id,
                                     @RequestBody AmountRequest req) {
        return service.deposit(id, req.getAmount());
    }

    @PostMapping("/{id}/transfer")
    public TransactionResult transfer(@PathVariable long id,
                                    @RequestBody AmountRequest req) {
        return service.transfer(id, req.getAmount());
    }
}
