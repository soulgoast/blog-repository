package com.qunce.permi.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class SecurityApp {

    private static final Logger log = LoggerFactory.getLogger(SecurityApp.class);

    private final Environment env;

    public SecurityApp(Environment env) {
        this.env = env;
    }



    /**
     * Main method, used to run the application.
     *
     * @param args the command line arguments.
     */
    public static void main(String[] args) {
        SpringApplication.run(SecurityApp.class, args);

    }
}
