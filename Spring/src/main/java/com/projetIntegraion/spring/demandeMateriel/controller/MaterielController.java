package com.projetIntegraion.spring.demandeMateriel.controller;

import java.util.List;

import com.projetIntegraion.spring.demandeMateriel.entity.Materiel;
import com.projetIntegraion.spring.demandeMateriel.service.MaterielService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MaterielController {
    @Autowired MaterielService MaterielService;
    @RequestMapping("/materiel")
    public String getMateriel (ModelMap modelMap) {
        List<Materiel> listMateriel = MaterielService.getAllMateriel();
        modelMap.addAttribute("Materiels", listMateriel);
        return "materiel";
    } 
    @RequestMapping("/showMateriel")
    public String getMaterielParCategorie (ModelMap modelMap, Long idCategorie) {
        List<Materiel> listMateriel = MaterielService.getAllMaterielByIdCategorie(idCategorie);
        modelMap.addAttribute("M", listMateriel);
        modelMap.addAttribute("edit", false);

        return "/demandeMateriel/MaterielForm";
    }
}
