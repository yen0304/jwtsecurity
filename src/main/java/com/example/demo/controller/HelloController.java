package com.example.demo.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/hello")
    @PreAuthorize("hasAuthority('system:dept:list')")
    public  String hello(){
        return "hello";
    }

    @RequestMapping("/hello2")
    @PreAuthorize("hasAuthority('system:admin:list')")
    public  String hello2(){
        return "hello";
    }
}
