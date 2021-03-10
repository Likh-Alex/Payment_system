package paymentsystem.dev.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import paymentsystem.dev.com.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
