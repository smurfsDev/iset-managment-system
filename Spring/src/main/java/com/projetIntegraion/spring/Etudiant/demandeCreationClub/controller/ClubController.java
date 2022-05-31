package com.projetIntegraion.spring.Etudiant.demandeCreationClub.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.Member;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.User;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.repository.MemberRepository;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.repository.UserRepository;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.service.MemberService;
import com.projetIntegraion.spring.blogClub.entity.Club;
import com.projetIntegraion.spring.blogClub.repository.ClubRepository;
import com.projetIntegraion.spring.blogClub.service.ClubService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ClubController {

    @Autowired
    private ClubService clubService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClubRepository clubRepository;

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MemberService memberService;

    public User getUser(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        User user = userRepository.findUserWithName(principal.getName()).get();
        return user;
    }

    @GetMapping(value = "/mesMembres")
    public String getAllClub(ModelMap modelMap,
            HttpServletRequest request,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {

        User user = this.getUser(request);
        Club club = clubRepository.findByResponsableClub(user).get();
        Page<Member> listmember = memberRepository.findByClub(club, PageRequest.of(page, size));
        modelMap.addAttribute("listmember", listmember);
        modelMap.addAttribute("pages", new int[listmember.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);

        return "Club/listmember";

    }

    @GetMapping(value = "/supprimerMembre")
    public String supprimerMembre(ModelMap modelMap, long id,
            HttpServletRequest request,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {
        if (memberService.deleteById(id)) {
            modelMap.addAttribute("type", "danger");
            modelMap.addAttribute("msg", "Member est supprimée avec succès");
        } else {
            modelMap.addAttribute("type", "danger");
            modelMap.addAttribute("msg", "Member non supprimée : Id non trouvé");
        }
        modelMap.addAttribute("msg", "Membre supprimée avec succès");
        User user = this.getUser(request);
        Club club = clubRepository.findByResponsableClub(user).get();

        Page<Member> listmember = memberRepository.findByClub(club, PageRequest.of(page, size));
        modelMap.addAttribute("listmember", listmember);
        modelMap.addAttribute("pages", new int[listmember.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);

        return "Club/listmember";

    }

    @GetMapping("/listeClub")
    public String showList(ModelMap modelMap,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {
        Page<Club> listClub = clubService.getAllClubParPage(page, size);
        modelMap.addAttribute("Clubs", listClub);
        modelMap.addAttribute("pages", new int[listClub.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);

        return "Club/list";
    }

}
