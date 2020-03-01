package com.coderprabhu.api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * IndexController
 */
@RestController
public class IndexController {

    @GetMapping(value="/")
    public String home() {
        return "Hello from CoderPraBhu Api";
    }
    
}