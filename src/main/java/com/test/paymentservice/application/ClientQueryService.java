package com.test.paymentservice.application;

import com.test.paymentservice.application.representation.ClientAccountView;

import java.util.List;

public interface ClientQueryService {

    List<ClientAccountView> findAllClientAccounts(final int clientId);
}
