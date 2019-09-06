package com.codeup.springblog.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MathController {
    @GetMapping("/add/{num1}/and/{num2}")
    @ResponseBody
    public String add(@PathVariable int num1,@PathVariable int num2   ){
        int total=num1+num2;
        return "Add " + total;
    }

    @RequestMapping(path = "/test/{num}", method = RequestMethod.GET)
    @ResponseBody
    public String number(@PathVariable int num){
        return "this number is "+ num;
    }


    @GetMapping("/subtract/{num1}/from/{num2}")
    @ResponseBody
    public String subtract (@PathVariable int num1,@PathVariable int num2   ){
        int total = num2 - num1;
        return Long.toString(total) ;
    }

    @GetMapping("/multiply/{num1}/and/{num2}")
    @ResponseBody
    public String multiply(@PathVariable int num1,@PathVariable int num2 ){
        int total = num1* num2;
        return "Multiply " +total;
    }

    @GetMapping("/divide/{num1}/by/{num2}")
    @ResponseBody
    public String divide (@PathVariable int num1, @PathVariable int num2){
        int total = num1/ num2;
        return "Divide " +total;
    }

}
