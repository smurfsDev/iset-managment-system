package com.projetIntegraion.spring.demandeMateriel.controller;

import java.util.List;

import com.projetIntegraion.spring.demandeMateriel.entity.CategorieMateriel;
import com.projetIntegraion.spring.demandeMateriel.service.CategorieMaterielService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CategorieMaterielControler {
    @Autowired  
    private CategorieMaterielService CategorieMaterielService;
    
    @RequestMapping("/categorieMateriel")
    public String getCategorieMateriel (ModelMap modelMap) {
        List<CategorieMateriel> listCategorieMateriel = CategorieMaterielService.getAllCategorie();
        modelMap.addAttribute("CM", listCategorieMateriel);
        return "demandeMateriel/form";
    }
    

}
