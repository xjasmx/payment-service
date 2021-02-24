package com.test.paymentservice.application.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CreateClientWithAccountsDto {

    @NotBlank
    @Size(max = 255)
    private String firstName;

    @NotBlank
    @Size(max = 255)
    private String lastName;

    @NotEmpty
    private List<AccountDto> accounts;
}
