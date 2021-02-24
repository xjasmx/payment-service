package com.test.paymentservice.web.response;


import com.test.paymentservice.application.representation.ClientAccountView;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "accounts")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CollectionClientAccountsWrapper {

    @XmlElement(name = "account")
    private List<ClientAccountView> accounts;
}
