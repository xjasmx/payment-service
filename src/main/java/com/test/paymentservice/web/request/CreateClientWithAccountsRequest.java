package com.test.paymentservice.web.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.test.paymentservice.application.dto.AccountDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "create_client_with_accounts")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CreateClientWithAccountsRequest {

    @XmlElement(name = "first_name")
    @NotBlank
    @Size(max = 255)
    private String firstName;

    @XmlElement(name = "last_name")
    @NotBlank
    @Size(max = 255)
    private String lastName;

    @NotEmpty
    private List<AccountDto> accounts;
}
