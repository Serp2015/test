package com.haulmont.test.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(value = "com.haulmont.test")
public class AppConfig {
}
