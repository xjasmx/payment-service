package com.test.paymentservice.application.representation;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.test.paymentservice.infrastructure.convertor.LocalDateTimeAdapter;
import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@XmlRootElement(name = "payment")
@XmlAccessorType(XmlAccessType.FIELD)
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PaymentView {

    @XmlElement(name = "payment_id")
    @JsonProperty("payment_id")
    private int id;

    @XmlJavaTypeAdapter(value = LocalDateTimeAdapter.class)
    @XmlElement(name = "timestamp")
    @JsonProperty("timestamp")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime payedOn;

    @XmlElement(name = "src_acc_num")
    @JsonProperty("src_acc_num")
    private String accountSourceNumber;

    @XmlElement(name = "dest_acc_num")
    @JsonProperty("dest_acc_num")
    private String accountDestinationNumber;

    @XmlElement
    private BigDecimal amount;

    @XmlElement
    private ClientView payer;

    @XmlElement
    private ClientView recipient;
}


