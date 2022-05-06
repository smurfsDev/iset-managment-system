package com.projetIntegraion.spring.blogClub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BlogClubController {
   
   
    @RequestMapping("/showManageBlog")
    public String showManageBlog(ModelMap model) {
        return "ManageBlog";
    }


}
