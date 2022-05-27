package com.projetIntegraion.spring.demandePlannificationEvent.controller;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.User;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.repository.RoleRepository;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.repository.UserRepository;
import com.projetIntegraion.spring.blogClub.entity.Club;
import com.projetIntegraion.spring.blogClub.service.ClubService;
import com.projetIntegraion.spring.demandePlannificationEvent.entity.DemandeEvent;
import com.projetIntegraion.spring.demandePlannificationEvent.service.DemandeAdhesionEventService;
import com.projetIntegraion.spring.demandePlannificationEvent.service.DemandeEventService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DemandePlannificationEventController {
    @Autowired
    private DemandeEventService DemandeEventService;
    @Autowired
    private ClubService clubService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private DemandeAdhesionEventService demandeAdhesionEventService;
    

    public User getUser(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        User user = userRepository.findUserWithName(principal.getName()).get();
        return user;
    }

    @RequestMapping("/listeDE")
    public String showList(ModelMap modelMap,
            HttpServletRequest request,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {
        if (request.isUserInRole("ROLE_RESPONSABLE")) {
            Page<DemandeEvent> listDemandeEvent = DemandeEventService
                    .getAuthUserDemandsParPage(this.getUser(request), page, size);
          
            modelMap.addAttribute("DE", listDemandeEvent);
            modelMap.addAttribute("currentPage", page);
            modelMap.addAttribute("size", size);
            return "DemandeEvent/list";
        }

        Page<DemandeEvent> listDemandeEvent = DemandeEventService.getAllDemandeEventParPage(page, size);
        modelMap.addAttribute("DE", listDemandeEvent);
        modelMap.addAttribute("pages", new int[listDemandeEvent.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);

        return "DemandeEvent/list";
    }

    @RequestMapping("/showCreateDE")
    public String createDE(ModelMap modelMap,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {
        modelMap.addAttribute("DE", new DemandeEvent());
        modelMap.addAttribute("edit", false);
        modelMap.addAttribute("pages",
                new int[DemandeEventService.getAllDemandeEventParPage(page, size).getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        return "DemandeEvent/form";
    }

    @RequestMapping("/saveDE")
    public String saveDE(ModelMap modelMap,
            @Valid DemandeEvent DE,
            BindingResult bindingResult,
            HttpServletRequest request,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) throws IOException {
        if (bindingResult.hasErrors()) {
            modelMap.addAttribute("org.springframework.validation.BindingResult.DE", bindingResult);
            modelMap.addAttribute("DE", DE);
            return "DemandeEvent/form";
        } else {
            DemandeEvent de = new DemandeEvent();
            de.setNomEvent(DE.getNomEvent());
            de.setStatus(0);
            de.setDescription(DE.getDescription());
            de.setAdmin(null);
            de.setDateEvent(DE.getDateEvent());
            Club club = clubService.getClubParResponsable(this.getUser(request).getId()).get();
            de.setResponsableClub(this.getUser(request));
            de.setClub(club);
            de = DemandeEventService.save(de);
            modelMap.addAttribute("DE", new DemandeEvent());
            modelMap.addAttribute("msg", "Demande de de plannification d'evenement enregistrée avec succès");
            modelMap.addAttribute("type", "success");
            modelMap.addAttribute("pages",
                    new int[DemandeEventService.getAllDemandeEventParPage(page, size).getTotalPages()]);
            return this.showList(modelMap, request, page, size);
        }
    }

    @RequestMapping("/deleteDE")
    public String deleteDE(@RequestParam("id") Long id, ModelMap modelMap,
            HttpServletRequest request,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {
                //demandeEventService.
            DemandeEvent d = DemandeEventService.getDemandeEvent(id);
           

        if (demandeAdhesionEventService.deleteById(id)) {
            
            modelMap.addAttribute("type", "danger");
            modelMap.addAttribute("msg", "Demande de plannification d'evenement supprimée avec succès");
        } else {
            modelMap.addAttribute("type", "danger");
            modelMap.addAttribute("msg", "Demande de plannification d'evenement non supprimée : Id non trouvé");
        }
        return this.showList(modelMap, request, page, size);

    }

    @RequestMapping("/modifierDE")
    public String showUpdateDE(@RequestParam("id") Long id, ModelMap modelMap,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {
        DemandeEvent DE = DemandeEventService.getDemandeEvent(id);
        modelMap.addAttribute("DE", DE);
        modelMap.addAttribute("edit", true);
        modelMap.addAttribute("pages",
                new int[DemandeEventService.getAllDemandeEventParPage(page, size).getTotalPages()]);
        return "DemandeEvent/form";
    }

    @RequestMapping("/updateDE")
    public String updateDE(ModelMap modelMap,
            @Valid DemandeEvent DE,
            BindingResult bindingResult,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size,
            HttpServletRequest request) throws IOException {
        if (bindingResult.hasErrors()) {
            modelMap.addAttribute("org.springframework.validation.BindingResult.DE", bindingResult);
            modelMap.addAttribute("DE", DE);
            modelMap.addAttribute("edit", true);
            return "DemandeEvent/form";
        } else {
            DemandeEvent de = new DemandeEvent();
            de.setId(DE.getId());
            de.setNomEvent(DE.getNomEvent());
            de.setStatus(0);
            de.setDescription(DE.getDescription());
            de.setAdmin(null);
            de.setDateEvent(DE.getDateEvent());
            Club club = clubService.getClubParResponsable(this.getUser(request).getId()).get();
            de.setResponsableClub(this.getUser(request));
            de.setClub(club);
            de = DemandeEventService.save(de);
            modelMap.addAttribute("DE", new DemandeEvent());
            modelMap.addAttribute("pages",
                    new int[DemandeEventService.getAllDemandeEventParPage(page, size).getTotalPages()]);
            modelMap.addAttribute("type", "warning");
            modelMap.addAttribute("msg", "Demande de plannification d'évenement modifiée avec succès");
            return this.showList(modelMap, request, page, size);
        }
    }
    @RequestMapping("/acceptDE")
    public String acceptDE(@RequestParam("id") Long id, ModelMap modelMap,
            @RequestParam(name = "page", defaultValue = "0") int page,
            HttpServletRequest request,
            @RequestParam(name = "size", defaultValue = "2") int size) {
        DemandeEvent de = DemandeEventService.getDemandeEvent(id);
        de.setStatus(1);
        de.setAdmin(this.getUser(request));
        DemandeEventService.save(de);
        // Club c = new Club();
        // de.setNomEvent(de.getNomEvent());
        // de.setDescription(de.getDescription());
        // de.setClub(de.getClub());
        // de.setDateEvent(de.getDateEvent());

        // c.setNomClub(de.getNomClub());
        // c.setDateCreation(de.getDateCreation());
        // c.setPresident(de.getPresident());
        // c.setVicePresident(de.getVicePresident());
        // c.setResponsableClub(de.getResponsableClub());
        // c.setLogo(de.getLogo());
        de.setAdmin(this.getUser(request));

     

        modelMap.addAttribute("DE", new DemandeEvent());
        modelMap.addAttribute("pages",
                new int[DemandeEventService.getAllDemandeEventParPage(page, size).getTotalPages()]);
        modelMap.addAttribute("type", "success");
        modelMap.addAttribute("msg", "Demande de plannification d'evenement acceptée avec succès");
        return this.showList(modelMap, request, page, size);
    }

    @RequestMapping("/declineDE")
    public String declineDE(@RequestParam("id") Long id, ModelMap modelMap,
            HttpServletRequest request,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {
        DemandeEvent dc = DemandeEventService.getDemandeEvent(id);
        dc.setAdmin(this.getUser(request));
        dc.setStatus(2);
        DemandeEventService.save(dc);
        modelMap.addAttribute("Dcc", new DemandeEvent());
        modelMap.addAttribute("pages",
                new int[DemandeEventService.getAllDemandeEventParPage(page, size).getTotalPages()]);
        modelMap.addAttribute("type", "success");
        modelMap.addAttribute("msg", "Demande de plannification d'evenement refusée avec succès");
        return this.showList(modelMap, request, page, size);
    }

    

  
    

}
