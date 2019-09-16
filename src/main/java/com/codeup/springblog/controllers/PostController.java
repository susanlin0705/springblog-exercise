package com.codeup.springblog.controllers;

import com.codeup.springblog.Services.EmailService;
import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.User;
import com.codeup.springblog.repos.PostRepository;
import com.codeup.springblog.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;



import javax.validation.Valid;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    private final PostRepository postDao;
    private final UserRepository userDao;
    private final EmailService emailService;


    public PostController(PostRepository postRepository,UserRepository userRepository, EmailService emailService ){
        this.postDao = postRepository;
        this.userDao = userRepository;
        this.emailService = emailService;
    }

//@Autowired
// private PostRepository postDao;

//    @Autowired
//    private EmailService emailService;

    @GetMapping("/posts")
    public String index(Model vModel){
       Iterable <Post> posts = postDao.findAll();
       vModel.addAttribute("posts", posts );
       return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String individual(@PathVariable long id, Model vModel){
        Post post = postDao.findOne(id);
        vModel.addAttribute("post", post);
        return "posts/show";
    }

    @PostMapping("/posts/{id}/delete")
    public String delete(@PathVariable long id){
        postDao.delete(id);
        return "redirect:/posts/myPost";
    }

    @GetMapping("/posts/{id}/edit")
    public String getEdit(@PathVariable long id, Model vModel){
        Post post =postDao.findOne(id);
        vModel.addAttribute("post",post);
        return "posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String update(@ModelAttribute Post post){
         postDao.save(post);
         return "redirect:/posts/myPost";
    }

//old way to write PostMapping
//    @PostMapping("/posts/{id}/edit")
//    public String update(
//                         @PathVariable long id,
//                         @RequestParam(name = "title") String title,
//                         @RequestParam(name = "body") String body
//                        ){
//
//    Post postUpdated = postDao.findOne(id);
//         postUpdated.setTitle(title);
//         postUpdated.setBody(body);
//        postDao.save(postUpdated);
//        return "redirect:/posts";
//    }

    //create a form
    @GetMapping("/posts/create")
    public String showCreateForm(Model vModel){
        vModel.addAttribute("post", new Post());
        return "posts/create";
    }

    //old way for GetMapping
//    @GetMapping("/posts/create")
//    public String showCreateForm(){
//        return "posts/create";
//    }

    @PostMapping("/posts/create")
    public String createPost(
            @Valid Post post,
            Errors validation,
            Model vModel){
        if(validation.hasErrors()){
            vModel.addAttribute("errors",validation);
            vModel.addAttribute("post", post);
            return "posts/create";
        }else{

            User userSession = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User userDB = userDao.findOne(userSession.getId());
            post.setUser(userDB);
            Post savePost=postDao.save(post);
            emailService.prepareAndSend(savePost,"New Post",String.format("Post with the id %d has been created",savePost.getId())
            );
            return "redirect:/posts/myPost";
        }

    }

    //old way for PostMapping create
//    @PostMapping("/posts/create")
//    public String createPost(
//                             @RequestParam(name= "title") String newTitle,
//                             @RequestParam(name= "body") String newBody
//            ){
//        User userDB = userDao.findOne(1L);
//
//        Post postToCreate = new Post();
//        postToCreate.setTitle(newTitle);
//        postToCreate.setBody(newBody);
//        postToCreate.setUser(userDB);
//        postDao.save(postToCreate);
//        return "redirect:/posts/";
//    }


    //json
    @GetMapping("/posts.json")
    public @ResponseBody List<Post> viewAllPostsInJSONFormat(){
        return (List<Post>) postDao.findAll();
    }
    //ajax
    @GetMapping("/posts/ajax")
    public String viewAllPostsWithAjax() {
        return "posts/ajax";
    }









}
