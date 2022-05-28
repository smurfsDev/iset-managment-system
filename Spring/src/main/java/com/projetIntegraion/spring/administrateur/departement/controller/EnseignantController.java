package com.projetIntegraion.spring.administrateur.departement.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.User;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.UserRole;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.repository.ClasseRepository;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.repository.UserRepository;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.repository.UserRoleRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EnseignantController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private ClasseRepository classeRepository;

    public User  getUser(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        User user = userRepository.findUserWithName(principal.getName()).get();
        return user;
    }
 
    @RequestMapping(value = "/listeEnseignants")
    public String showAllEnseignant(ModelMap modelMap,
            HttpServletRequest request,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {

        User user = this.getUser(request);
        UserRole userRole = userRoleRepository.findFirstByUserId(user.getId()).get();
        List<User> listeEnseignants = userRepository.findByRolesId(5L);
        List<User> listeEnseignantsFiltered = new ArrayList<>();

        
        for (User ens : listeEnseignants) {
            UserRole ii = userRoleRepository.findByRoleIdAndUserId(5L, ens.getId()).get();
            if (ii.getDepartement() == userRole.getDepartement()) {
                listeEnseignantsFiltered.add(ens);
               

            }
        }
        Page<User> pagei = new PageImpl<>(listeEnseignantsFiltered, PageRequest.of(page, size),
                listeEnseignantsFiltered.size());
        // List<Object[]> listClasse = classeRepository.findByDep(userRole.getDepartement());
        modelMap.addAttribute("listEns", pagei);
        modelMap.addAttribute("listeEnseignants", listeEnseignantsFiltered);
        modelMap.addAttribute("listClasse", userRole.getStatus());
        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("pages", new int[pagei.getTotalPages()]);
        modelMap.addAttribute("size", size);

        return "ChefDepartment/Enseignent/liste";
    }

    @GetMapping(value = "/acceptEns")
    public String accept(@RequestParam int idEns,
            HttpServletRequest request,

            ModelMap modelMap,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {
        Optional<UserRole> userRole = userRoleRepository.findByRoleIdAndUserId(5L, idEns);
        userRole.get().setStatus(1);
                userRoleRepository.save(userRole.get());
                modelMap.addAttribute("typea","success");
                modelMap.addAttribute("message", "L'enseignant a été accepté");

        return this.showAllEnseignant(modelMap, request, page, size);
    }

    @GetMapping(value = "/declineEns")
    public String decline(@RequestParam int idEns,
            HttpServletRequest request,

            ModelMap modelMap,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {
        Optional<UserRole> userRole = userRoleRepository.findByRoleIdAndUserId(5L, idEns);
        userRole.get().setStatus(2);
                userRoleRepository.save(userRole.get());
                modelMap.addAttribute("typea","success");
                modelMap.addAttribute("message", "L'enseignant a été refusé");

        return this.showAllEnseignant(modelMap, request, page, size);
    }




}
