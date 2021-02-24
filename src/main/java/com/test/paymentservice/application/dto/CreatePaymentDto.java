package com.test.paymentservice.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CreatePaymentDto {

    @Positive
    private int accountSourceId;

    @Positive
    private int accountDestinationId;

    @Digits(integer = 15, fraction = 2)
    private BigDecimal amount;

    @NotBlank
    private String reason;
}
