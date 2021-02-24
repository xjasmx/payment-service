package com.test.paymentservice.application.impl;

import com.test.paymentservice.application.ClientService;
import com.test.paymentservice.application.dto.CreateClientWithAccountsDto;
import com.test.paymentservice.domain.exception.NotFoundException;
import com.test.paymentservice.domain.model.Account;
import com.test.paymentservice.domain.model.Client;
import com.test.paymentservice.domain.repository.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Validated
@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client getEntityClient(final int id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Client with id '%s' is not found".formatted(id)));
    }

    @Override
    public int createWithAccounts(@Valid final CreateClientWithAccountsDto clientWithAccountsDto) {

        final Client client = Client.builder()
                .firstName(clientWithAccountsDto.getFirstName())
                .lastName(clientWithAccountsDto.getLastName())
                .build();

        clientWithAccountsDto.getAccounts()
                .forEach(a -> {
                    final Account account = Account.builder()
                            .number(a.getNumber())
                            .type(a.getType())
                            .balance(a.getBalance())
                            .build();

                    client.addAccount(account);
                });

        clientRepository.save(client);

        return client.getId();
    }
}
