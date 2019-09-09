package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Ad;
import com.codeup.springblog.repos.AdRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AdController {
    private final AdRepository adDao;

    public AdController (AdRepository adRepository){

        adDao = adRepository;
    }
    @GetMapping("/ads")
    public String index(Model vModel){
        Iterable <Ad> ads = adDao.findAll();
        vModel.addAttribute("ads",ads);
        return "ads";
    }






}
