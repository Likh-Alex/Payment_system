package paymentsystem.dev.com.service.mapper;

import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.stereotype.Component;
import paymentsystem.dev.com.dto.response.PaymentExtendedResponseDto;
import paymentsystem.dev.com.dto.response.PaymentResponseDto;
import paymentsystem.dev.com.entity.Payment;
import paymentsystem.dev.com.entity.PaymentStatus;

@Component
public class PaymentMapper {

    public static final String LAST_NAME = "last_name";
    public static final String FIRST_NAME = "first_name";

    public PaymentResponseDto toDto(Payment payment) {
        PaymentResponseDto dto = new PaymentResponseDto();
        dto.setPaymentId(payment.getId());
        dto.setStatus(PaymentStatus.valueOf(String.valueOf(payment.getStatus())));
        return dto;
    }

    public PaymentExtendedResponseDto toExtendedDto(Payment payment) {
        PaymentExtendedResponseDto dto = new PaymentExtendedResponseDto();
        dto.setPaymentId(payment.getId());
        dto.setTimeStamp(payment.getDate());
        dto.setSrcAccNum(payment.getAccountFrom().getAccountNumber());
        dto.setDestAccNum(payment.getAccountTo().getAccountNumber());
        dto.setAmount(payment.getAmount());

        Map<String, String> payerData = new LinkedHashMap<>();
        payerData.put(FIRST_NAME, payment.getAccountFrom().getClient().getFirstName());
        payerData.put(LAST_NAME, payment.getAccountFrom().getClient().getLastName());
        dto.setPayer(payerData);

        Map<String, String> recipientData = new LinkedHashMap<>();
        recipientData.put(FIRST_NAME, payment.getAccountTo().getClient().getFirstName());
        recipientData.put(LAST_NAME, payment.getAccountTo().getClient().getLastName());
        dto.setRecipient(recipientData);
        return dto;
    }
}
