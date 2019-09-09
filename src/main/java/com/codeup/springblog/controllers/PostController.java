package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostController {
    @GetMapping("/posts")
    public String index(){
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String individual (@PathVariable int id){
        return "posts/show";
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String create(){
        return "view the form for creating a post";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String postCreate(){
        return "Create a new post.";
    }
}
