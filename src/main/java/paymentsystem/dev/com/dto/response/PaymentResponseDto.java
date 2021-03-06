package paymentsystem.dev.com.dto.response;

import lombok.Data;
import paymentsystem.dev.com.entity.PaymentStatus;

@Data
public class PaymentResponseDto {
    private Long paymentId;
    private PaymentStatus status;
}
