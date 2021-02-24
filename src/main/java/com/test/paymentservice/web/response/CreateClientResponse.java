package com.test.paymentservice.web.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "create_client_response")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateClientResponse<T> {

    @XmlElement(name = "client_id")
    @JsonProperty("client_id")
    private T clientId;
}
