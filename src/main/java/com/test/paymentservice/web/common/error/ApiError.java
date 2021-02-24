package com.test.paymentservice.web.common.error;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "error")
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
@Getter
@Slf4j
public class ApiError {
    @XmlTransient
    @JsonIgnore
    private HttpStatus status;
    @XmlElement
    private int code;
    @XmlElement
    private String text;

    public ApiError(HttpStatus status, String text) {
        this.status = status;
        this.code = status.value();
        this.text = text;

        log.trace(text);
    }
}
