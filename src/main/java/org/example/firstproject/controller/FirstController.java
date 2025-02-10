package org.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import sun.font.AttributeValues;

@Controller
public class FirstController {

    @GetMapping("/hi")
    public String niceToMeetYou(Model model){
        model.addAttribute("username", "지후");
        return "greetings";
    }
   @GetMapping("/bye")
     public String seeYouNext(Model model){
        model.addAttribute("nickname","홍길동");
        return "goodbye";
   }
}
