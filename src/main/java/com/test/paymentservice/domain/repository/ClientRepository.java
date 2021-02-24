package com.test.paymentservice.domain.repository;

import com.test.paymentservice.domain.model.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, Integer> {
}
