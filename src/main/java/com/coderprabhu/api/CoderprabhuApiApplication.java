package com.coderprabhu.api;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import ua_parser.Parser;

@SpringBootApplication
public class CoderprabhuApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoderprabhuApiApplication.class, args);
	}

	@Bean
    public Parser uaParser() throws IOException {
        return new Parser();
	}
}
