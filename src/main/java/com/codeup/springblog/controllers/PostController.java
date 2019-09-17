package com.codeup.springblog.controllers;

import com.codeup.springblog.Services.EmailService;
import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.PostImage;
import com.codeup.springblog.models.User;
import com.codeup.springblog.repos.PostImageRepository;
import com.codeup.springblog.repos.PostRepository;
import com.codeup.springblog.repos.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class PostController {

    private final PostRepository postDao;
    private final UserRepository userDao;
    private final EmailService emailService;
    private final PostImageRepository uploadDao;


    public PostController(PostRepository postRepository, UserRepository userRepository, EmailService emailService, PostImageRepository fileUploadRepository ){
        this.postDao = postRepository;
        this.userDao = userRepository;
        this.emailService = emailService;
        this.uploadDao = fileUploadRepository;
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
    @Value("/springblog/src/main/resources/static/Uploads")
    private String uploadPath;
    @PostMapping("/posts/create")
    public String createPost( @RequestParam(name = "file") MultipartFile uploadedFile,
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
            String filename = uploadedFile.getOriginalFilename();
            String filepath = Paths.get(uploadPath, filename).toString();
            File destinationFile = new File(filepath);
            try {
                uploadedFile.transferTo(destinationFile);

                PostImage file = new PostImage(destinationFile.getAbsolutePath(),savePost);
                // save to database
                uploadDao.save(file);
                vModel.addAttribute("message", "File successfully uploaded!");

            } catch (IOException e) {
                e.printStackTrace();
                vModel.addAttribute("message", "Oops! Something went wrong! " + e);
            }
            return "redirect:/posts";
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
