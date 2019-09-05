package com.codeup.springblog.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello")
    @ResponseBody
    public String hello(){
        return "<h2>Hello, welcome to my page!";
    }

    @PostMapping("bye")
    @ResponseBody
    public String bye(){
        return "<h2>See you soon!<h2>";
    }


    @GetMapping("/hello/{name}")
    @ResponseBody
    public String sayHello(@PathVariable String name) {
        return "Hello " + name + "!";
    }





    @GetMapping("/number/{num}")
    @ResponseBody
    public String number(@PathVariable int num){
        return "Your number is "+ num;
    }
}
