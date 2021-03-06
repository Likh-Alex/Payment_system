package paymentsystem.dev.com.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import lombok.Data;

@Data
public class PaymentExtendedResponseDto {
    private Long paymentId;
    private LocalDateTime timeStamp;
    private String srcAccNum;
    private String destAccNum;
    private BigDecimal amount;
    private Map<String, String> payer;
    private Map<String, String> recipient;
}
