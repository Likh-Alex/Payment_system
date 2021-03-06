package paymentsystem.dev.com.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import paymentsystem.dev.com.dto.request.PaymentRequestDto;
import paymentsystem.dev.com.entity.Account;
import paymentsystem.dev.com.entity.Payment;
import paymentsystem.dev.com.entity.PaymentStatus;
import paymentsystem.dev.com.repository.PaymentRepository;
import paymentsystem.dev.com.service.AccountService;
import paymentsystem.dev.com.service.PaymentService;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final AccountService accountService;
    private final PaymentRepository paymentRepository;
    private final EntityManager entityManager;
    private final Map<String, String> correctParameters = new LinkedHashMap<>();

    {
        correctParameters.put("payer_id", "payerId");
        correctParameters.put("recipient_id", "recipientId");
        correctParameters.put("source_acc_id", "sourceAccId");
        correctParameters.put("dest_acc_id", "destAccId");
    }

    @Override
    @Transactional
    public Payment processPayment(PaymentRequestDto dto) {
        Account sourceAccount = accountService.getById(dto.getSourceAccId());
        Account destAccount = accountService.getById(dto.getDestAccId());
        BigDecimal sourceAccountBalanceResult = sourceAccount.getBalance()
                .subtract(dto.getAmount());
        if (sourceAccountBalanceResult.compareTo(BigDecimal.ZERO) < 0) {
            return getErrorPayment(dto, destAccount, sourceAccount);
        }
        sourceAccount.setBalance(sourceAccount.getBalance().subtract(dto.getAmount()));
        destAccount.setBalance(destAccount.getBalance().add(dto.getAmount()));
        accountService.save(sourceAccount);
        accountService.save(destAccount);
        return getValidPayment(dto, sourceAccount, destAccount);
    }

    @Override
    public List<Payment> getByParameters(Map<String, Long> inputParameters) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        Map<String, Long> validParameters = getValidNamings(inputParameters);
        CriteriaQuery<Payment> criteriaQuery = criteriaBuilder
                .createQuery(Payment.class);
        Root<Payment> root = criteriaQuery.from(Payment.class);
        List<Predicate> andPredicates = new ArrayList<>();
        for (Map.Entry<String, Long> entry : validParameters.entrySet()) {
            andPredicates.add(root.get(entry.getKey()).in(entry.getValue()));
        }
        criteriaQuery.where(andPredicates.toArray(new Predicate[]{}));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    private Map<String, Long> getValidNamings(Map<String, Long> inputParameters) {
        Map<String, Long> validParameters = new LinkedHashMap<>();
        for (Map.Entry<String, Long> entry1 : inputParameters.entrySet()) {
            if (correctParameters.containsKey(entry1.getKey())) {
                String correctKey = correctParameters.get(entry1.getKey());
                validParameters.put(correctKey, entry1.getValue());
            }
        }
        return validParameters;
    }

    private Payment getValidPayment(PaymentRequestDto dto, Account destAccount,
                                    Account sourceAccount) {
        Payment validPayment = getPaymentObject(dto, sourceAccount, destAccount);
        validPayment.setStatus(PaymentStatus.OK);
        return paymentRepository.save(validPayment);
    }

    private Payment getErrorPayment(PaymentRequestDto dto, Account destAccount,
                                    Account sourceAccount) {
        Payment errorPayment = getPaymentObject(dto, destAccount, sourceAccount);
        errorPayment.setStatus(PaymentStatus.ERROR);
        return paymentRepository.save(errorPayment);
    }

    private Payment getPaymentObject(PaymentRequestDto dto, Account destAccount,
                                     Account sourceAccount) {
        Payment payment = new Payment();
        payment.setPayerId(sourceAccount.getClient().getId());
        payment.setSourceAccId(sourceAccount.getId());
        payment.setRecipientId(destAccount.getClient().getId());
        payment.setDestAccId(destAccount.getId());
        payment.setReason(dto.getReason());
        payment.setAccountFrom(sourceAccount);
        payment.setAccountTo(destAccount);
        payment.setAmount(dto.getAmount());
        payment.setDate(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        return payment;
    }
}
