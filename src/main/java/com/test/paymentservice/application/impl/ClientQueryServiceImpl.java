package com.test.paymentservice.application.impl;

import com.test.paymentservice.application.ClientQueryService;
import com.test.paymentservice.application.representation.ClientAccountView;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Types;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ClientQueryServiceImpl implements ClientQueryService {

    private final JdbcTemplate jdbcTemplate;

    public ClientQueryServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<ClientAccountView> findAllClientAccounts(final int clientId) {
        final String sql = """
                select id, account_number, account_type, balance
                from accounts
                where id_client = ?;
                """;

        return jdbcTemplate.query(sql, new Object[]{clientId}, new int[]{Types.INTEGER}, (rs, rowNum) ->
                new ClientAccountView(rs.getInt("id"),
                        rs.getString("account_number"),
                        rs.getString("account_type").toLowerCase(),
                        rs.getBigDecimal("balance")));
    }
}
