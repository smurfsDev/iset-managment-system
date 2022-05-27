package com.projetIntegraion.spring.demandePlannificationEvent.controller;

import java.security.Principal;

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

            adh.setStatus(0);
            adh.setEtudiant(this.getUser(request));
            
            adh.setClub(club);
           
            adh = demandeAdhesionEventService.save(adh);
         
          
        } else {
            modelMap.addAttribute("type", "danger");
            modelMap.addAttribute("msg", "Demande de plannification d'evenement non supprimée : Id non trouvé");
        }
        return this.showList(modelMap, request, page, size);

    }

    // @GetMapping("/joinEvent")
    // public String joinEvent(
    //         ModelMap modelMap,
    //         HttpServletRequest request,
    //         @RequestParam(name = "page", defaultValue = "0") int page,
    //         @RequestParam(name = "size", defaultValue = "2") int size,
    //         @RequestParam(name = "idClub") Long idClub) {
    //             Boolean c = demandeAdhesionEventService.existsByIds(idClub, this.getUser(request).getId());
    //             Boolean isThisClubOwner = clubService.isThisClubOwner(idClub, this.getUser(request));
    //             if (c) {
    //                 Page<DemandeAdhesionEvent> Dacs = demandeAdhesionEventService.getAllDemandeAdhesionEventParPageEtudiant(this.getUser(request).getId(), page, size);
    //                 modelMap.addAttribute("Dacs", Dacs);
    //                 modelMap.addAttribute("pages", new int[Dacs.getTotalPages()]);
    //                 modelMap.addAttribute("currentPage", 0);
    //                 modelMap.addAttribute("exist", 1);
    //                 return "demandeAdhesion/list";
    //             }
    //             else if(isThisClubOwner){
    //                 Page<DemandeAdhesionEvent> Dacs = demandeAdhesionEventService.getAllDemandeAdhesionEventParPageEtudiant(this.getUser(request).getId(), page, size);
    //                 modelMap.addAttribute("Dacs", Dacs);
    //                 modelMap.addAttribute("pages", new int[Dacs.getTotalPages()]);
    //                 modelMap.addAttribute("currentPage", 0);
    //                 modelMap.addAttribute("clubOwner", 1);
    //                 return "demandeAdhesion/list";
    //             }
    //             modelMap.addAttribute("Dac", new DemandeAdhesionEvent());
    //             modelMap.addAttribute("edit", false);
    //             modelMap.addAttribute("pages",
    //                     new int[demandeAdhesionEventService.getAllDemandeAdhesionEventParPage(page, size).getTotalPages()]);
    //             modelMap.addAttribute("currentPage", page);
    //             modelMap.addAttribute("idClub", idClub);
    //             modelMap.addAttribute("nomClub", clubService.getClub(idClub).getNomClub());
    //             return "demandeAdhesion/form";
            
               
    //         }
}
