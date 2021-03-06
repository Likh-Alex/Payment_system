package paymentsystem.dev.com.service.mapper;

import org.springframework.stereotype.Component;
import paymentsystem.dev.com.dto.response.AccountResponseDto;
import paymentsystem.dev.com.entity.Account;

@Component
public class AccountMapper {
    public AccountResponseDto toDto(Account account) {
        AccountResponseDto dto = new AccountResponseDto();
        dto.setAccountId(account.getId());
        dto.setAccountNumber(account.getAccountNumber());
        dto.setAccountType(account.getAccountType());
        dto.setBalance(account.getBalance());
        return dto;
    }
}
