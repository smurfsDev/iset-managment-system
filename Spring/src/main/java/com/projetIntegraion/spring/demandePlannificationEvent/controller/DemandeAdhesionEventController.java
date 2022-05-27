package com.projetIntegraion.spring.demandePlannificationEvent.controller;

import java.security.Principal;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.User;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.repository.RoleRepository;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.repository.UserRepository;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.service.DemandeAdhesionClubService;
import com.projetIntegraion.spring.blogClub.entity.Club;
import com.projetIntegraion.spring.blogClub.service.ClubService;
import com.projetIntegraion.spring.demandePlannificationEvent.entity.DemandeAdhesionEvent;
import com.projetIntegraion.spring.demandePlannificationEvent.entity.DemandeEvent;
import com.projetIntegraion.spring.demandePlannificationEvent.service.DemandeAdhesionEventService;
import com.projetIntegraion.spring.demandePlannificationEvent.service.DemandeEventService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DemandeAdhesionEventController {
    @Autowired
    DemandeEventService demandeEventService;
    @Autowired
    DemandeAdhesionEventService demandeAdhesionEventService;
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
    @RequestMapping("/listeEvent")
    public String showList(ModelMap modelMap,
            HttpServletRequest request,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {
        

        Page<DemandeEvent> listEvent = demandeEventService.findApprouvedEvent(page, size);
        modelMap.addAttribute("DE", listEvent);
        modelMap.addAttribute("pages", new int[listEvent.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);

        return "demandeAdhesion/list";
    }
    @RequestMapping("/joinEvent")
    public String joinEvent(@RequestParam("id") Long id, ModelMap modelMap,
            HttpServletRequest request,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {
                System.out.println("id = "+id);
                
        if (demandeEventService.existsById(id)) {

            Club club = demandeEventService.getDemandeEvent(id).getClub();
            Boolean c = demandeAdhesionEventService.existsByIds(club.getId(), this.getUser(request).getId());
            Boolean isThisClubOwner = clubService.isThisClubOwner(club.getId(), this.getUser(request));
            if (c){
                Page<DemandeEvent> Dacs = demandeEventService.findApprouvedEvent( page, size);
                        modelMap.addAttribute("DE", Dacs);
                        modelMap.addAttribute("pages", new int[Dacs.getTotalPages()]);
                        modelMap.addAttribute("currentPage", 0);
                        modelMap.addAttribute("exist", 1);
                        return "demandeAdhesion/list";
            }
            else if(isThisClubOwner){
                    Page<DemandeEvent> Dacs = demandeEventService.findApprouvedEvent( page, size);
                    modelMap.addAttribute("DE", Dacs);
                    modelMap.addAttribute("pages", new int[Dacs.getTotalPages()]);
                    modelMap.addAttribute("currentPage", 0);
                    modelMap.addAttribute("clubOwner", 1);
                    return "demandeAdhesion/list";
                }
            DemandeAdhesionEvent adh = new DemandeAdhesionEvent();
            DemandeEvent event= demandeEventService.getDemandeEvent(id);
            adh.setStatus(0);
            adh.setEtudiant(this.getUser(request));
            
            adh.setClub(club);
           adh.setEvent(event);
            adh = demandeAdhesionEventService.save(adh);
            modelMap.addAttribute("add", 1);
         
          
        } else {
            modelMap.addAttribute("type", "danger");
            modelMap.addAttribute("msg", "Demande de plannification d'evenement non supprimée : Id non trouvé");
        }
        return this.showList(modelMap, request, page, size);

    }
 
    @GetMapping("/deleteDA")
    public String deleteDA(
            @RequestParam(name = "id") long id,
            HttpServletRequest request,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size,
            ModelMap modelMap) {
                if (demandeAdhesionEventService.existsByIds(id,this.getUser(request).getId())) {
                    DemandeAdhesionEvent d=  demandeAdhesionEventService.getByIds(id, this.getUser(request).getId());
                    //demandeEventService.deleteById(id);
                   
                    demandeAdhesionEventService.deleteById(d.getId());
                    Page<DemandeEvent> DE = demandeEventService.findApprouvedEvent(page, size);
                    modelMap.addAttribute("DE", DE);
                    modelMap.addAttribute("pages", new int[DE.getTotalPages()]);
                    modelMap.addAttribute("currentPage", page);
                    modelMap.addAttribute("del", 1);
                    
                }
                else {
                    Page<DemandeEvent> DE = demandeEventService.findApprouvedEvent(page, size);
                    modelMap.addAttribute("DE", DE);
                    modelMap.addAttribute("pages", new int[DE.getTotalPages()]);
                    modelMap.addAttribute("currentPage", page);
                    modelMap.addAttribute("introuvable", 1);
                }
        return "demandeAdhesion/list";
    }
    @RequestMapping("/listeDemande")
    public String showDemandesParCLub(ModelMap modelMap,
            HttpServletRequest request,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {
        
        Optional<Club> c =clubService.getClubParResponsable(this.getUser(request).getId());
        
        Page<DemandeAdhesionEvent> listEvent = demandeAdhesionEventService.findNonApprouvedDemandesParClub(c.get(),page, size);
        modelMap.addAttribute("DEMANDES", listEvent);
        modelMap.addAttribute("pages", new int[listEvent.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);

        return "ResponsedemandeAdhesionEvent/list";
    }
    @RequestMapping("/acceptAdh")
    public String acceptAdh(@RequestParam("id") Long id, ModelMap modelMap,
            @RequestParam(name = "page", defaultValue = "0") int page,
            HttpServletRequest request,
            @RequestParam(name = "size", defaultValue = "2") int size) {
        DemandeAdhesionEvent de = demandeAdhesionEventService.getDemandeAdhesionEvent(id);
        de.setStatus(1);
        //de.setAdmin(this.getUser(request));
        demandeAdhesionEventService.save(de);
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
        

     

        modelMap.addAttribute("DEMANDES", new DemandeAdhesionEvent());
        Optional<Club> c =clubService.getClubParResponsable(this.getUser(request).getId());
        
        //Page<DemandeAdhesionEvent> listEvent = demandeAdhesionEventService.findNonApprouvedDemandesParClub(c.get(),page, size);
   
        modelMap.addAttribute("pages",
                new int[demandeAdhesionEventService.findNonApprouvedDemandesParClub(c.get() ,page, size).getTotalPages()]);
        modelMap.addAttribute("type", "success");
        modelMap.addAttribute("msg", "Demande d'adhesion acceptée avec succès");
        return this.showDemandesParCLub(modelMap, request, page, size);
    }

    @RequestMapping("/declineAdh")
    public String declineAdh(@RequestParam("id") Long id, ModelMap modelMap,
            HttpServletRequest request,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {
        DemandeAdhesionEvent de = demandeAdhesionEventService.getDemandeAdhesionEvent(id);

       
        de.setStatus(2);
        demandeAdhesionEventService.save(de);
        Optional<Club> c =clubService.getClubParResponsable(this.getUser(request).getId());

        modelMap.addAttribute("Dcc", new DemandeAdhesionEvent());
        modelMap.addAttribute("pages",
                new int[demandeAdhesionEventService.findNonApprouvedDemandesParClub(c.get() ,page, size).getTotalPages()]);

        modelMap.addAttribute("type", "success");
        modelMap.addAttribute("msg", "Demande d'adhesion refusée !");
        return this.showDemandesParCLub(modelMap, request, page, size);
    }

    

   
}
