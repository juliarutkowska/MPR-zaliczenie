package pl.bank.s33840bank.store;

import org.springframework.stereotype.Component;
import pl.bank.s33840bank.model.Client;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class ClientStore {

    private final Map<Long, Client> db = new HashMap<>();

    public Client save(Client client) {
        db.put(client.getId(), client);
        return client;
    }

    public Optional<Client> findById(long id) {
        return Optional.ofNullable(db.get(id));
    }
}
