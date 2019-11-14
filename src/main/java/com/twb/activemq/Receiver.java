package com.twb.activemq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;

@Slf4j
public class Receiver {

    @JmsListener(destination = "helloworld.q")
    public void receive(String message) {
        log.info("received message='{}'", message);
    }
}
