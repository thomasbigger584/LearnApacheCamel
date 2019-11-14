package com.twb.activemq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

@Slf4j
public class Sender {
    @Autowired
    private JmsTemplate jmsTemplate;

    public void send(String message) {
        log.info("sending message='{}'", message);
        jmsTemplate.convertAndSend("helloworld.q", message);
    }
}
