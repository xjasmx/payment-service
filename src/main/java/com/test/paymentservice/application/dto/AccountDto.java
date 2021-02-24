package com.test.paymentservice.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.test.paymentservice.domain.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {

    @JsonProperty("account_num")
    @NotBlank
    @Size(max = 255)
    private String number;

    @JsonProperty("account_type")
    @NotNull
    private AccountType type;

    @Digits(integer = 15, fraction = 2)
    private BigDecimal balance;
}
