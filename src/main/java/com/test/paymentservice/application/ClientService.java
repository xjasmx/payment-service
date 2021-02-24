package com.test.paymentservice.application;

import com.test.paymentservice.application.dto.CreateClientWithAccountsDto;
import com.test.paymentservice.domain.model.Client;

import javax.validation.Valid;

public interface ClientService {

    Client getEntityClient(final int id);

    int createWithAccounts(@Valid final CreateClientWithAccountsDto createClientWithAccountsDto);
}
