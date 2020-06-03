package com.coderprabhu.api.data;

import java.time.ZonedDateTime;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class ZonedDateTimeWriteConverter implements Converter<ZonedDateTime, Date> {
    @Override
    public Date convert(ZonedDateTime zonedDateTime) {
        return Date.from(zonedDateTime.toInstant());
        // return date.toInstant().atZone(ZoneId.of("America/Los_Angeles"));
        // return Date.from(zonedDateTime.toInstant().atZone(ZoneId.of("America/Los_Angeles")).toInstant());
    }
}