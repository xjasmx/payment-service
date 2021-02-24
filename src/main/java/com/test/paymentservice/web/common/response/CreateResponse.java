package com.test.paymentservice.web.common.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateResponse<T> {
    private final T id;
}
