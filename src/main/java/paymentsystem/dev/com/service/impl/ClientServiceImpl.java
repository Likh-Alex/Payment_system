package paymentsystem.dev.com.service.impl;

import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import paymentsystem.dev.com.entity.Client;
import paymentsystem.dev.com.repository.ClientRepository;
import paymentsystem.dev.com.service.ClientService;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    @Override
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Optional<Client> getById(Long id) {
        return clientRepository.findById(id);
    }
}
