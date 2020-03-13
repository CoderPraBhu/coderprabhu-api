package com.coderprabhu.api.controllers;

import com.coderprabhu.api.counter.Counter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * IndexController
 */
@RestController
public class IndexController {

    @Autowired Counter counter;

    @GetMapping(value="/hello")
    @CrossOrigin(origins = "*")
    public String home() {
        counter.increament();
        return "Hello from CoderPraBhu!";
    }

    @GetMapping(value="/count")
    @CrossOrigin(origins = "*")
    public int count() {
        return counter.getCount();
    }
}