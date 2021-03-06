package paymentsystem.dev.com.service.mapper;

import org.springframework.stereotype.Component;
import paymentsystem.dev.com.dto.request.ClientRequestDto;
import paymentsystem.dev.com.dto.response.ClientResponseDto;
import paymentsystem.dev.com.entity.Client;

@Component
public class ClientMapper {
    public Client toEntity(ClientRequestDto dto) {
        Client client = new Client();
        client.setLastName(dto.getLastName());
        client.setFirstName(dto.getFirstName());
        client.setAccountList(dto.getAccounts());
        return client;
    }

    public ClientResponseDto toDto(Client client) {
        ClientResponseDto dto = new ClientResponseDto();
        dto.setClientId(client.getId());
        return dto;
    }
}
