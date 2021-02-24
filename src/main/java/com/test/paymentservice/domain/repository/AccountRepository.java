package com.test.paymentservice.domain.repository;

import com.test.paymentservice.application.representation.ClientAccountView;
import com.test.paymentservice.domain.model.Account;
import com.test.paymentservice.domain.model.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountRepository extends CrudRepository<Account, Integer> {

    List<ClientAccountView> findProjectedByClient(Client client);
}
