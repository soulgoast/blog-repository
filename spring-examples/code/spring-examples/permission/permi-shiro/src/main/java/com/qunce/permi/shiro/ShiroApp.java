package com.qunce.permi.shiro;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class ShiroApp {

    private static final Logger log = LoggerFactory.getLogger(ShiroApp.class);

    private final Environment env;

    public ShiroApp(Environment env) {
        this.env = env;
    }



    /**
     * Main method, used to run the application.
     *
     * @param args the command line arguments.
     */
    public static void main(String[] args) {
        SpringApplication.run(ShiroApp.class, args);

    }
}
