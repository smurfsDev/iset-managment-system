package com.projetIntegraion.spring.demandeSalle.controller;

import com.projetIntegraion.spring.demandeSalle.entity.Salle;
import com.projetIntegraion.spring.demandeSalle.service.SalleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SalleController {
    @Autowired SalleService salleService;
    public String getSalle (ModelMap modelMap,
	@RequestParam(name = "page", defaultValue = "0") int page,
	@RequestParam(name = "size", defaultValue = "2") int size) {
        Page<Salle> listSalle = salleService.getAllSalleParPage(page, size);
        modelMap.addAttribute("salle", listSalle);
        modelMap.addAttribute("pages", new int[listSalle.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        return "demandeSalle/form";
    }
    public String getSalleById (ModelMap modelMap, Long id) {
        Salle salle = salleService.getSalleById(id);
        modelMap.addAttribute("salle", salle);
        return "demandeSalle/form";
    }
    
     
        

    

}
