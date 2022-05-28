package com.projetIntegraion.spring.Etudiant.demandeCreationClub.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.User;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.UserRole;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.repository.ClasseRepository;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.repository.UserRepository;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.repository.UserRoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StudentController {

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

    @RequestMapping(value = "/listeStudents")
    public String showAll(ModelMap modelMap,
            HttpServletRequest request,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {

        User user = this.getUser(request);
        UserRole userRole = userRoleRepository.findFirstByUserId(user.getId()).get();
        List<User> listStudents = userRepository.findByRolesId(3L);
        List<User> listStudentsFiltered = new ArrayList<>();
        for (User student : listStudents) {
            UserRole ii = userRoleRepository.findByRoleIdAndUserId(3L, student.getId()).get();
            if (ii.getDepartement() == userRole.getDepartement()) {
                listStudentsFiltered.add(student);
            }
        }
        Page<User> pagei = new PageImpl<>(listStudentsFiltered, PageRequest.of(page, size),
                listStudentsFiltered.size());
        List<Object[]> listClasse = classeRepository.findByDep(userRole.getDepartement());
        modelMap.addAttribute("listChefDepartments", pagei);
        modelMap.addAttribute("listStudents", listStudentsFiltered);
        modelMap.addAttribute("listClasse", listClasse);
        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("pages", new int[pagei.getTotalPages()]);
        modelMap.addAttribute("size", size);

        return "ChefDepartment/gererStudents/liste";
    }

    @RequestMapping(value = "/acceptSTD")
    public String accept(@RequestParam int idSTD,
            HttpServletRequest request,
            ModelMap modelMap,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {
        Optional<UserRole> userRole1 = userRoleRepository.findByRoleIdAndUserId(3L,
                idSTD);
        if (userRole1.isPresent()) {
            userRole1.get().setStatus(1);
            userRoleRepository.save(userRole1.get());
            modelMap.addAttribute("msg", "L'etudiant a été accepté");
            modelMap.addAttribute("type", "success");

        } else {
            modelMap.addAttribute("type", "danger");
            modelMap.addAttribute("msg", "Une erreur est survenue");
        }
        return this.showAll(modelMap, request, page, size);
    }

    @RequestMapping(value = "/declineSTD")
    public String decline(@RequestParam int idSTD,
            HttpServletRequest request,
            ModelMap modelMap,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {
        Optional<UserRole> userRole1 = userRoleRepository.findByRoleIdAndUserId(3L,
                idSTD);
        if (userRole1.isPresent()) {
            userRole1.get().setStatus(2);
            userRoleRepository.save(userRole1.get());
            modelMap.addAttribute("type", "success");
            modelMap.addAttribute("msg", "L'etudiant a été refusé");

        } else {
            modelMap.addAttribute("type", "danger");
            modelMap.addAttribute("msg", "Une erreur est survenue");
        }
        return this.showAll(modelMap, request, page, size);

    }

}
