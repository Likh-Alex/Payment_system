package paymentsystem.dev.com.entity;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "number", nullable = false, unique = true)
    private String accountNumber;
    @Column(name = "type")
    private AccountType accountType;
    private BigDecimal balance;
    @ManyToOne
    @JoinColumn
    private Client client;
}
