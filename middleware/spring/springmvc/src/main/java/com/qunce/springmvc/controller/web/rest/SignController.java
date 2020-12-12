package com.qunce.springmvc.controller.web.rest;

import com.qunce.springmvc.annotation.ResponseEncode;
import com.qunce.springmvc.annotation.ResponseSign;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description TODO
 * @Author hu zhongxi
 */
@RestController
public class SignController {

    @ResponseEncode
    @ResponseSign
    @RequestMapping("/greet")
    public void hello(@RequestBody String greet) {
        System.out.println("greet:" + greet);
    }
}
