package com.coderprabhu.api.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * IndexController
 */
@RestController
public class IndexController {

    @GetMapping(value="/")
    @CrossOrigin(origins = "*")
    public String home() {
        return "Home";
    }

    @GetMapping(value="/hello")
    @CrossOrigin(origins = "*")
    public String hello() {
        return "Build. Ship. Repeat.";
    }
}