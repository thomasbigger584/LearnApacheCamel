package com.twb;

import com.twb.activemq.Sender;
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

    @Autowired
    private Sender sender;

    public static void main(String[] args) {
        SpringApplication.run(ApacheCamelApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

//        send message directy to get a file from resources on a camel route for resources file path
//        todo would like to update this to use a consumer to automatically get the file instead of this
        producerTemplate.sendBody("direct:resourceFile", "json/data.json");

//        using activemq to send this string message onto a queue
        sender.send("Hello World from Application");
    }
}
