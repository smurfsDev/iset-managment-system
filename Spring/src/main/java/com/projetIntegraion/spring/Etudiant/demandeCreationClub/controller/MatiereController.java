package com.projetIntegraion.spring.Etudiant.demandeCreationClub.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.Matiere;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.User;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.UserRole;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.repository.UserRepository;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.repository.UserRoleRepository;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.service.MatiereService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MatiereController {
    
    @Autowired
    private MatiereService matiereService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    public User getUser(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        User user = userRepository.findUserWithName(principal.getName()).get();
        return user;
    }

    @GetMapping(value = "/matieres")
    public String getAllMatieres(ModelMap modelMap,
            @RequestParam Long idC,
            HttpServletRequest request,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {
       // User user = this.getUser(request);
     
        Page<Matiere> matieres = matiereService.getAllMatiereByClasseId(idC, page, size);
        modelMap.addAttribute("matieres", matieres);
        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("typea", "");
        modelMap.addAttribute("message", "vide");
        modelMap.addAttribute("size", size);
        modelMap.addAttribute("pages",
                new int[matiereService.getAllMatiereByClasseId(idC,
                        page, size).getTotalPages()]);

        return "ChefDepartment/Class/matieres";

    }

    
}
