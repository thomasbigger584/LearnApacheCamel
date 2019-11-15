package com.twb.camel.resourcefile;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.File;

@Slf4j
public class ResourceFileProcessor implements Processor {
    static final String FILE_EXTENSION_HEADER = "X-FileExtension";

    @Override
    public void process(Exchange exchange) throws Exception {
        Message message = exchange.getMessage();
        final String filePath = message.getBody(String.class);
        Resource resource = new ClassPathResource(filePath);
        File file = resource.getFile();
        FileType fileType = FileType.getFileType(file);

        Message in = exchange.getIn();
        in.setHeader(FILE_EXTENSION_HEADER, fileType);
        in.setBody(file, File.class);
    }
}
