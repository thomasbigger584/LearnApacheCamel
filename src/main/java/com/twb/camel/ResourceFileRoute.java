package com.twb.camel;

import org.apache.camel.ErrorHandlerFactory;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.spi.RouteContext;
import org.springframework.stereotype.Component;

@Component
public class ResourceFileRoute extends RouteBuilder {
    @Override
    public void configure() {
        from("direct:resourceFile").
                process(new ResourceFileProcessor()).
                unmarshal(new JacksonDataFormat()).
                to("log:logger");
    }
}
