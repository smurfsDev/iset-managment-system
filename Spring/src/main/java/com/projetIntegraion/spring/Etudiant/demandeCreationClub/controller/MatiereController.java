package com.projetIntegraion.spring.Etudiant.demandeCreationClub.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.Classe;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.Matiere;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.Role;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.User;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.UserRole;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.repository.ClasseRepository;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.repository.MatiereRepository;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.repository.RoleRepository;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.repository.UserRepository;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.repository.UserRoleRepository;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.service.MatiereService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MatiereController {
    
    @Autowired
    private MatiereService matiereService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private ClasseRepository classeRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private MatiereRepository matiereRepository;

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
        modelMap.addAttribute("idC", idC);
        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("typea", "");
        modelMap.addAttribute("message", "vide");
        modelMap.addAttribute("size", size);
        modelMap.addAttribute("pages",
                new int[matiereService.getAllMatiereByClasseId(idC,
                        page, size).getTotalPages()]);

        return "ChefDepartment/Class/matieres";

    }

    @RequestMapping("/showCreateMatieres")
    public String createMatiere(ModelMap modelMap,
            @RequestParam Long idC,

            HttpServletRequest request,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {
        // User user = this.getUser(request);
        // UserRole userRole = userRoleRepository.findFirstByUserId(user.getId()).get();

        // List<User> enseignents = userRepository.findAll();
        Role role = roleRepository.findByName("ROLE_ENSEIGNANT");
        
      //  List<User> enseignents = userRepository.findByRolesId(role.getId());

        
        Optional<UserRole> enseignents = userRoleRepository.findByRoleIdAndStatus(role.getId(), 1);
        if (enseignents.isPresent()){
            modelMap.addAttribute("enseignants", enseignents.get().getUser());
            modelMap.addAttribute("ens", true);

        }
        else {
            modelMap.addAttribute("ens", false);
        }
        
        // Matiere matiere = new Matiere();
        // Classe c =  classeRepository.findById(idC).get();
        // matiere.setClasse(c);
        modelMap.addAttribute("idC", idC);
        modelMap.addAttribute("matiere", new Matiere());
        modelMap.addAttribute("edit", false);
        modelMap.addAttribute("pages",
            new int[matiereService.getAllMatiereByClasseId(idC,
            page, size).getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        return "ChefDepartment/Class/createMatiere";
    }

    @RequestMapping("/createMatiere")
    public String saveMatiere(ModelMap modelMap,
          
            @RequestParam(value = "idC") Long idC,
            @RequestParam(value = "enseignant") int enseignant,

            @Valid Matiere matiere,
            BindingResult bindingResult,
            HttpServletRequest request,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) throws IOException {
        if (bindingResult.hasErrors()) {
            modelMap.addAttribute("org.springframework.validation.BindingResult.matiere", bindingResult);
            modelMap.addAttribute("matiere", matiere);
        modelMap.addAttribute("idC", idC);

           return "ChefDepartment/Class/createMatiere";
        } else {
          
            User ens = userRepository.getById(enseignant);
            matiere.setEnseignant(ens);
           Classe c =  classeRepository.getById(idC);
            matiere.setClasse(c);
          System.out.println("matiereeee : "+matiere);
            matiereService.save(matiere);
        
            modelMap.addAttribute("currentPage", page);
            modelMap.addAttribute("typea", "success");
          

            modelMap.addAttribute("message", "Matiere ajoutée avec succès");
            modelMap.addAttribute("size", size);

            return this.getAllMatieres(modelMap, idC, request, page, size);

        }

    }

    @GetMapping(value = "/deleteMatiere")
    public String DeleteMatiere(ModelMap modelMap,
            HttpServletRequest request,
            @RequestParam(name = "id", defaultValue = "0") Long id,
            @RequestParam(name = "idC", defaultValue = "0") Long idC,

            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {
        matiereService.deleteById(id);

        User user = this.getUser(request);
        UserRole userRole = userRoleRepository.findFirstByUserId(user.getId()).get();

        // Page<Classe> pageClasses = matiereService.getAllMatiereByClasseId(Long.parseLong(userRole.getDepartement().toString()),
        //         page, size);
        Page<Matiere> matieres = matiereService.getAllMatiereByClasseId(idC, page, size);

        modelMap.addAttribute("matieres", matieres);
        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("pages",
                new int[matiereService.getAllMatiereByClasseId(idC,
                page, size).getTotalPages()]);
        modelMap.addAttribute("typea", "danger");
        modelMap.addAttribute("message", "Matiere supprimé avec succès");
        modelMap.addAttribute("size", size);

        return "ChefDepartment/Class/matieres";

    }

    @RequestMapping("/modifierMatiere")
           // @RequestParam(name = "idC", defaultValue = "0") Long idC,
            public String showUpdateDcc(@RequestParam("id") Long id,
            @RequestParam(name = "idC") Long idC, ModelMap modelMap,
            HttpServletRequest request,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {
        Matiere matiere = matiereService.getMatiere(id);
        User user = this.getUser(request);
        UserRole userRole = userRoleRepository.findFirstByUserId(user.getId()).get();
        Role role = roleRepository.findByName("ROLE_ENSEIGNANT");
        
        //  List<User> enseignents = userRepository.findByRolesId(role.getId());
  
          
          Optional<UserRole> enseignents = userRoleRepository.findByRoleIdAndStatus(role.getId(), 1);
          if (enseignents.isPresent()){
              modelMap.addAttribute("enseignants", enseignents.get().getUser());
              modelMap.addAttribute("ens", true);
  
          }
          else {
              modelMap.addAttribute("ens", false);
          }
        modelMap.addAttribute("idC", idC);

        modelMap.addAttribute("matiere", matiere);
        modelMap.addAttribute("edit", true);
        modelMap.addAttribute("pages",
            new int[matiereService.getAllMatiereByClasseId(idC,
            page, size).getTotalPages()]);
            return "ChefDepartment/Class/createMatiere";

    }

    @RequestMapping("/updateMatiere")
    public String updateMatiere(@RequestParam(name = "idC") Long idC,
    @RequestParam(name="id") Long id,
            ModelMap modelMap,
            Matiere matiere,
            BindingResult bindingResult,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size,
            HttpServletRequest request) throws IOException {
                
        if (bindingResult.hasErrors()) {
            modelMap.addAttribute("org.springframework.validation.BindingResult.matiere", bindingResult);
            modelMap.addAttribute("matiere", matiere);
            modelMap.addAttribute("edit", true);
            return "ChefDepartment/Class/createMatiere";

        } else {
            User user = this.getUser(request);
            UserRole userRole = userRoleRepository.findFirstByUserId(user.getId()).get();

            Matiere matieree = new Matiere();
            matieree.setId(id);
            matieree.setCoefficient(matiere.getCoefficient());
            matieree.setEnseignant(matiere.getEnseignant());
            matieree.setSemestre(matiere.getSemestre());
            matieree.setNom(matiere.getNom());
            matieree.setClasse(classeRepository.findById(idC).get());

            matiereService.save(matieree);
            // Page<Classe> matieres = matiereService.getAllClasseById(
            //         Long.parseLong(userRole.getDepartement().toString()),
            //         page, size);
        Page<Matiere> matieres = matiereService.getAllMatiereByClasseId(idC, page, size);

            modelMap.addAttribute("matieres", matieres);
            modelMap.addAttribute("currentPage", page);
            modelMap.addAttribute("typea", "warning");
            modelMap.addAttribute("pages",
            new int[matiereService.getAllMatiereByClasseId(idC,
            page, size).getTotalPages()]);

            modelMap.addAttribute("message", "Matiere modifiée avec succès");
            modelMap.addAttribute("size", size);

            return this.getAllMatieres(modelMap, idC, request, page, size);

        }
    }

    @RequestMapping("/mesMatieres")
    public String GetMyMatieres(
        ModelMap modelMap,
        HttpServletRequest request
    ) {

        User user = this.getUser(request);
        UserRole userRole = userRoleRepository.findFirstByUserId(user.getId()).get();
        Classe classe = classeRepository.findById(Long.parseLong(userRole.getClasse().toString())).get();
        List<Matiere> matieres = matiereRepository.findByClasseId(classe.getId());
        System.out.println("AHA"+matieres.get(1).getNom());
        modelMap.addAttribute("matieres", matieres);
        return "/studentMatiere";
    
    }
    


    
}
