package paymentsystem.dev.com.service;

import java.util.List;
import java.util.Map;
import paymentsystem.dev.com.dto.request.PaymentRequestDto;
import paymentsystem.dev.com.entity.Payment;

public interface PaymentService {
    Payment processPayment(PaymentRequestDto dto);

    List<Payment> getByParameters(Map<String, Long> parameters);
}
