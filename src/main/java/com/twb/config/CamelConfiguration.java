package com.twb.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.CamelContext;
import org.apache.camel.spring.boot.CamelContextConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@SuppressWarnings("unused")
public class CamelConfiguration {
    @Bean
    CamelContextConfiguration contextConfiguration() {
        return new CamelContextConfiguration() {
            @Override
            public void beforeApplicationStart(CamelContext camelContext) {
                log.info("beforeApplicationStart: " + camelContext);
            }

            @Override
            public void afterApplicationStart(CamelContext camelContext) {
                log.info("afterApplicationStart: " + camelContext);
            }
        };
    }
}
