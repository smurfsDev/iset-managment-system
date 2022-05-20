package com.projetIntegraion.spring.Etudiant.demandeCreationClub.controller;

import com.projetIntegraion.spring.blogClub.entity.Club;
import com.projetIntegraion.spring.blogClub.service.ClubService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ClubController {

    @Autowired
    private ClubService clubService;

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
