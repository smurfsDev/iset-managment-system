package com.projetIntegraion.spring.blogClub.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.projetIntegraion.spring.blogClub.entity.About;

import com.projetIntegraion.spring.blogClub.service.AboutService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AboutClubsController {
    @Autowired
    private AboutService aboutService;
   
    

    @RequestMapping("/showCreateBlog")
    public String showBlog(ModelMap modelMap,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size ) {
               
        Page<About> listDcc = aboutService.getAllAboutsParPage(page, size);
         modelMap.addAttribute("Dccs", listDcc);
        
        modelMap.addAttribute("pages", new int[listDcc.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        System.out.println("abouts: "+modelMap.toString());
        System.out.println("page = "+page);
        System.out.println("currentPage = "+modelMap.get("currentPage").toString());
        System.out.println("siizee = "+modelMap.get("pages").toString());
        return "ManageAbout";
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
           /* About about = new About();
            System.out.println("About: "+About.toString());
            about.setLongDescription(About.getLongDescription());
            about.setClub(About.getClub());*/
            aboutService.save(About);
           // modelMap.addAttribute("About", newAbout);
            modelMap.addAttribute("msg", "About enregistrée avec succès");
            modelMap.addAttribute("type", "success");
            modelMap.addAttribute("pages",
                    new int[aboutService.getAllAboutsParPage(page, size).getTotalPages()]);
            System.out.println("modelMap = " + modelMap.toString());
                    return this.showBlog(modelMap, page, size);
        }
    }
    @RequestMapping("/deleteAbout")
    public String deleteAbout(@RequestParam("id") Long id, ModelMap modelMap,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {

        if (aboutService.deleteById(id)) {
            modelMap.addAttribute("type", "danger");
            modelMap.addAttribute("msg", "About supprimé!");
        } else {
            modelMap.addAttribute("type", "danger");
            modelMap.addAttribute("msg", "About non supprimée : Id non trouvé");
        }
        return this.showBlog(modelMap, page, size);

    }
    @RequestMapping("/modifierAbout")
    public String showUpdateAbout(@RequestParam("id") Long id, ModelMap modelMap,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {
        About About = aboutService.getAbout(id);
        modelMap.addAttribute("About", About);
        modelMap.addAttribute("edit", true);
        modelMap.addAttribute("pages",
                new int[aboutService.getAllAboutsParPage(page, size).getTotalPages()]);
        
                return "createAbout";
    }

    @RequestMapping("/updateAbout")
    public String updateAbout(ModelMap modelMap,
            
            @Valid About About,
            BindingResult bindingResult,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size,
            HttpServletRequest request) throws IOException {
        if (bindingResult.hasErrors()) {
            modelMap.addAttribute("org.springframework.validation.BindingResult.Dcc", bindingResult);
            modelMap.addAttribute("About", About);
            modelMap.addAttribute("edit", true);
            return "createAbout";
        } else {
            About about = new About();
            about.setId(About.getId());
            about.setLongDescription(About.getLongDescription());
            about.setClub(About.getClub());
             aboutService.save(about);
            
            
            modelMap.addAttribute("pages",
                    new int[aboutService.getAllAboutsParPage(page, size).getTotalPages()]);
            modelMap.addAttribute("type", "warning");
            modelMap.addAttribute("msg", "Demande de creation de club modifiée avec succès");
            return this.showBlog(modelMap, page, size);
        }
    }

}
