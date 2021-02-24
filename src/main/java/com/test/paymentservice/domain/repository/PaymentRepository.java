package com.test.paymentservice.domain.repository;

import com.test.paymentservice.domain.model.Payment;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<Payment, Integer> {
}
