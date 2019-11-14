package com.twb.camel;

import org.apache.camel.Message;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.LinkedHashMap;

@Component
public class ResourceFileRoute extends RouteBuilder {
    @Override
    public void configure() {

//         route gets activated via direct.
//         i.e. ProducerTemplate or some other route direct
        from("direct:resourceFile").

//                Using a processor to get the file and
//                place it into the message
                process(new ResourceFileProcessor()).

//                Verifying that the file is actually in the message
                process(exchange -> {
                    Message message = exchange.getMessage();
                    File file = message.getBody(File.class);
                    log.info("File: " + file);
                }).

//                Use Jackson to deserialise the file into a LinkedHashMap
                unmarshal(new JacksonDataFormat()).

//                Verifying that indeed it is a LinkedHashMap in the body of the message
                process(exchange -> {
                    Message message = exchange.getMessage();
                    LinkedHashMap map = message.getBody(LinkedHashMap.class);
                    log.info("LinkedHashMap: " + map);
                }).

//                logging out the exchange
                to("log:logger");
    }
}
