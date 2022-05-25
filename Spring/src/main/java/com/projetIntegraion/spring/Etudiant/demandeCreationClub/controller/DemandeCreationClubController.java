package com.projetIntegraion.spring.Etudiant.demandeCreationClub.controller;

import java.io.IOException;
import java.security.Principal;

import org.apache.tomcat.util.codec.binary.Base64;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.DemandeCreationClub;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.Role;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.User;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.repository.RoleRepository;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.repository.UserRepository;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.service.DemandeCreationClubService;
import com.projetIntegraion.spring.blogClub.entity.Club;
import com.projetIntegraion.spring.blogClub.service.ClubService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class DemandeCreationClubController {
    @Autowired
    private DemandeCreationClubService DemandeCreationClubService;
    @Autowired
    private ClubService clubService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    public User getUser(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        User user = userRepository.findUserWithName(principal.getName()).get();
        return user;
    }

    @RequestMapping("/listeDcc")
    public String showList(ModelMap modelMap,
            HttpServletRequest request,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {
        if (request.isUserInRole("ROLE_STUDENT")||request.isUserInRole("ROLE_RESPONSABLE")) {
            Page<DemandeCreationClub> listDemandeCreationClub = DemandeCreationClubService
                    .getAuthUserDemandsParPage(this.getUser(request), page, size);
            System.out.println(listDemandeCreationClub);
            modelMap.addAttribute("Dccs", listDemandeCreationClub);
            modelMap.addAttribute("currentPage", page);
            modelMap.addAttribute("size", size);
            return "demandeCreationClub/list";
        }

        Page<DemandeCreationClub> listDcc = DemandeCreationClubService.getAllDemandeCreationClubParPage(page, size);
        modelMap.addAttribute("Dccs", listDcc);
        modelMap.addAttribute("pages", new int[listDcc.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);

        return "demandeCreationClub/list";
    }

    @RequestMapping("/showCreateDcc")
    public String createDcc(ModelMap modelMap,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {
        modelMap.addAttribute("Dcc", new DemandeCreationClub());
        modelMap.addAttribute("edit", false);
        modelMap.addAttribute("pages",
                new int[DemandeCreationClubService.getAllDemandeCreationClubParPage(page, size).getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        return "demandeCreationClub/form";
    }

    @RequestMapping("/saveDcc")
    public String saveDcc(ModelMap modelMap,
            @RequestParam("image") MultipartFile multipartFile,
            @Valid DemandeCreationClub Dcc,
            BindingResult bindingResult,
            HttpServletRequest request,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) throws IOException {
        if (bindingResult.hasErrors()) {
            modelMap.addAttribute("org.springframework.validation.BindingResult.Dcc", bindingResult);
            modelMap.addAttribute("Dcc", Dcc);
            return "demandeCreationClub/form";
        } else {
            DemandeCreationClub dc = new DemandeCreationClub();
            dc.setNomClub(Dcc.getNomClub());
            dc.setDateCreation(Dcc.getDateCreation());
            dc.setActivite(Dcc.getActivite());
            dc.setPresident(Dcc.getPresident());
            dc.setVicePresident(Dcc.getVicePresident());
            dc.setResponsableClub(this.getUser(request));
            dc.setLogo(new String(Base64.encodeBase64(multipartFile.getBytes())));
            dc = DemandeCreationClubService.save(dc);
            modelMap.addAttribute("Dcc", new DemandeCreationClub());
            modelMap.addAttribute("msg", "Demande de création de club enregistrée avec succès");
            modelMap.addAttribute("type", "success");
            modelMap.addAttribute("pages",
                    new int[DemandeCreationClubService.getAllDemandeCreationClubParPage(page, size).getTotalPages()]);
            return this.showList(modelMap, request, page, size);
        }
    }

    @RequestMapping("/deleteDcc")
    public String deleteDcc(@RequestParam("id") Long id, ModelMap modelMap,
            HttpServletRequest request,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {

        if (DemandeCreationClubService.deleteById(id)) {
            modelMap.addAttribute("type", "danger");
            modelMap.addAttribute("msg", "Demande de creation de club supprimée avec succès");
        } else {
            modelMap.addAttribute("type", "danger");
            modelMap.addAttribute("msg", "Demande de creation de club non supprimée : Id non trouvé");
        }
        return this.showList(modelMap, request, page, size);

    }

    @RequestMapping("/modifierDcc")
    public String showUpdateDcc(@RequestParam("id") Long id, ModelMap modelMap,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {
        DemandeCreationClub Dcc = DemandeCreationClubService.getDemandeCreationClub(id);
        modelMap.addAttribute("Dcc", Dcc);
        modelMap.addAttribute("edit", true);
        modelMap.addAttribute("pages",
                new int[DemandeCreationClubService.getAllDemandeCreationClubParPage(page, size).getTotalPages()]);
        return "demandeCreationClub/form";
    }

    @RequestMapping("/updateDcc")
    public String updateDcc(ModelMap modelMap,
            @RequestParam(name = "image") MultipartFile multipartFile,
            @Valid DemandeCreationClub Dcc,
            BindingResult bindingResult,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size,
            HttpServletRequest request) throws IOException {
        if (bindingResult.hasErrors()) {
            modelMap.addAttribute("org.springframework.validation.BindingResult.Dcc", bindingResult);
            modelMap.addAttribute("Dcc", Dcc);
            modelMap.addAttribute("edit", true);
            return "demandeCreationClub/form";
        } else {
            DemandeCreationClub dc = new DemandeCreationClub();
            dc.setId(Dcc.getId());
            dc.setNomClub(Dcc.getNomClub());
            dc.setDateCreation(Dcc.getDateCreation());
            dc.setActivite(Dcc.getActivite());
            dc.setPresident(Dcc.getPresident());
            dc.setVicePresident(Dcc.getVicePresident());
            if (multipartFile.getSize() != 0) {
                dc.setLogo(new String(Base64.encodeBase64(multipartFile.getBytes())));
            } else {
                dc.setLogo(DemandeCreationClubService.getDemandeCreationClub(Dcc.getId()).getLogo());
            }
            dc = DemandeCreationClubService.save(dc);
            modelMap.addAttribute("Dcc", new DemandeCreationClub());
            modelMap.addAttribute("pages",
                    new int[DemandeCreationClubService.getAllDemandeCreationClubParPage(page, size).getTotalPages()]);
            modelMap.addAttribute("type", "warning");
            modelMap.addAttribute("msg", "Demande de creation de club modifiée avec succès");
            return this.showList(modelMap, request, page, size);
        }
    }

    @RequestMapping("/accept")
    public String acceptDcc(@RequestParam("id") Long id, ModelMap modelMap,
            @RequestParam(name = "page", defaultValue = "0") int page,
            HttpServletRequest request,
            @RequestParam(name = "size", defaultValue = "2") int size) {
        DemandeCreationClub dc = DemandeCreationClubService.getDemandeCreationClub(id);
        dc.setStatus(1);
        DemandeCreationClubService.save(dc);
        Club c = new Club();
        c.setNomClub(dc.getNomClub());
        c.setDateCreation(dc.getDateCreation());
        c.setPresident(dc.getPresident());
        c.setVicePresident(dc.getVicePresident());
        c.setResponsableClub(dc.getResponsableClub());
        c.setLogo(dc.getLogo());
        dc.setAdmin(this.getUser(request));

        c = clubService.save(c);

        Role res = roleRepository.findByName("ROLE_RESPONSABLE");
        User r = c.getResponsableClub();
        r.getRoles().add(res);
        userRepository.save(r);

        modelMap.addAttribute("Dcc", new DemandeCreationClub());
        modelMap.addAttribute("pages",
                new int[DemandeCreationClubService.getAllDemandeCreationClubParPage(page, size).getTotalPages()]);
        modelMap.addAttribute("type", "success");
        modelMap.addAttribute("msg", "Demande de creation de club acceptée avec succès");
        return this.showList(modelMap, request, page, size);
    }

    @RequestMapping("/decline")
    public String declineDcc(@RequestParam("id") Long id, ModelMap modelMap,
            HttpServletRequest request,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {
        DemandeCreationClub dc = DemandeCreationClubService.getDemandeCreationClub(id);
        dc.setAdmin(this.getUser(request));
        dc.setStatus(2);
        DemandeCreationClubService.save(dc);
        modelMap.addAttribute("Dcc", new DemandeCreationClub());
        modelMap.addAttribute("pages",
                new int[DemandeCreationClubService.getAllDemandeCreationClubParPage(page, size).getTotalPages()]);
        modelMap.addAttribute("type", "success");
        modelMap.addAttribute("msg", "Demande de creation de club refusée avec succès");
        return this.showList(modelMap, request, page, size);
    }

    @RequestMapping("/searchProduit")
    public String searchProduit(@RequestParam("name") String name, ModelMap modelMap,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {

        Page<DemandeCreationClub> prods = DemandeCreationClubService.getDemandeCreationClubParNom(name, page, size);
        modelMap.addAttribute("Dccs", prods);
        modelMap.addAttribute("pages", new int[prods.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("name", name);
        return "demandeCreationClub/list";
    }

}
