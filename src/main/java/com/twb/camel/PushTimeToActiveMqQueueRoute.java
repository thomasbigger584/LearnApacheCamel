package com.twb.camel;

import org.apache.camel.Message;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class PushTimeToActiveMqQueueRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        from("timer:tick?period=2000").
                process(exchange -> {
                    Long millis = System.currentTimeMillis();
                    Message in = exchange.getIn();
                    in.setBody(millis, Long.class);
                }).
                to("jms:helloworld.q");
    }
}
