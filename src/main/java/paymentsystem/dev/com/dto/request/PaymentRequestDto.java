package paymentsystem.dev.com.dto.request;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class PaymentRequestDto {
    private Long sourceAccId;
    private Long destAccId;
    private BigDecimal amount;
    private String reason;
}
