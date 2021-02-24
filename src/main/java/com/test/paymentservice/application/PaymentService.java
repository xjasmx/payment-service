package com.test.paymentservice.application;

import com.test.paymentservice.application.dto.CreatePaymentDto;
import com.test.paymentservice.application.representation.BulkPaymentResultView;

import javax.validation.Valid;
import java.util.List;

public interface PaymentService {

    int create(@Valid final CreatePaymentDto command);

    List<BulkPaymentResultView> bulkCreate(@Valid final List<CreatePaymentDto> createPaymentDtos);
}
