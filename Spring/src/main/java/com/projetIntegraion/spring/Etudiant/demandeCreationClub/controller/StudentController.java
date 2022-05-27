package com.projetIntegraion.spring.Etudiant.demandeCreationClub.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.Classe;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.User;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.UserRole;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.repository.ClasseRepository;
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
public class StudentController {

    @Autowired
    private DepartementRepository departementRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private ClasseRepository classeRepository;

    public User getUser(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        User user = userRepository.findUserWithName(principal.getName()).get();
        return user;
    }

    @GetMapping(value = "/listeStudents")
    public String showAll(ModelMap modelMap,
            HttpServletRequest request,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {

        User user = this.getUser(request);
        UserRole userRole = userRoleRepository.findFirstByUserId(user.getId()).get();

        Page<User> listStudents = userRepository.findByRolesId(3L, PageRequest.of(page, size));
        List<Object[]> listClasse = classeRepository.findByDep(userRole.getDepartement());
        modelMap.addAttribute("listChefDepartments", listStudents);
        modelMap.addAttribute("listClasse", listClasse);
        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("typea", "");
        modelMap.addAttribute("message", "vide");
        modelMap.addAttribute("pages", new int[listStudents.getTotalPages()]);
        modelMap.addAttribute("size", size);

        return "ChefDepartment/gererStudents/liste";
    }

    @GetMapping(value = "/acceptSTD")
    public String accept(@RequestParam int idSTD,
             HttpServletRequest request,
            ModelMap modelMap,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {
                User user = this.getUser(request);
                UserRole userRole = userRoleRepository.findFirstByUserId(user.getId()).get();
        
        Optional<UserRole> userRole1 = userRoleRepository.findByRoleIdAndUserId(3L,
                idSTD);
        if (userRole1.isPresent()) {
            userRole1.get().setStatus(1);
            userRoleRepository.save(userRole1.get());
            modelMap.addAttribute("typea", "success");
            modelMap.addAttribute("message", "L'etudiant a été accepté");

        } else {
            modelMap.addAttribute("typea", "danger");
            modelMap.addAttribute("message", "Une erreur est survenue");
        }

        Page<User> listStudents = userRepository.findByRolesId(3L, PageRequest.of(page, size));
        List<Object[]> listClasse = classeRepository.findByDep(userRole.getDepartement());
        modelMap.addAttribute("listChefDepartments", listStudents);
        modelMap.addAttribute("listClasse", listClasse);
        modelMap.addAttribute("pages", new int[listStudents.getTotalPages()]);

        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("size", size);

        return "ChefDepartment/gererStudents/liste";
        
    }

    @GetMapping(value = "/declineSTD")
    public String decline(@RequestParam int idSTD,
             HttpServletRequest request,
            ModelMap modelMap,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {
                User user = this.getUser(request);
                UserRole userRole = userRoleRepository.findFirstByUserId(user.getId()).get();
        
        Optional<UserRole> userRole1 = userRoleRepository.findByRoleIdAndUserId(3L,
                idSTD);
        if (userRole1.isPresent()) {
            userRole1.get().setStatus(2);
            userRoleRepository.save(userRole1.get());
            modelMap.addAttribute("typea", "success");
            modelMap.addAttribute("message", "L'etudiant a été refusé");

        } else {
            modelMap.addAttribute("typea", "danger");
            modelMap.addAttribute("message", "Une erreur est survenue");
        }

        Page<User> listStudents = userRepository.findByRolesId(3L, PageRequest.of(page, size));
        List<Object[]> listClasse = classeRepository.findByDep(userRole.getDepartement());
        modelMap.addAttribute("listChefDepartments", listStudents);
        modelMap.addAttribute("listClasse", listClasse);
        modelMap.addAttribute("pages", new int[listStudents.getTotalPages()]);

        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("size", size);

        return "ChefDepartment/gererStudents/liste";
        
    }


}
