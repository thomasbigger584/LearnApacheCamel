package com.twb.camel;

import org.apache.camel.Message;
import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class PushTimeToActiveMqQueueRoute extends SpringRouteBuilder {
    @Override
    public void configure() throws Exception {

        from("timer:tick?period=2000").
                process(exchange -> {
                    Long millis = System.currentTimeMillis();
                    Message in = exchange.getIn();
                    in.setBody(millis, Long.class);
                }).
                process(exchange -> log.info(exchange.getIn().getHeaders().toString())).
                to("jms:queue:helloworld.q");
    }
}
