package paymentsystem.dev.com.controller;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import paymentsystem.dev.com.dto.request.ClientRequestDto;
import paymentsystem.dev.com.dto.response.ClientResponseDto;
import paymentsystem.dev.com.entity.Account;
import paymentsystem.dev.com.entity.Client;
import paymentsystem.dev.com.service.AccountService;
import paymentsystem.dev.com.service.ClientService;
import paymentsystem.dev.com.service.mapper.ClientMapper;

@RestController
@RequestMapping("/clients")
@AllArgsConstructor
public class ClientController {
    private final ClientService clientService;
    private final ClientMapper clientMapper;
    private final AccountService accountService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<ClientResponseDto> save(@RequestBody ClientRequestDto dto) {
        Client newClient = clientMapper.toEntity(dto);
        newClient.setAccountList(accountService.saveAll(newClient.getAccountList()));
        Client savedClient = clientService.save(newClient);

        List<Account> accountList = savedClient.getAccountList();
        accountList.forEach(account -> account.setClient(newClient));
        accountService.saveAll(accountList);
        return new ResponseEntity<>(clientMapper.toDto(savedClient), HttpStatus.CREATED);
    }
}
