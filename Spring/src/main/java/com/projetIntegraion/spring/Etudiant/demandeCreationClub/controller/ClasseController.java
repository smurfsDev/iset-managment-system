package com.projetIntegraion.spring.Etudiant.demandeCreationClub.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.Classe;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.Matiere;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.Role;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.User;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.UserRole;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.repository.RoleRepository;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.repository.UserRepository;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.repository.UserRoleRepository;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.service.ClasseService;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.service.MatiereService;
import com.projetIntegraion.spring.administrateur.departement.repository.DepartementRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ClasseController {

    @Autowired
    private ClasseService classeService;

    @Autowired
    private MatiereService matiereService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private DepartementRepository departementRepository;

    public User getUser(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        User user = userRepository.findUserWithName(principal.getName()).get();
        return user;
    }

    @GetMapping(value = "/classe")
    public String getAllClasses(ModelMap modelMap,
            HttpServletRequest request,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {
        User user = this.getUser(request);
        UserRole userRole = userRoleRepository.findFirstByUserId(user.getId()).get();

        Page<Classe> pageClasses = classeService.getAllClasseById(Long.parseLong(userRole.getDepartement().toString()),
                page, size);
        modelMap.addAttribute("pageClasses", pageClasses);
        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("typea", "");
        modelMap.addAttribute("message", "vide");
        modelMap.addAttribute("size", size);
        modelMap.addAttribute("pages",
                new int[classeService.getAllClasseById(Long.parseLong(userRole.getDepartement().toString()),
                        page, size).getTotalPages()]);

        return "ChefDepartment/Class/listeClass";

    }

    @GetMapping(value = "/deleteClass")
    public String DeleteClass(ModelMap modelMap,
            HttpServletRequest request,
            @RequestParam(name = "id", defaultValue = "0") Long id,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {
        classeService.deleteById(id);

        User user = this.getUser(request);
        UserRole userRole = userRoleRepository.findFirstByUserId(user.getId()).get();

        Page<Classe> pageClasses = classeService.getAllClasseById(Long.parseLong(userRole.getDepartement().toString()),
                page, size);
        modelMap.addAttribute("pageClasses", pageClasses);
        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("pages",
                new int[classeService.getAllClasseById(Long.parseLong(userRole.getDepartement().toString()),
                        page, size).getTotalPages()]);

        modelMap.addAttribute("typea", "danger");
        modelMap.addAttribute("message", "Classe supprimé avec succès");
        modelMap.addAttribute("size", size);

        return "ChefDepartment/Class/listeClass";
    }

    @RequestMapping("/showCreateClass")
    public String createclasse(ModelMap modelMap,
            HttpServletRequest request,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {
        User user = this.getUser(request);
        UserRole userRole = userRoleRepository.findFirstByUserId(user.getId()).get();

        modelMap.addAttribute("classe", new Classe());
        modelMap.addAttribute("edit", false);
        modelMap.addAttribute("pages",
                new int[classeService.getAllClasseById(Long.parseLong(userRole.getDepartement().toString()),
                        page, size).getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        return "ChefDepartment/Class/form";
    }

    @RequestMapping("/createClass")
    public String saveclasse(ModelMap modelMap,
            @Valid Classe classe,
            BindingResult bindingResult,
            HttpServletRequest request,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) throws IOException {
        if (bindingResult.hasErrors()) {
            modelMap.addAttribute("org.springframework.validation.BindingResult.classe", bindingResult);
            modelMap.addAttribute("classe", classe);
            return "ChefDepartment/Class/form";
        } else {
            User user = this.getUser(request);
            UserRole userRole = userRoleRepository.findFirstByUserId(user.getId()).get();

            classe.setDepartement(departementRepository.getById(Long.parseLong(userRole.getDepartement().toString())));
            classeService.save(classe);
            Page<Classe> pageClasses = classeService.getAllClasseById(
                    Long.parseLong(userRole.getDepartement().toString()),
                    page, size);
            modelMap.addAttribute("pageClasses", pageClasses);
            modelMap.addAttribute("currentPage", page);
            modelMap.addAttribute("typea", "success");
            modelMap.addAttribute("pages",
                    new int[classeService.getAllClasseById(Long.parseLong(userRole.getDepartement().toString()),
                            page, size).getTotalPages()]);

            modelMap.addAttribute("message", "Classe ajoutée avec succès");
            modelMap.addAttribute("size", size);

            return "ChefDepartment/Class/listeClass";

        }

    }

    @RequestMapping("/modifierClass")
    public String showUpdateDcc(@RequestParam("id") Long id, ModelMap modelMap,
            HttpServletRequest request,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {
        Classe classe = classeService.getClasse(id);
        User user = this.getUser(request);
        UserRole userRole = userRoleRepository.findFirstByUserId(user.getId()).get();

        modelMap.addAttribute("classe", classe);
        modelMap.addAttribute("edit", true);
        modelMap.addAttribute("pages",
                new int[classeService.getAllClasseById(Long.parseLong(userRole.getDepartement().toString()),
                        page, size).getTotalPages()]);
        return "ChefDepartment/Class/form";
    }

    @RequestMapping("/updateClass")
    public String updateClass(ModelMap modelMap,
            Classe classe,
            BindingResult bindingResult,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size,
            HttpServletRequest request) throws IOException {
                if(classe.getAbreviation().length() < 3 || classe.getAbreviation().length()>50){
                        bindingResult.rejectValue("abreviation", "abreviation.size", "Abreviation doit contenir entre 3 et 50 caractères");
                }
                if(classe.getNom().length() < 3 || classe.getNom().length()>50){
                        bindingResult.rejectValue("nom", "nom.size", "Nom doit contenir entre 3 et 50 caractères");
                }
        if (bindingResult.hasErrors()) {
            modelMap.addAttribute("org.springframework.validation.BindingResult.classe", bindingResult);
            modelMap.addAttribute("classe", classe);
            modelMap.addAttribute("edit", true);
            return "ChefDepartment/Class/form";
        } else {
            User user = this.getUser(request);
            UserRole userRole = userRoleRepository.findFirstByUserId(user.getId()).get();

            classe.setDepartement(departementRepository.getById(Long.parseLong(userRole.getDepartement().toString())));
            classeService.save(classe);
            Page<Classe> pageClasses = classeService.getAllClasseById(
                    Long.parseLong(userRole.getDepartement().toString()),
                    page, size);
            modelMap.addAttribute("pageClasses", pageClasses);
            modelMap.addAttribute("currentPage", page);
            modelMap.addAttribute("typea", "warning");
            modelMap.addAttribute("pages",
                    new int[classeService.getAllClasseById(Long.parseLong(userRole.getDepartement().toString()),
                            page, size).getTotalPages()]);

            modelMap.addAttribute("message", "Classe modifiée avec succès");
            modelMap.addAttribute("size", size);

            return "ChefDepartment/Class/listeClass";
        }
    }

}
