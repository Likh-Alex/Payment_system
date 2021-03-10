package paymentsystem.dev.com.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.sun.istack.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "payment")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String reason;
    private Long payerId;
    private Long recipientId;
    private Long sourceAccId;
    private Long destAccId;
    @JoinColumn(name = "account_from", nullable = false)
    @ManyToOne
    private Account accountFrom;
    @JoinColumn(name = "account_to", nullable = false)
    @ManyToOne
    private Account accountTo;
    @NotNull
    private BigDecimal amount;
    @Column(nullable = false)
    private LocalDateTime date;
    private PaymentStatus status;
}
