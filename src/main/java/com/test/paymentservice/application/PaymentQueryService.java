package com.test.paymentservice.application;

import com.test.paymentservice.application.dto.FindPaymentsByCriteriaQueryDto;
import com.test.paymentservice.application.representation.PaymentView;

import java.util.List;

public interface PaymentQueryService {

    List<PaymentView> findAll(final FindPaymentsByCriteriaQueryDto paymentQuery);
}
