package paymentsystem.dev.com.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import paymentsystem.dev.com.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query("FROM Account a JOIN FETCH a.client c WHERE c.id = ?1")
    List<Account> findAllByClientId(Long id);

    Optional<Account> findAccountById(Long id);
}
