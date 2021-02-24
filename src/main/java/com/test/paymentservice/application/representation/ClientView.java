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

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ClientView {

    @XmlElement(name = "first_name")
    @JsonProperty("first_name")
    private String firstName;

    @XmlElement(name = "last_name")
    @JsonProperty("last_name")
    private String lastName;
}
