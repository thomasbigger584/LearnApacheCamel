package com.twb.camel.resourcefile;

import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.stereotype.Component;

import static com.twb.camel.resourcefile.ResourceFileProcessor.FILE_EXTENSION_HEADER;

@Component
public class ResourceFileRoute extends SpringRouteBuilder {
    @Override
    public void configure() {
        //@formatter:off

        from("direct:resourceFile").

                process(new ResourceFileProcessor()).

                process(exchange -> {
                    log.info(exchange.getMessage().getHeaders().toString());
                }).

                filter(exchange -> {
                  FileType fileType = exchange.getIn().
                          getHeader(FILE_EXTENSION_HEADER, FileType.class);
                   return fileType != FileType.NOT_SUPPORTED;
                }).

                choice().
                    when(header(FILE_EXTENSION_HEADER).isEqualTo(FileType.CSV)).
                        to("jms:queue:csv.q").
                    otherwise().when(header(FILE_EXTENSION_HEADER).isEqualTo(FileType.JSON)).
                        to("jms:queue:json.q").
                    otherwise().when(header(FILE_EXTENSION_HEADER).isEqualTo(FileType.XML)).
                        to("jms:queue:xml.q").
                end().

                to("jms:queue:file.q");

        //@formatter:on
    }

}
