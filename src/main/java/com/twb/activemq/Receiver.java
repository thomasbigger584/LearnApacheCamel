package com.twb.activemq;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.Message;
import org.springframework.jms.annotation.JmsListener;

@Slf4j
public class Receiver {

    @JmsListener(destination = "csv.q")
    public void receiveCsv(Message message) {
        log.info("CSV received'{}'", message);
    }

    @JmsListener(destination = "json.q")
    public void receiveJson(Message message) {
        log.info("JSON received'{}'", message);
    }

    @JmsListener(destination = "xml.q")
    public void receiveXml(Message message) {
        log.info("XML received'{}'", message);
    }

    @JmsListener(destination = "file.q")
    public void receiveFile(Message message) {
        log.info("File received'{}'", message);
    }
}
