package com.test.paymentservice.application.impl;

import com.test.paymentservice.application.PaymentQueryService;
import com.test.paymentservice.application.dto.FindPaymentsByCriteriaQueryDto;
import com.test.paymentservice.application.representation.ClientView;
import com.test.paymentservice.application.representation.PaymentView;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true)
public class PaymentQueryServiceImpl implements PaymentQueryService {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public PaymentQueryServiceImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<PaymentView> findAll(final FindPaymentsByCriteriaQueryDto paymentQuery) {
        final StringBuilder sql = new StringBuilder("""
                select p.id,
                       p.payment_on,
                       sa.account_number as account_source_number,
                       da.account_number as account_destination_number,
                       p.payment_amount,
                       pr.first_name as payer_first_name,
                       pr.last_name as payer_last_name,
                       rt.first_name as recipient_first_name,
                       rt.last_name as recipient_last_name
                from payments p
                         inner join (select id, id_client, account_number from accounts) da on da.id = p.id_account_destination
                         inner join (select id, id_client, account_number from accounts) sa on sa.id = p.id_account_source
                         inner join clients pr on pr.id = sa.id_client
                         inner join clients rt on rt.id = da.id_client
                 """);

        Map<String, Object> arguments = new HashMap<>();

        if (paymentQuery.getAccountSourceId() != null) {
            sql.append("and p.id_account_source = :accountSourceId ");
            arguments.put("accountSourceId", paymentQuery.getAccountSourceId());
        }

        if (paymentQuery.getAccountDestinationId() != null) {
            sql.append("and p.id_account_destination = :accountDestinationId ");
            arguments.put("accountDestinationId", paymentQuery.getAccountDestinationId());
        }

        if (paymentQuery.getPayerId() != null) {
            sql.append("and pr.id = :payerId ");
            arguments.put("payerId", paymentQuery.getPayerId());
        }

        if (paymentQuery.getRecipientId() != null) {
            sql.append("and rt.id = :recipientId ");
            arguments.put("recipientId", paymentQuery.getRecipientId());
        }

        if (arguments.isEmpty()) {
            return Collections.emptyList();
        }

        return jdbcTemplate.query(sql.toString(),
                arguments,
                ((rs, rowNum) ->
                        PaymentView.builder()
                                .id(rs.getInt("id"))
                                .payedOn(rs.getObject("payment_on", LocalDateTime.class))
                                .accountSourceNumber(rs.getString("account_source_number"))
                                .accountDestinationNumber(rs.getString("account_destination_number"))
                                .amount(rs.getBigDecimal("payment_amount"))
                                .payer(new ClientView(rs.getString("payer_first_name"), rs.getString("payer_last_name")))
                                .recipient(new ClientView(rs.getString("recipient_first_name"), rs.getString("recipient_last_name")))
                                .build())
        );
    }
}
