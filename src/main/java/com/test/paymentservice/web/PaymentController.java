package com.test.paymentservice.web;

import com.test.paymentservice.application.PaymentQueryService;
import com.test.paymentservice.application.PaymentService;
import com.test.paymentservice.application.dto.CreatePaymentDto;
import com.test.paymentservice.application.dto.FindPaymentsByCriteriaQueryDto;
import com.test.paymentservice.application.representation.BulkPaymentResultView;
import com.test.paymentservice.application.representation.PaymentView;
import com.test.paymentservice.web.request.CreatePaymentRequest;
import com.test.paymentservice.web.response.CollectionBulkCreatePaymentsWrapper;
import com.test.paymentservice.web.response.CollectionPaymentsWrapper;
import com.test.paymentservice.web.response.CreatePaymentResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;
    private final PaymentQueryService paymentQueryService;

    public PaymentController(PaymentService paymentService, PaymentQueryService paymentQueryService) {
        this.paymentService = paymentService;
        this.paymentQueryService = paymentQueryService;
    }

    @PostMapping(value = "/find_by_criteria",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public List<PaymentView> findAllJson(@RequestBody FindPaymentsByCriteriaQueryDto paymentQuery) {
        return paymentQueryService.findAll(paymentQuery);
    }

    @PostMapping(value = "/find_by_criteria",
            produces = {MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public CollectionPaymentsWrapper findAllXml(@RequestBody FindPaymentsByCriteriaQueryDto paymentQuery) {
        return new CollectionPaymentsWrapper(paymentQueryService.findAll(paymentQuery));
    }

    @PostMapping(
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public CreatePaymentResponse<Integer> transferAndCreatePayment(@Valid @RequestBody CreatePaymentRequest request) {
        int id = paymentService.create(
                new CreatePaymentDto(request.getAccountSourceId(), request.getAccountDestinationId(), request.getAmount(), request.getReason()));

        return new CreatePaymentResponse<>(id);
    }

    @PostMapping(value = "/bulk_create",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public List<BulkPaymentResultView> bulkCreateJson(@Valid @RequestBody List<CreatePaymentRequest> request) {
        final List<CreatePaymentDto> paymentDtos = request.stream()
                .map(pr -> new CreatePaymentDto(pr.getAccountSourceId(), pr.getAccountDestinationId(), pr.getAmount(), pr.getReason()))
                .collect(toList());

        return paymentService.bulkCreate(paymentDtos);
    }

    @PostMapping(value = "/bulk_create",
            produces = {MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public CollectionBulkCreatePaymentsWrapper bulkCreateXml(@Valid @RequestBody List<CreatePaymentRequest> request) {
        final List<CreatePaymentDto> paymentDtos = request.stream()
                .map(pr -> new CreatePaymentDto(pr.getAccountSourceId(), pr.getAccountDestinationId(), pr.getAmount(), pr.getReason()))
                .collect(toList());

        return new CollectionBulkCreatePaymentsWrapper(paymentService.bulkCreate(paymentDtos));
    }
}
