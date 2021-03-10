package paymentsystem.dev.com.dto.request;

import java.util.List;
import lombok.Data;
import paymentsystem.dev.com.entity.Account;

@Data
public class ClientRequestDto {
    private String firstName;
    private String lastName;
    private List<Account> accounts;
}
