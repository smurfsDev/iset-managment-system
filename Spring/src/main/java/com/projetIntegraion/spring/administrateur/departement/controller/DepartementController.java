package com.projetIntegraion.spring.administrateur.departement.controller;

import java.util.List;

import com.projetIntegraion.spring.administrateur.departement.entity.Departement;
import com.projetIntegraion.spring.administrateur.departement.service.DepartementService;
import com.projetIntegraion.spring.demandeSalle.entity.Salle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DepartementController {
    @Autowired
    private DepartementService departementService; 
    @RequestMapping("/departement")
    public String getDepartement (ModelMap modelMap) {
        List<Departement> listDepartement = departementService.getAllDepartement();
        modelMap.addAttribute("departement", listDepartement);
        return "administrateur/departement/form";
    }
    @RequestMapping("/showSalles")
    public String showSalles(ModelMap modelMap, Long idDepartement) {
        List<Salle> salles = departementService.getAllSalleByDepartement(idDepartement);
        modelMap.addAttribute("sallesdep", salles);
        return "demandeSalle/list";
    }

}
