package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RollDiceController {

    @GetMapping("/roll-dice")
    public String rollDice(){
        return "roll-dice";
    }


    @GetMapping("/roll-dice/{n}")
    public String guess(@PathVariable int n, Model viewModel){
        int num =(int) (Math.random()*6+1);

        viewModel.addAttribute("diceRoll",num);
        viewModel.addAttribute("userGuess",n);

        boolean isCorrect = false;

        if(num == n) {
            isCorrect = true;
        }

        viewModel.addAttribute("match", isCorrect);

        return "roll-dice-page";
    }

}
