package paymentsystem.dev.com.service;

import java.util.List;
import paymentsystem.dev.com.entity.Account;

public interface AccountService {
    Account save(Account account);

    Account getById(Long id);

    List<Account> getAccountsByClientId(Long id);

    List<Account> saveAll(List<Account> accountList);
}
