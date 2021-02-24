package com.test.paymentservice.application.impl;

import com.test.paymentservice.application.AccountService;
import com.test.paymentservice.domain.exception.NotFoundException;
import com.test.paymentservice.domain.model.Account;
import com.test.paymentservice.domain.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account getEntityAccount(final int id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Account with id '%s' is not found".formatted(id)));
    }
}
