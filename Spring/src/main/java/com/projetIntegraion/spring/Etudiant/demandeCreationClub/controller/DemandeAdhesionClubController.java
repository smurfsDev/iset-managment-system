package com.projetIntegraion.spring.Etudiant.demandeCreationClub.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.DemandeAdhesionClub;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.Member;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.User;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.repository.UserRepository;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.service.DemandeAdhesionClubService;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.service.MemberService;
import com.projetIntegraion.spring.blogClub.entity.Club;
import com.projetIntegraion.spring.blogClub.service.ClubService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DemandeAdhesionClubController {

    @Autowired
    private DemandeAdhesionClubService demandeAdhesionClubService;

    @Autowired
    private ClubService clubService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MemberService memberService;

    public User getUser(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        User user = userRepository.findUserWithName(principal.getName()).get();
        return user;
    }

    @GetMapping("/demandeAdhesionClub")
    public String demandeAdhesionClub(ModelMap modelMap,
            HttpServletRequest request,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {

        Page<DemandeAdhesionClub> Dacs = demandeAdhesionClubService.getAllDemandeAdhesionClubParPageEtudiant(this.getUser(request).getId(), page, size);
        modelMap.addAttribute("Dacs", Dacs);
        modelMap.addAttribute("pages", new int[Dacs.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        return "Club/demandeAdhesionClub/list";

    }

    @GetMapping("/demandeAdhesionClubClub")
    public String demandeAdhesionClubClub(ModelMap modelMap,
            HttpServletRequest request,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {
        Club club = clubService.getClubParResponsable(this.getUser(request).getId()).get();

        Page<DemandeAdhesionClub> Dacs = demandeAdhesionClubService.getAllDemandeAdhesionClubParPageClub(club.getId(),
                page, size);

        modelMap.addAttribute("Dacs", Dacs);
        modelMap.addAttribute("pages", new int[Dacs.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        return "Club/demandeAdhesionClub/listDms";

    }

    @GetMapping("/joinClub")
    public String joinClub(
            ModelMap modelMap,
            HttpServletRequest request,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size,
            @RequestParam(name = "idClub") Long idClub) {
        Boolean c = demandeAdhesionClubService.existsByIds(idClub, this.getUser(request).getId());
        Boolean isThisClubOwner = clubService.getClubParResponsable(this.getUser(request).getId()).get().getId()==(idClub);
        if (c) {
            Page<DemandeAdhesionClub> Dacs = demandeAdhesionClubService.getAllDemandeAdhesionClubParPageEtudiant(this.getUser(request).getId(), page, size);
            modelMap.addAttribute("Dacs", Dacs);
            modelMap.addAttribute("pages", new int[Dacs.getTotalPages()]);
            modelMap.addAttribute("currentPage", 0);
            modelMap.addAttribute("exist", 1);
            return "Club/demandeAdhesionClub/list";
        }
        else if(isThisClubOwner){
            Page<DemandeAdhesionClub> Dacs = demandeAdhesionClubService.getAllDemandeAdhesionClubParPageEtudiant(this.getUser(request).getId(), page, size);
            modelMap.addAttribute("Dacs", Dacs);
            modelMap.addAttribute("pages", new int[Dacs.getTotalPages()]);
            modelMap.addAttribute("currentPage", 0);
            modelMap.addAttribute("clubOwner", 1);
            return "Club/demandeAdhesionClub/list";
        }
        modelMap.addAttribute("Dac", new DemandeAdhesionClub());
        modelMap.addAttribute("edit", false);
        modelMap.addAttribute("pages",
                new int[demandeAdhesionClubService.getAllDemandeAdhesionClubParPage(page, size).getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("idClub", idClub);
        modelMap.addAttribute("nomClub", clubService.getClub(idClub).getNomClub());
        return "Club/demandeAdhesionClub/form";
    }

    @PostMapping("/JoinClub")
    public String joinClub(
            @Valid DemandeAdhesionClub Dac,
            BindingResult bindingResult,
            HttpServletRequest request,
            @RequestParam(name = "nomClub", defaultValue = "") String nomClub,
            @RequestParam(name = "idClub", defaultValue = "") Long idClub,
            ModelMap modelMap) {

        if (bindingResult.hasErrors()) {
            modelMap.addAttribute("org.springframework.validation.BindingResult.Dac", bindingResult);
            modelMap.addAttribute("Dac", Dac);
            modelMap.addAttribute("idClub", idClub);
            modelMap.addAttribute("nomClub", nomClub);

            return "Club/demandeAdhesionClub/form";
        }
        Dac.setClub(clubService.getClub(idClub));
        Dac.setEtudiant(this.getUser(request));
        demandeAdhesionClubService.save(Dac);

        Page<DemandeAdhesionClub> Dacs = demandeAdhesionClubService.getAllDemandeAdhesionClubParPage(0, 2);
        modelMap.addAttribute("Dacs", Dacs);
        modelMap.addAttribute("pages", new int[Dacs.getTotalPages()]);
        modelMap.addAttribute("currentPage", 0);
        modelMap.addAttribute("add", 1);
        return "Club/demandeAdhesionClub/list";
    }

    @GetMapping("/deleteDac")
    public String deleteDac(
            @RequestParam(name = "id") long id,
            HttpServletRequest request,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size,
            ModelMap modelMap) {
        demandeAdhesionClubService.deleteById(id);
        Page<DemandeAdhesionClub> Dacs = demandeAdhesionClubService.getAllDemandeAdhesionClubParPageEtudiant(this.getUser(request).getId(), page, size);
        modelMap.addAttribute("Dacs", Dacs);
        modelMap.addAttribute("pages", new int[Dacs.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("del", 1);
        return "Club/demandeAdhesionClub/list";
    }

    @GetMapping("/editDac")
    public String editDac(
            @RequestParam(name = "id") long id,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size,
            ModelMap modelMap) {
        DemandeAdhesionClub Dac = demandeAdhesionClubService.getDemandeAdhesionClub(id);
        modelMap.addAttribute("Dac", Dac);
        modelMap.addAttribute("edit", true);
        modelMap.addAttribute("pages",
                new int[demandeAdhesionClubService.getAllDemandeAdhesionClubParPage(page, size).getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("nomClub", clubService.getClub(Dac.getClub().getId()).getNomClub());

        return "Club/demandeAdhesionClub/form";
    }

    @PostMapping("/editJoinClub")
    public String editJoinClub(
            @Valid DemandeAdhesionClub Dac,
            BindingResult bindingResult,
            HttpServletRequest request,
            @RequestParam(name = "nomClub", defaultValue = "") String nomClub,
            @RequestParam(name = "idClub", defaultValue = "") Long idClub,
            ModelMap modelMap) {
        if (bindingResult.hasErrors()) {
            modelMap.addAttribute("org.springframework.validation.BindingResult.Dac", bindingResult);
            modelMap.addAttribute("Dac", Dac);
            modelMap.addAttribute("idClub", idClub);
            modelMap.addAttribute("nomClub", nomClub);
            modelMap.addAttribute("edit", true);

            return "Club/demandeAdhesionClub/form";
        }
        DemandeAdhesionClub daac = demandeAdhesionClubService.getDemandeAdhesionClub(Dac.getId());
        daac.setId(Dac.getId());
        daac.setMessage(Dac.getMessage());
        demandeAdhesionClubService.save(daac);

        Page<DemandeAdhesionClub> Dacs = demandeAdhesionClubService.getAllDemandeAdhesionClubParPageEtudiant(this.getUser(request).getId(), 0, 2);
        modelMap.addAttribute("Dacs", Dacs);
        modelMap.addAttribute("pages", new int[Dacs.getTotalPages()]);
        modelMap.addAttribute("currentPage", 0);
        modelMap.addAttribute("edit", 1);
        return "Club/demandeAdhesionClub/list";
    }

    @GetMapping("/acceptDac")
    public String acceptDac(
            @RequestParam(name = "id") long id,
            HttpServletRequest request,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size,
            ModelMap modelMap) {
        DemandeAdhesionClub Dac = demandeAdhesionClubService.getDemandeAdhesionClub(id);
        Dac.setStatus(1);
        Member m = new Member();
        m.setClub(Dac.getClub());
        m.setEtudiant(Dac.getEtudiant());
        memberService.save(m);
        demandeAdhesionClubService.save(Dac);
        Principal principal = request.getUserPrincipal();
        User user = userRepository.findUserWithName(principal.getName()).get();
        Club club = clubService.getClubParResponsable(user.getId()).get();

        Page<DemandeAdhesionClub> Dacs = demandeAdhesionClubService.getAllDemandeAdhesionClubParPageClub(club.getId(),
                page, size);

        modelMap.addAttribute("Dacs", Dacs);
        modelMap.addAttribute("accept", 1);
        modelMap.addAttribute("pages", new int[Dacs.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        return "Club/demandeAdhesionClub/listDms";
    }

    @GetMapping("/declineDac")
    public String declineDac(
            @RequestParam(name = "id") long id,
            HttpServletRequest request,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size,
            ModelMap modelMap) {
        DemandeAdhesionClub Dac = demandeAdhesionClubService.getDemandeAdhesionClub(id);
        Dac.setStatus(2);
        demandeAdhesionClubService.save(Dac);
        Principal principal = request.getUserPrincipal();
        User user = userRepository.findUserWithName(principal.getName()).get();
        Club club = clubService.getClubParResponsable(user.getId()).get();

        Page<DemandeAdhesionClub> Dacs = demandeAdhesionClubService.getAllDemandeAdhesionClubParPageClub(club.getId(),
                page, size);

        modelMap.addAttribute("Dacs", Dacs);
        modelMap.addAttribute("decline", 1);
        modelMap.addAttribute("pages", new int[Dacs.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        return "Club/demandeAdhesionClub/listDms";
    }

}
