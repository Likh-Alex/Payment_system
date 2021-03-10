package paymentsystem.dev.com.dto.response;

import java.math.BigDecimal;
import lombok.Data;
import paymentsystem.dev.com.entity.AccountType;

@Data
public class AccountResponseDto {
    private Long accountId;
    private String accountNumber;
    private AccountType accountType;
    private BigDecimal balance;
}
