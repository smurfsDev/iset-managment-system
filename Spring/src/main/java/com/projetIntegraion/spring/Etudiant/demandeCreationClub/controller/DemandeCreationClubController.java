package com.projetIntegraion.spring.Etudiant.demandeCreationClub.controller;

import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.DemandeCreationClub;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.service.DemandeCreationClubService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DemandeCreationClubController {
    @Autowired
    private DemandeCreationClubService DemandeCreationClubService;




    @RequestMapping("/listeDcc")
    public String showList(ModelMap modelMap,
        @RequestParam(name = "page", defaultValue = "0") int page,
        @RequestParam(name = "size", defaultValue = "2") int size) {
        Page<DemandeCreationClub> listDcc = DemandeCreationClubService.getAllDemandeCreationClubParPage(page, size);
        modelMap.addAttribute("dccs", listDcc);
        modelMap.addAttribute("pages", new int[listDcc.getTotalPages()]);
		modelMap.addAttribute("currentPage", page);

        return "list";
    }


}
