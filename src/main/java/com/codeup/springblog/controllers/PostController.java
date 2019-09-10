package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.User;
import com.codeup.springblog.repos.PostRepository;
import com.codeup.springblog.repos.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Controller
public class PostController {

    private final PostRepository postDao;
    private final UserRepository userDao;


    public PostController(PostRepository postRepository, UserRepository userRepository){
        this.postDao = postRepository;
        this.userDao = userRepository;
    }
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
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/edit")
    public String getEdit(@PathVariable long id, Model vModel){
        Post post =postDao.findOne(id);
        vModel.addAttribute("post",post);
        return "posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String update(@PathVariable long id,
                         @RequestParam(name = "title") String title,
                         @RequestParam(name = "body") String body,
                         Model vModel){

    Post postUpdated = postDao.findOne(id);
         postUpdated.setTitle(title);
         postUpdated.setBody(body);
         postDao.save(postUpdated);
         return "redirect:/posts";
    }
    // eidt has a id bot with the create
    //create a form
    @GetMapping("/posts/create")
    public String showCreateForm(){
        return "posts/create";
    }
    @PostMapping("/posts/create")
    public String createPost(
                             @RequestParam(name= "title") String newTitle,
                             @RequestParam(name= "body") String newBody
                             ){
        User userDB = userDao.findOne(1L);


        //create a new post
        Post postToCreate = new Post();
        postToCreate.setTitle(newTitle);
        postToCreate.setBody(newBody);

        //for user
        postToCreate.setUser(userDB);
        postDao.save(postToCreate);
        return "redirect:/posts/";
    }





//    @GetMapping("/posts")
//    public String index(Model vModel){
//        ArrayList<Post> posts = new ArrayList<>();
//        Post dogs= new Post("Dogs", "new little puppies.");
//        Post cats = new Post("Cats", "new little kittens");
//        posts.add(dogs);
//        posts.add(cats);
//
//        vModel.addAttribute("posts",posts);
//        return "posts/index";
//    }

//    @GetMapping("/posts/{id}")
//    public String individual (@PathVariable int id, Model model){
//        Post article = new Post("My cats","MY cats are crazy" );
//        model.addAttribute("article", article);
////        model.addAttribute("id",id);
//        return "posts/show";
//    }






}
