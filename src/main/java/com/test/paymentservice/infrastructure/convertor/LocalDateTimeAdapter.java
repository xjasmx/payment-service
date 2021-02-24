package com.test.paymentservice.infrastructure.convertor;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime> {

    final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public LocalDateTime unmarshal(String v) throws Exception {
        return LocalDateTime.parse(v);
    }

    @Override
    public String marshal(LocalDateTime v) throws Exception {
        return v.format(dateTimeFormatter);
    }
}
