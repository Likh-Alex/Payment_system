package paymentsystem.dev.com.service;

import java.util.Optional;
import paymentsystem.dev.com.entity.Client;

public interface ClientService {
    Client save(Client client);

    Optional<Client> getById(Long id);
}
