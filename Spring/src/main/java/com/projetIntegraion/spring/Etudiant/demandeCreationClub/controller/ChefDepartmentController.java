package com.projetIntegraion.spring.Etudiant.demandeCreationClub.controller;

import java.util.Optional;

import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.Role;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.UserRole;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.repository.RoleRepository;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.repository.UserRoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ChefDepartmentController {


    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping(value = "/listeChefDepartments")
    public String showAll(ModelMap modelMap,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {
        Page<Object[]> listChefDepartments = userRoleRepository.findAllChefDepartments( PageRequest.of(page, size));
        modelMap.addAttribute("listChefDepartments", listChefDepartments);
        modelMap.addAttribute("pages", new int[listChefDepartments.getTotalPages()]);

        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("size", size);

        return "admin/gererChefDepartments/listeChefDepartments";

    }

    @GetMapping(value = "/acceptCD")
    public String accept(@RequestParam int idCD,
            ModelMap modelMap,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {
                Role r = roleRepository.findByName("ROLE_CHEFDEPARTEMENT");
        Optional<UserRole> userRole = userRoleRepository.findByRoleIdAndUserId(r.getId(), idCD);
        if (userRole.isPresent()) {
            Optional<UserRole> userRole1 = userRoleRepository.findByRoleIdAndDepartementAndStatus(r.getId(),userRole.get().getDepartement(), 1);
            if (userRole1.isPresent()){
                userRole.get().setStatus(3);
                userRoleRepository.save(userRole.get());
                modelMap.addAttribute("type","danger");
                modelMap.addAttribute("msg", "La departement admet deja un chef de departement, Demande va etre rejeter");


            }else{
                userRole.get().setStatus(1);
                userRoleRepository.save(userRole.get());
                modelMap.addAttribute("type","success");
                modelMap.addAttribute("msg", "Le chef de département a été accepté");
            }
    
        }else{
            modelMap.addAttribute("type","danger");
            modelMap.addAttribute("msg", "Une erreur est survenue");
        }
       return this.showAll(modelMap, page, size);
    }

    @GetMapping(value = "/declineCD")
    public String decline(@RequestParam int idCD,
            ModelMap modelMap,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {
                Role r = roleRepository.findByName("ROLE_CHEFDEPARTEMENT");
        Optional<UserRole> userRole = userRoleRepository.findByRoleIdAndUserId(r.getId(), idCD);
        if (userRole.isPresent()) {
            userRole.get().setStatus(2);
            userRoleRepository.save(userRole.get());
            modelMap.addAttribute("type","success");
            modelMap.addAttribute("msg", "Le chef de département a été refusée");
    
        }else{
            modelMap.addAttribute("type","danger");
            modelMap.addAttribute("msg", "Une erreur est survenue");
        }
        return this.showAll(modelMap, page, size);

    }



}
