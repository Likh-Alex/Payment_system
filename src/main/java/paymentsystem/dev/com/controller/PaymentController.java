package paymentsystem.dev.com.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import paymentsystem.dev.com.dto.request.PaymentRequestDto;
import paymentsystem.dev.com.dto.response.PaymentExtendedResponseDto;
import paymentsystem.dev.com.dto.response.PaymentResponseDto;
import paymentsystem.dev.com.entity.Payment;
import paymentsystem.dev.com.service.PaymentService;
import paymentsystem.dev.com.service.mapper.PaymentMapper;

@RestController
@RequestMapping("/payments")
@AllArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;
    private final PaymentMapper paymentMapper;

    @PostMapping(path = "/one", consumes = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<PaymentResponseDto> save(@RequestBody PaymentRequestDto dto) {
        Payment payment = paymentService.processPayment(dto);
        PaymentResponseDto responseDto = paymentMapper.toDto(payment);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PostMapping(path = "/many", consumes = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<PaymentResponseDto>> saveMany(@RequestBody List<PaymentRequestDto>
                                                                     requestDtoList) {
        List<PaymentResponseDto> responseDtoList = new ArrayList<>();
        for (PaymentRequestDto dto : requestDtoList) {
            Payment payment = paymentService.processPayment(dto);
            PaymentResponseDto responseDto = paymentMapper.toDto(payment);
            responseDtoList.add(responseDto);
        }
        return new ResponseEntity<>(responseDtoList, HttpStatus.CREATED);
    }

    @GetMapping(path = "/parameters",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<PaymentExtendedResponseDto> getByParameters(@RequestBody Map<String,
            Long> inputParameters) {
        List<Payment> paymentList = paymentService.getByParameters(inputParameters);
        return paymentList.stream().map(paymentMapper::toExtendedDto).collect(Collectors.toList());
    }
}
