package com.test.paymentservice.application.representation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement(name = "account")
@XmlAccessorType(XmlAccessType.PROPERTY)
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ClientAccountView {

    @XmlElement(name = "account_id")
    @JsonProperty("account_id")
    private int id;

    @XmlElement(name = "account_num")
    @JsonProperty("account_num")
    private String number;

    @XmlElement(name = "account_type")
    @JsonProperty("account_type")
    private String type;

    @XmlElement
    private BigDecimal balance;
}
