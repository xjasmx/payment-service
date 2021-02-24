package com.test.paymentservice.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "find_payments_by_criteria")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FindPaymentsByCriteriaQueryDto {

    @XmlElement(name = "payer_id")
    @JsonProperty("payer_id")
    private Integer payerId;

    @XmlElement(name = "recipient_id")
    @JsonProperty("recipient_id")
    private Integer recipientId;

    @XmlElement(name = "source_acc_id")
    @JsonProperty("source_acc_id")
    private Integer accountSourceId;

    @XmlElement(name = "dest_acc_id")
    @JsonProperty("dest_acc_id")
    private Integer accountDestinationId;
}
