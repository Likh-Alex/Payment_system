package paymentsystem.dev.com.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import paymentsystem.dev.com.dto.response.AccountResponseDto;
import paymentsystem.dev.com.entity.Client;
import paymentsystem.dev.com.service.AccountService;
import paymentsystem.dev.com.service.ClientService;
import paymentsystem.dev.com.service.mapper.AccountMapper;

@RestController
@RequestMapping("/accounts")
@AllArgsConstructor
public class AccountController {
    private final AccountService accountService;
    private final AccountMapper accountMapper;
    private final ClientService clientService;

    @GetMapping("/{id}")
    public ResponseEntity<List<AccountResponseDto>> getAccountsById(@PathVariable final Long id) {
        Optional<Client> clientById = clientService.getById(id);
        if (clientById.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<AccountResponseDto> accountResponseDtoList = accountService
                .getAccountsByClientId(id)
                .stream()
                .map(accountMapper::toDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(accountResponseDtoList, HttpStatus.OK);
    }
}
