package com.coderprabhu.api.data;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class ZonedDateTimeReadConverter implements Converter<Date, ZonedDateTime>{
    @Override
    public ZonedDateTime convert(Date date) {
        return date.toInstant().atZone(ZoneOffset.UTC);
        // return date.toInstant().atOffset(ZoneOffset.of(offsetId));
        // return date.toInstant().atZone(ZoneId.of("America/Los_Angeles"));
    }
}