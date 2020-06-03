package com.coderprabhu.api.data;

import java.time.ZonedDateTime;

import org.springframework.data.annotation.Id;

import lombok.Data;

/**
 * Visitor
 */
@Data
public class Visitor {

    @Id
    String id;
    String ip;
    String device;
    ZonedDateTime zonedDateTime;
}