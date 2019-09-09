package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String individual (@PathVariable int id, Model model){
        Post article = new Post("My cats","MY cats are crazy" );
        model.addAttribute("article", article);
        model.addAttribute("id",id);
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
