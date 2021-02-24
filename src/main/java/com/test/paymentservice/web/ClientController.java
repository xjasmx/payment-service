package com.test.paymentservice.web;

import com.test.paymentservice.application.ClientQueryService;
import com.test.paymentservice.application.ClientService;
import com.test.paymentservice.application.dto.CreateClientWithAccountsDto;
import com.test.paymentservice.application.representation.ClientAccountView;
import com.test.paymentservice.web.request.CreateClientWithAccountsRequest;
import com.test.paymentservice.web.response.CollectionClientAccountsWrapper;
import com.test.paymentservice.web.response.CreateClientResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/clients")
public class ClientController {

    private final ClientService clientService;
    private final ClientQueryService clientQueryService;

    public ClientController(ClientService clientService, ClientQueryService clientQueryService) {
        this.clientService = clientService;
        this.clientQueryService = clientQueryService;
    }

    @PostMapping(
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public CreateClientResponse<Integer> create(@Valid @RequestBody CreateClientWithAccountsRequest request) {
        int id = clientService.createWithAccounts(
                new CreateClientWithAccountsDto(request.getFirstName(), request.getLastName(), request.getAccounts()));

        return new CreateClientResponse<>(id);
    }

    @GetMapping(value = "/{id}/get_accounts",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public List<ClientAccountView> findAllClientAccountsJson(@PathVariable int id) {
        return clientQueryService.findAllClientAccounts(id);
    }

    @GetMapping(value = "/{id}/get_accounts",
            produces = {MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public CollectionClientAccountsWrapper findAllClientAccountsXml(@PathVariable int id) {
        return new CollectionClientAccountsWrapper(clientQueryService.findAllClientAccounts(id));
    }
}
