package com.twb.camel;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Body;
import org.apache.camel.Header;
import org.springframework.stereotype.Service;

import java.io.File;

@Slf4j
@Service
public class JavaBeanService {

    public void call(@Header("X-FileExtension") FileType fileType, @Body File file) {
        log.info("----->>>");
        log.info("Java Bean Called!");
        log.info(fileType.toString());
        log.info(file.toString());
        log.info("----->>>");
    }

    public void throwException() throws Exception {
        throw new Exception();
    }
}
