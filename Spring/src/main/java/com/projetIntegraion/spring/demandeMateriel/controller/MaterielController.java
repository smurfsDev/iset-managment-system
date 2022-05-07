package com.projetIntegraion.spring.demandeMateriel.controller;

import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;
import java.util.List;

import com.projetIntegraion.spring.demandeMateriel.entity.DemandeMateriel;
import com.projetIntegraion.spring.demandeMateriel.entity.DemandeMaterielMateriel;
import com.projetIntegraion.spring.demandeMateriel.entity.Materiel;
import com.projetIntegraion.spring.demandeMateriel.repository.DemandeMaterielMaterielRepository;
import com.projetIntegraion.spring.demandeMateriel.service.DemandeMaterielService;
import com.projetIntegraion.spring.demandeMateriel.service.MaterielService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MaterielController {
    @Autowired 
    MaterielService MaterielService;

    @Autowired
    DemandeMaterielService DemandeMaterielService;

    @Autowired
    DemandeMaterielMaterielRepository DemandeMaterielMaterielRepository;

    @RequestMapping("/materiel")
    public String getMateriel (ModelMap modelMap) {
        List<Materiel> listMateriel = MaterielService.getAllMateriel();
        modelMap.addAttribute("Materiels", listMateriel);
        return "materiel";
    } 
    @RequestMapping("/showMateriel")
    public String getMaterielParCategorie (ModelMap modelMap,
    @RequestParam(name = "id", defaultValue = "0") Long idCategorie,
    @RequestParam(name = "idDemande", defaultValue = "0") Long idDemande
    ) {
        List<Materiel> listMateriel = MaterielService.getAllMaterielByIdCategorie(idCategorie);
        modelMap.addAttribute("Materiels", listMateriel);
        modelMap.addAttribute("edit", false);
        return "/demandeMateriel/MaterielForm";
    }

    @RequestMapping("/saveMateriel")
    public String saveMateriel(ModelMap modelMap, Long idMateriel, Long idDemande){

        DemandeMaterielMateriel materiel = new DemandeMaterielMateriel();
        Materiel Mat = MaterielService.getMaterielById(idMateriel);
        DemandeMateriel dm =  DemandeMaterielService.getdemandeById(idDemande);
        materiel.setMateriel(Mat);
        materiel.setDemandeMateriel(dm);
        DemandeMaterielMaterielRepository.save(materiel);
        return this.getMaterielParCategorie(modelMap,idMateriel,idDemande);
    }


}
