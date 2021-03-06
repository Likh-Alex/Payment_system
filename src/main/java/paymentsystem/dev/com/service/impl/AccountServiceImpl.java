package paymentsystem.dev.com.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import paymentsystem.dev.com.entity.Account;
import paymentsystem.dev.com.repository.AccountRepository;
import paymentsystem.dev.com.service.AccountService;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account getById(Long id) {
        return accountRepository.findAccountById(id).orElseThrow(()
                -> new NoSuchElementException("Can not find account with ID: " + id));
    }

    @Override
    public List<Account> getAccountsByClientId(Long id) {
        return accountRepository.findAllByClientId(id);
    }

    @Override
    public List<Account> saveAll(List<Account> accountList) {
        return accountRepository.saveAll(accountList);
    }
}
