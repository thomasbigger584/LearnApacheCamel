package com.twb.camel.resourcefile;

import org.apache.camel.Message;
import org.apache.camel.spring.SpringRouteBuilder;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class ResourceFileRoute extends SpringRouteBuilder {
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
    String extension = FilenameUtils.getExtension(file.getName());
    message.setHeader("FileExtension", extension);
                }).

                process(exchange -> {
                    log.info(exchange.getMessage().getHeaders().toString());
                }).

                choice().
                when(header("FileExtension").isEqualTo("csv")).
                to("jms:queue:csv.q").
                when(header("FileExtension").isEqualTo("json")).
                to("jms:queue:json.q").
                when(header("FileExtension").isEqualTo("xml")).
                to("jms:queue:xml.q").
                endChoice();
    }
}
