package com.coderprabhu.api.controllers;

import javax.servlet.http.HttpServletRequest;

import com.coderprabhu.api.data.Visitor;
import com.coderprabhu.api.services.VisitorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;

/**
 * VisitorController
 */
@RestController
@Log4j2
public class VisitorController {

    @Autowired VisitorService visitorService;

    @GetMapping(value="/visit")
    @CrossOrigin(origins = "*")
    public Visitor visit(HttpServletRequest request) {
        return visitorService.processVisit(request);
    }

    @GetMapping(value="/count")
    @CrossOrigin(origins = "*")
    public int count() {
        Integer totalVisits = visitorService.getTotalVisits();
        log.info("Total visitors so far: " + totalVisits);
        return totalVisits;
    }

    @GetMapping(value="/unique")
    @CrossOrigin(origins = "*")
    public int unique() {
        Integer uniqueVisits = visitorService.getUniqueVisits();
        log.info("Total visitors so far: " + uniqueVisits);
        return uniqueVisits;
    }
}