package com.projetIntegraion.spring.administrateur.departement.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.Classe;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.Matiere;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.Role;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.User;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.UserRole;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.repository.ClasseRepository;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.repository.RoleRepository;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.repository.UserRepository;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.repository.UserRoleRepository;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.service.MatiereService;

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

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private MatiereService matiereService;

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
        Role role = roleRepository.findByName("ROLE_ENSEIGNANT");
        UserRole userRole = userRoleRepository.findFirstByUserId(user.getId()).get();
        List<User> listeEnseignants = userRepository.findByRolesId(role.getId());
    //     List<User> listeEnseignantsFiltered = new ArrayList<>();

    //    // int[] response = new int[2000];
    //     List<Integer> response = new ArrayList<>();
    //     for (User ens : listeEnseignants) {
    //         UserRole ii = userRoleRepository.findByRoleIdAndUserId(role.getId(), ens.getId()).get();
    //         if (ii.getDepartement() == userRole.getDepartement()) {
    //             listeEnseignantsFiltered.add(ens);
    //         //    respone[ens.getId()] = userRole.getStatus();
    //             response.add(ii.getStatus());

    //         }
    //     }
    //     Page<User> pagei = new PageImpl<>(listeEnseignantsFiltered, PageRequest.of(page, size),
    //             listeEnseignantsFiltered.size());
    Page<Object[]> ListEnseignant = userRoleRepository.findByDepAndRoleEnseignant(userRole.getDepartement(), PageRequest.of(page, size));

        // List<Object[]> listClasse = classeRepository.findByDep(userRole.getDepartement());
        modelMap.addAttribute("listEns", ListEnseignant);
        // modelMap.addAttribute("response", response);
        
        // modelMap.addAttribute("listeEnseignants", listeEnseignantsFiltered);
        modelMap.addAttribute("listClasse", userRole.getStatus());
        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("pages", new int[ListEnseignant.getTotalPages()]);
        modelMap.addAttribute("size", size);

        return "ChefDepartment/Enseignent/liste";
    }

    @GetMapping(value = "/acceptEns")
    public String accept(@RequestParam int idEns,
            HttpServletRequest request,

            ModelMap modelMap,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {
        // Role role = roleRepository.findByName("ROLE_ENSEIGNANT");
        Role role = roleRepository.findByName("ROLE_ENSEIGNANT");

        Optional<UserRole> userRole = userRoleRepository.findByRoleIdAndUserId(role.getId(), idEns);

        // Optional<UserRole> userRole = userRoleRepository.findByRoleIdAndUserId(role.getId(), idEns);
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
                
         Role role = roleRepository.findByName("ROLE_ENSEIGNANT");

         Optional<UserRole> userRole = userRoleRepository.findByRoleIdAndUserId(role.getId(), idEns);
        // Optional<UserRole> userRole = userRoleRepository.findById(idEns);
        
        userRole.get().setStatus(2);
                userRoleRepository.save(userRole.get());
                modelMap.addAttribute("typea","danger");
                modelMap.addAttribute("message", "L'enseignant a été refusé");

        return this.showAllEnseignant(modelMap, request, page, size);
    }
    // @RequestMapping(value = "/listeClassesEnseignes")
    // public String showAllClassesEnseignes(ModelMap modelMap,
    //         HttpServletRequest request,
    //         @RequestParam(name = "page", defaultValue = "0") int page,
    //         @RequestParam(name = "size", defaultValue = "2") int size) {

    //     Page<Object[]> listeClasses = classeRepository.findByDepAndRoleStudent(this.getUser(request).getId(), PageRequest.of(page, size));
    //     modelMap.addAttribute("listeClasses", listeClasses);
    //     System.out.println("liste classes : "+listeClasses);
    //     modelMap.addAttribute("currentPage", page);
    //     modelMap.addAttribute("pages", new int[listeClasses.getTotalPages()]);
    //     modelMap.addAttribute("size", size);
    //     // User user = this.getUser(request);
    //     // Page<Classe> classes = userRoleRepository.getClasses(user.getId(), PageRequest.of(page, size));
    //     // modelMap.addAttribute("classes",classes);
    //     // Page<Object[]> ListClasse = classeRepository.findAllClassesParEnseignant( this.getUser(request).getId(),PageRequest.of(page, size));

    //     // Page<Matiere> matieres = matiereService.getAllMatiereByEnseignantId(this.getUser(request).getId(), page, size);
    //     //  modelMap.addAttribute("ListClasse",ListClasse);
    //     //    System.out.println("matieres: "+ListClasse.getContent());
    //     //    modelMap.addAttribute("currentPage", page);
    //     // modelMap.addAttribute("pages", new int[ListClasse.getTotalPages()]);
    //     // modelMap.addAttribute("size", size);
    //     return "ChefDepartment/Enseignent/classesEnseignes";
    // //     User user = this.getUser(request);
    // //     Role role = roleRepository.findByName("ROLE_ENSEIGNANT");
    // //     UserRole userRole = userRoleRepository.findFirstByUserId(user.getId()).get();
    // //     List<User> listeEnseignants = userRepository.findByRolesId(role.getId());
    // //     List<User> listeEnseignantsFiltered = new ArrayList<>();

    // //    // int[] response = new int[2000];
    // //     List<Integer> response = new ArrayList<>();
    // //     for (User ens : listeEnseignants) {
    // //         UserRole ii = userRoleRepository.findByRoleIdAndUserId(role.getId(), ens.getId()).get();
    // //         if (ii.getDepartement() == userRole.getDepartement()) {
    // //             listeEnseignantsFiltered.add(ens);
    // //         //    respone[ens.getId()] = userRole.getStatus();
    // //             response.add(ii.getStatus());

    // //         }
    // //     }
    // //     Page<User> pagei = new PageImpl<>(listeEnseignantsFiltered, PageRequest.of(page, size),
    // //             listeEnseignantsFiltered.size());
    // //     // List<Object[]> listClasse = classeRepository.findByDep(userRole.getDepartement());
    // //     modelMap.addAttribute("listEns", pagei);
    // //     modelMap.addAttribute("response", response);
        
    // //     modelMap.addAttribute("listeEnseignants", listeEnseignantsFiltered);
    // //     modelMap.addAttribute("listClasse", userRole.getStatus());
    // //     modelMap.addAttribute("currentPage", page);
    // //     modelMap.addAttribute("pages", new int[pagei.getTotalPages()]);
    // //     modelMap.addAttribute("size", size);

    //    // return "ChefDepartment/Enseignent/liste";
    // }

    @RequestMapping(value = "/listeClassesEnseignes")
    public String showAllClassesEnseignes(ModelMap modelMap,
            HttpServletRequest request,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {

        // User user = this.getUser(request);
        // Page<Classe> classes = userRoleRepository.getClasses(user.getId(), PageRequest.of(page, size));
        // modelMap.addAttribute("classes",classes);

        Page<Matiere> matieres = matiereService.getAllMatiereByEnseignantId(this.getUser(request).getId(), page, size);
         modelMap.addAttribute("classes",matieres);
           System.out.println("matieres: "+matieres.getContent());
           modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("pages", new int[matiereService.getAllMatiereByEnseignantId(this.getUser(request).getId(), page, size).getTotalPages()]);
        modelMap.addAttribute("size", size);
        return "ChefDepartment/Enseignent/classesEnseignes";
    }




}
