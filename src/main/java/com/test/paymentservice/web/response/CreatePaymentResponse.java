package com.test.paymentservice.web.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "create_payment_response")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreatePaymentResponse<T> {

    @XmlElement(name = "payment_id")
    @JsonProperty("payment_id")
    private T paymentId;
}
