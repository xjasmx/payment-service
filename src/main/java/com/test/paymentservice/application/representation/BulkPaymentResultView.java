package com.test.paymentservice.application.representation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.test.paymentservice.domain.enums.PaymentStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "bulk_payment_result")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@NoArgsConstructor
@ToString
public class BulkPaymentResultView {

    @XmlElement(name = "payment_id")
    @JsonProperty("payment_id")
    private int id;

    @XmlElement
    private String status;

    public BulkPaymentResultView(int id, PaymentStatus status) {
        this.id = id;
        this.status = status.name().toLowerCase();
    }
}
