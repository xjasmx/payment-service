package com.test.paymentservice.web.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement(name = "create_payment")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CreatePaymentRequest {

    @XmlElement(name = "source_acc_id")
    @JsonProperty("source_acc_id")
    @Positive
    private int accountSourceId;

    @XmlElement(name = "dest_acc_id")
    @JsonProperty("dest_acc_id")
    @Positive
    private int accountDestinationId;

    @Digits(integer = 15, fraction = 2)
    private BigDecimal amount;

    @NotBlank
    @Size(max = 255)
    private String reason;
}
