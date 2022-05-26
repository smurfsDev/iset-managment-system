package com.projetIntegraion.spring.Etudiant.demandeCreationClub.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.User;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.UserRole;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.repository.UserRepository;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.repository.UserRoleRepository;
import com.projetIntegraion.spring.administrateur.departement.entity.Departement;
import com.projetIntegraion.spring.administrateur.departement.repository.DepartementRepository;

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
    private DepartementRepository departementRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @GetMapping(value = "/listeChefDepartments")
    public String showAll(ModelMap modelMap,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {

        Page<User> listChefDepartments = userRepository.findByRolesId(4L, PageRequest.of(page, size));
        Set<Integer> ids = listChefDepartments.stream().map(u -> u.getId())
                .collect(java.util.stream.Collectors.toSet());
        List<UserRole> listUserRole = userRoleRepository.findByUserIdInAndRoleId(ids, 4L);
        List<Departement> listDepartement = departementRepository.findAll();

        

        modelMap.addAttribute("listChefDepartments", listChefDepartments);
        modelMap.addAttribute("listUserRole", listUserRole);
        modelMap.addAttribute("listDepartement", listDepartement);
        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("typea","");
        modelMap.addAttribute("message", "vide");
        modelMap.addAttribute("size", size);

        return "admin/gererChefDepartments/listeChefDepartments";

    }

    @GetMapping(value = "acceptCD")
    public String accept(@RequestParam int idCD,
            ModelMap modelMap,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {
        Optional<UserRole> userRole = userRoleRepository.findByRoleIdAndUserId(4L, idCD);
        if (userRole.isPresent()) {
            Optional<UserRole> userRole1 = userRoleRepository.findByRoleIdAndDepartementAndStatus(4L,userRole.get().getDepartement(), 1);
            if (userRole1.isPresent()){
                userRole.get().setStatus(3);
                userRoleRepository.save(userRole.get());
                modelMap.addAttribute("typea","danger");
                modelMap.addAttribute("message", "La departement admet deja un chef de departement, Demande va etre rejeter");


            }else{
                userRole.get().setStatus(1);
                userRoleRepository.save(userRole.get());
                modelMap.addAttribute("typea","success");
                modelMap.addAttribute("message", "Le chef de département a été accepté");
            }
    
        }else{
            modelMap.addAttribute("typea","danger");
            modelMap.addAttribute("message", "Une erreur est survenue");
        }
        Page<User> listChefDepartments = userRepository.findByRolesId(4L, PageRequest.of(page, size));
        Set<Integer> ids = listChefDepartments.stream().map(u -> u.getId())
                .collect(java.util.stream.Collectors.toSet());
        List<UserRole> listUserRole = userRoleRepository.findByUserIdInAndRoleId(ids, 4L);
        List<Departement> listDepartement = departementRepository.findAll();
        modelMap.addAttribute("listChefDepartments", listChefDepartments);
        modelMap.addAttribute("listUserRole", listUserRole);
        modelMap.addAttribute("listDepartement", listDepartement);
        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("size", size);

        return "admin/gererChefDepartments/listeChefDepartments";
    }

    @GetMapping(value = "declineCD")
    public String decline(@RequestParam int idCD,
            ModelMap modelMap,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {
        Optional<UserRole> userRole = userRoleRepository.findByRoleIdAndUserId(4L, idCD);
        if (userRole.isPresent()) {
            userRole.get().setStatus(2);
            userRoleRepository.save(userRole.get());
            modelMap.addAttribute("typea","success");
            modelMap.addAttribute("message", "Le chef de département a été refusée");
    
        }else{
            modelMap.addAttribute("typea","danger");
            modelMap.addAttribute("message", "Une erreur est survenue");
        }
        Page<User> listChefDepartments = userRepository.findByRolesId(4L, PageRequest.of(page, size));
        Set<Integer> ids = listChefDepartments.stream().map(u -> u.getId())
                .collect(java.util.stream.Collectors.toSet());
        List<UserRole> listUserRole = userRoleRepository.findByUserIdInAndRoleId(ids, 4L);
        List<Departement> listDepartement = departementRepository.findAll();
        modelMap.addAttribute("listChefDepartments", listChefDepartments);
        modelMap.addAttribute("listUserRole", listUserRole);
        modelMap.addAttribute("listDepartement", listDepartement);
        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("size", size);

        return "admin/gererChefDepartments/listeChefDepartments";
    }



}
