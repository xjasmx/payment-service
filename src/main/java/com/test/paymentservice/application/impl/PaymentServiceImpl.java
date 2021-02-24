package com.test.paymentservice.application.impl;

import com.test.paymentservice.application.AccountService;
import com.test.paymentservice.application.PaymentService;
import com.test.paymentservice.application.dto.CreatePaymentDto;
import com.test.paymentservice.application.representation.BulkPaymentResultView;
import com.test.paymentservice.domain.enums.PaymentStatus;
import com.test.paymentservice.domain.exception.InsufficientFundException;
import com.test.paymentservice.domain.model.Account;
import com.test.paymentservice.domain.model.Payment;
import com.test.paymentservice.domain.repository.PaymentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Validated
@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final AccountService accountService;

    public PaymentServiceImpl(PaymentRepository paymentRepository,
                              AccountService accountService) {
        this.paymentRepository = paymentRepository;
        this.accountService = accountService;
    }

    @Transactional(noRollbackFor = InsufficientFundException.class)
    @Override
    public int create(@Valid final CreatePaymentDto createPaymentDto) {
        final Payment payment = transferAndBuildPayment(createPaymentDto);

        paymentRepository.save(payment);

        if (PaymentStatus.ERROR.equals(payment.getStatus())) {
            throw new InsufficientFundException("Insufficient funds in the account with id '%s'"
                    .formatted(createPaymentDto.getAccountSourceId()));
        }

        return payment.getId();
    }

    @Override
    public List<BulkPaymentResultView> bulkCreate(@Valid final List<CreatePaymentDto> createPaymentDtos) {
        final List<Payment> payments = createPaymentDtos.stream()
                .map(this::transferAndBuildPayment)
                .collect(toList());

        paymentRepository.saveAll(payments);

        return payments.stream()
                .map(p -> new BulkPaymentResultView(p.getId(), p.getStatus()))
                .collect(toList());
    }

    private Payment transferAndBuildPayment(@Valid final CreatePaymentDto createPaymentDto) {
        final Account accountDestination = accountService.getEntityAccount(createPaymentDto.getAccountDestinationId());
        final Account accountSource = accountService.getEntityAccount(createPaymentDto.getAccountSourceId());

        final Payment.PaymentBuilder paymentBuilder = Payment.builder()
                .amount(createPaymentDto.getAmount())
                .reason(createPaymentDto.getReason())
                .paymentOn(LocalDateTime.now())
                .accountDestination(accountDestination)
                .accountSource(accountSource);

        transfer(createPaymentDto.getAmount(), accountDestination, accountSource, paymentBuilder);

        return paymentBuilder.build();
    }

    private void transfer(final BigDecimal amount,
                          final Account accountDestination,
                          final Account accountSource,
                          final Payment.PaymentBuilder paymentBuilder) {
        BigDecimal withdraw = accountSource.getBalance().subtract(amount);

        if (accountSource.getBalance().compareTo(BigDecimal.ZERO) < 0 || withdraw.compareTo(BigDecimal.ZERO) < 0) {
            paymentBuilder.status(PaymentStatus.ERROR);
        } else {
            accountSource.setBalance(withdraw);

            BigDecimal deposit = accountDestination.getBalance().add(amount);
            accountDestination.setBalance(deposit);

            paymentBuilder.status(PaymentStatus.OK);
        }
    }
}
