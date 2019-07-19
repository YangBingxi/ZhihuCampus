package edu.njupt.sw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    @RequestMapping("/")
    @ResponseBody
    public String index(){
        return "Hello";
    }




    @RequestMapping("/vm")
    public String pro(Model model){
        model.addAttribute("value","value");


        return "home";
    }
}
