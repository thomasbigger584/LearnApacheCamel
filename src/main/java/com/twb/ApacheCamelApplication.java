package com.twb;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class ApacheCamelApplication implements CommandLineRunner {

    @Autowired
    private ProducerTemplate producerTemplate;

    public static void main(String[] args) {
        SpringApplication.run(ApacheCamelApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        producerTemplate.sendBody("direct:resourceFile", "json/data.json");
    }
}
