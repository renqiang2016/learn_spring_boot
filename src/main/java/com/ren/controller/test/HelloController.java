package com.ren.controller.test;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qiang.ren
 * @version 1.0
 * @since 2020/8/23 22:53
 */
@RestController
public class HelloController {
    @RequestMapping("hello")
    public String hello(){
        return "<font style='font-size:28px;'>Hello Spring Boot</font>";
    }
}
