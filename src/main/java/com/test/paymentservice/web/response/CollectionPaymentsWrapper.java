package com.test.paymentservice.web.response;

import com.test.paymentservice.application.representation.PaymentView;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "payments")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CollectionPaymentsWrapper {

    @XmlElement(name = "payment")
    private List<PaymentView> payments;
}
