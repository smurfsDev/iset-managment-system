package com.projetIntegraion.spring.blogClub.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.projetIntegraion.spring.blogClub.entity.About;
import com.projetIntegraion.spring.blogClub.entity.Activity;
import com.projetIntegraion.spring.blogClub.service.AboutService;
import com.projetIntegraion.spring.blogClub.service.ActivitiesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BlogClubController {
   
   
    @RequestMapping("/showManageBlog")
    public String showManageBlog(ModelMap model) {
        return "ManageBlog";
    }


}
