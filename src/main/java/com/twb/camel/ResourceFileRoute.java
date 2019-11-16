package com.twb.camel;

import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.stereotype.Component;

import static com.twb.camel.ResourceFileProcessor.FILE_EXTENSION_HEADER;

@Component
public class ResourceFileRoute extends SpringRouteBuilder {
    @Override
    public void configure() {
        //@formatter:off

        from("direct:resourceFile").

                process(new ResourceFileProcessor()).

                //audit type 'to' producer
                wireTap("jms:queue:audit.q").

                filter(exchange -> {
                  FileType fileType = exchange.getIn().
                          getHeader(FILE_EXTENSION_HEADER, FileType.class);
                   return fileType != FileType.NOT_SUPPORTED;
                }).

                to("jms:queue:file.q").

                choice().
                    when(header(FILE_EXTENSION_HEADER).isEqualTo(FileType.CSV)).
                        to("jms:queue:csv.q").
                    otherwise().when(header(FILE_EXTENSION_HEADER).isEqualTo(FileType.JSON)).
                        to("jms:queue:json.q").
                    otherwise().when(header(FILE_EXTENSION_HEADER).isEqualTo(FileType.XML)).
                        to("jms:queue:xml.q").
                    otherwise().
                        to("jms:queue:unsupported.q").
                end().

                multicast().
                    parallelProcessing().
                    stopOnException().
                        to("jms:queue:multicast1.q", "jms:queue:multicast2.q").

                // call bean with parameters as annotations in service
                bean(JavaBeanService.class, "call");

        //@formatter:on
    }
}
