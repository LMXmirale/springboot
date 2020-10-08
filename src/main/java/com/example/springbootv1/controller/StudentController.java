package com.example.springbootv1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : pp
 * @Date : 2020/10/8 19:33
 */
@RestController
public class StudentController {

        @RequestMapping("/hello")
        public String hello() {
            System.out.println("你好!");
            return "恭喜OG";
        }
}
