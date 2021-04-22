package com.qunce.webflux;

import com.google.gson.Gson;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description TODO
 * @Author hu zhongxi
 */
@SpringBootApplication
public class WebFluxApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(WebFluxApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Gson gson = new Gson();
        System.out.println(gson.toJson(args));
    }
}
