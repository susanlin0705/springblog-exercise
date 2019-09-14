package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.User;
import com.codeup.springblog.repos.PostRepository;
import com.codeup.springblog.repos.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private PostRepository postDao;

    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.postDao = postRepository;
    }

    @GetMapping("/sign-up")
    public String showSignUpForm(Model vModel){
        vModel.addAttribute("user", new User());
        return "users/sign-up";
    }

    @PostMapping("/sign-up")
    public String saveUser(@ModelAttribute User user){
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        userRepository.save(user);
        return"redirect:/login";
    }
    @GetMapping("/posts/myPost")
    public String showUserPosts (Model vModel){
        User userSession = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Iterable <Post> posts = postDao.findByUser(userSession);
        vModel.addAttribute("posts", posts);
        return "posts/myPost";

    }




}
