package com.qunce.springmvc;

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
public class SpringMvcApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringMvcApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Gson gson = new Gson();
        /**
         * {
         * 	"source": {
         * 		"nonOptionArgsPropertyName": "nonOptionArgs",
         * 		"logger": {
         * 			"logger": {
         * 				"logger": {
         * 					"name": "org.springframework.boot.DefaultApplicationArguments$Source"
         *                                },
         * 				"locationAwareLogger": {
         * 					"name": "org.springframework.boot.DefaultApplicationArguments$Source"
         *                },
         * 				"name": "org.springframework.boot.DefaultApplicationArguments$Source",
         * 				"messageFactory": {
         *
         *                },
         * 				"flowMessageFactory": {
         * 					"entryText": "Enter",
         * 					"exitText": "Exit"
         *                }* 			}
         * 		}        ,
         * 		"name": "commandLineArgs",
         * 		"source": {
         * 			"optionArgs": {
         *
         *            },
         * 			"nonOptionArgs": []
         *        }
         *    },
         * 	"args": []
         * }
         */
        System.out.println(gson.toJson(args));
    }
}
