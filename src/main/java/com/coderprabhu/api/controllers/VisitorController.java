package com.coderprabhu.api.controllers;

import javax.servlet.http.HttpServletRequest;

import com.coderprabhu.api.counter.Counter;
import com.coderprabhu.api.data.Visitor;
import com.coderprabhu.api.services.VisitorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;

/**
 * IndexController
 */
@RestController
@Log4j2
public class VisitorController {

    @Autowired Counter counter;
    @Autowired VisitorService visitorService;

    @GetMapping(value="/visit")
    @CrossOrigin(origins = "*")
    public Visitor visit(HttpServletRequest request) {
        counter.increament();
        return visitorService.processVisit(request);
    }

    @GetMapping(value="/count")
    @CrossOrigin(origins = "*")
    public int count() {
        log.info("count is " + counter.getCount());
        return counter.getCount();
    }
}