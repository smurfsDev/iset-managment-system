package com.projetIntegraion.spring.demandePlannificationEvent.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.User;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.repository.RoleRepository;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.repository.UserRepository;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.service.DemandeAdhesionClubService;
import com.projetIntegraion.spring.blogClub.service.ClubService;
import com.projetIntegraion.spring.demandePlannificationEvent.entity.DemandeAdhesionEvent;
import com.projetIntegraion.spring.demandePlannificationEvent.entity.DemandeEvent;
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

    @GetMapping("/joinEvent")
    public String joinEvent(
            ModelMap modelMap,
            HttpServletRequest request,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size,
            @RequestParam(name = "idClub") Long idClub) {
                modelMap.addAttribute("DAE", new DemandeAdhesionEvent());
                modelMap.addAttribute("edit", false);
               
                modelMap.addAttribute("currentPage", page);
                modelMap.addAttribute("idClub", idClub);
                modelMap.addAttribute("nomClub", clubService.getClub(idClub).getNomClub());
                return "Club/demandeAdhesionClub/form";
               
            }
}
