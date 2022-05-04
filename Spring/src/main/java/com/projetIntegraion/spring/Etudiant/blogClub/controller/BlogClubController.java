package com.projetIntegraion.spring.Etudiant.blogClub.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.projetIntegraion.spring.Etudiant.blogClub.entity.About;
import com.projetIntegraion.spring.Etudiant.blogClub.service.AboutService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BlogClubController {
    @Autowired
    private AboutService aboutService;

    

    @RequestMapping("/showCreateBlog")
    public String showBlog(ModelMap modelMap,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {
        Page<About> listDcc = aboutService.getAllAboutsParPage(page, size);
        modelMap.addAttribute("Dccs", listDcc);
        modelMap.addAttribute("pages", new int[listDcc.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        System.out.println("modelMap = " + modelMap.toString());
        return "ManageBlog";
    }
   @RequestMapping("/createAbout")
    public String createAbout(ModelMap modelMap,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {
        modelMap.addAttribute("About", new About());
        modelMap.addAttribute("edit", false);
        modelMap.addAttribute("pages",
                new int[aboutService.getAllAboutsParPage(page, size).getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        return "createAbout";
    }
    @RequestMapping("/saveAbout")
    public String saveAbout(ModelMap modelMap,
           
            @Valid About About,
            BindingResult bindingResult,
            HttpServletRequest request,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) throws IOException {
        if (bindingResult.hasErrors()) {
            modelMap.addAttribute("org.springframework.validation.BindingResult.About", bindingResult);
            modelMap.addAttribute("About", About);
            return "createAbout";
        } else {
            About about = new About();
            System.out.println("About: "+About.toString());
            about.setLongDescription(About.getLongDescription());
            about.setClub(About.getClub());
            About newAbout = aboutService.save(about);
            modelMap.addAttribute("About", newAbout);
            modelMap.addAttribute("msg", "About enregistrée avec succès");
            modelMap.addAttribute("type", "success");
            modelMap.addAttribute("pages",
                    new int[aboutService.getAllAboutsParPage(page, size).getTotalPages()]);
            System.out.println("modelMap = " + modelMap.toString());
                    return this.showBlog(modelMap, page, size);
        }
    }

}
