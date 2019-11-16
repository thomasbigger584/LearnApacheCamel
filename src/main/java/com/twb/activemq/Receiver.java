package com.twb.activemq;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.Message;
import org.springframework.jms.annotation.JmsListener;

@Slf4j
@SuppressWarnings("unused")
public class Receiver {
    @JmsListener(destination = "audit.q")
    public void receiveAudit(Message message) {
        log.info("Audit received: '{}'", message);
    }

    @JmsListener(destination = "csv.q")
    public void receiveCsv(Message message) {
        log.info("CSV received: '{}'", message);
    }

    @JmsListener(destination = "json.q")
    public void receiveJson(Message message) {
        log.info("JSON received: '{}'", message);
    }

    @JmsListener(destination = "xml.q")
    public void receiveXml(Message message) {
        log.info("XML received: '{}'", message);
    }

    @JmsListener(destination = "unsupported.q")
    public void receiveUnsupported(Message message) {
        log.info("Unsupported received: '{}'", message);
    }

    @JmsListener(destination = "file.q")
    public void receiveFile(Message message) {
        log.info("File received: '{}'", message);
    }

    @JmsListener(destination = "multicast1.q")
    public void receivMulticast1(Message message) {
        log.info("Multicast1 received: '{}'", message);
    }

    @JmsListener(destination = "multicast2.q")
    public void receiveMulticast2(Message message) {
        log.info("Multicast2 received: '{}'", message);
    }
}
