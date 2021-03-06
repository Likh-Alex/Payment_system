package paymentsystem.dev.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import paymentsystem.dev.com.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
