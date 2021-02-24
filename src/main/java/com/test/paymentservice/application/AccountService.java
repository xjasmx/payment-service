package com.test.paymentservice.application;

import com.test.paymentservice.domain.model.Account;

public interface AccountService {

    Account getEntityAccount(final int id);
}
