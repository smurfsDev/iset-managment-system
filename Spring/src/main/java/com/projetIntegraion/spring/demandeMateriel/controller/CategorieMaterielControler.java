package com.projetIntegraion.spring.demandeMateriel.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.projetIntegraion.spring.demandeMateriel.entity.CategorieMateriel;
import com.projetIntegraion.spring.demandeMateriel.entity.Materiel;
import com.projetIntegraion.spring.demandeMateriel.repository.MaterielRepository;
import com.projetIntegraion.spring.demandeMateriel.service.CategorieMaterielService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CategorieMaterielControler {
    @Autowired
    private CategorieMaterielService CategorieMaterielService;

    @Autowired 
    private MaterielRepository MaterielRepository;

    @RequestMapping("/categorieMateriel")
    public String getCategorieMateriel(ModelMap modelMap) {
        List<CategorieMateriel> listCategorieMateriel = CategorieMaterielService.getAllCategorie();
        modelMap.addAttribute("CM", listCategorieMateriel);
        return "demandeMateriel/form";
    }

    @RequestMapping("/showListCategories")
    public String showListMateriel(ModelMap modelMap,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {
        Page<CategorieMateriel> listCategorie = CategorieMaterielService.getAllCategorieParPage(page, size);
        modelMap.addAttribute("listCategorie", listCategorie);
        modelMap.addAttribute("pages", new int[listCategorie.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);

        return "/demandeMateriel/categorieMateriel/list";
    }

    @RequestMapping("/showCreateCategorieForm")
    public String showCretateCategorieForm(ModelMap modelMap) {
        modelMap.addAttribute("categorieMateriel", new CategorieMateriel());
        modelMap.addAttribute("edit", false);

        List<CategorieMateriel> listCategorieMateriel = CategorieMaterielService.getAllCategorie();
        System.out.println(listCategorieMateriel);
        modelMap.addAttribute("CM", listCategorieMateriel);
        return "/demandeMateriel/categorieMateriel/form";
    }

    @RequestMapping("/saveCategorieMateriel")
    public String saveMateriel(ModelMap modelMap,
            @Valid CategorieMateriel categorieMateriel,
            BindingResult bindingResult,
            HttpServletRequest request,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) throws IOException {
        if (bindingResult.hasErrors()) {
            modelMap.addAttribute("org.springframework.validation.BindingResult.categorieMateriel", bindingResult);
            modelMap.addAttribute("categorieMateriel", categorieMateriel);
            return "/demandeMateriel/categorieMateriel/form";
        } else {
            CategorieMateriel m = new CategorieMateriel();
            m.setTitre(categorieMateriel.getTitre());
            m = CategorieMaterielService.save(m);
            modelMap.addAttribute("categorieMateriel", new CategorieMateriel());
            modelMap.addAttribute("msg", "Categorie materiel enregistrée avec succès");
            modelMap.addAttribute("type", "success");
            modelMap.addAttribute("pages",
                    new int[CategorieMaterielService.getAllCategorieParPage(page, size).getTotalPages()]);
            return this.showListMateriel(modelMap, page, size);
        }
    }

    @RequestMapping("/showEditCategorieForm")
    public String showEditCategorieForm(ModelMap modelMap,
            @RequestParam(name = "id") Long id,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {
        CategorieMateriel categorieMateriel = CategorieMaterielService.getCategorieById(id);
        modelMap.addAttribute("categorieMateriel", categorieMateriel);
        modelMap.addAttribute("edit", true);
        modelMap.addAttribute("pages",
                new int[CategorieMaterielService.getAllCategorieParPage(page, size).getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        return "/demandeMateriel/categorieMateriel/form";
    }

    @RequestMapping("/updateCategorieMateriel")
    public String updateCategorieMateriel(ModelMap modelMap,
            @Valid CategorieMateriel categorieMateriel,
            BindingResult bindingResult,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {
        if (bindingResult.hasErrors()) {
            modelMap.addAttribute("org.springframework.validation.BindingResult.categorieMateriel", bindingResult);
            modelMap.addAttribute("categorieMateriel", categorieMateriel);
            return "/demandeMateriel/categorieMateriel/form";
        } else {
            CategorieMateriel m = CategorieMaterielService.getCategorieById(categorieMateriel.getId());
            m.setTitre(categorieMateriel.getTitre());
            m = CategorieMaterielService.save(m);
            modelMap.addAttribute("categorieMateriel", new CategorieMateriel());
            modelMap.addAttribute("msg", "Categorie materiel modifiée avec succès");
            modelMap.addAttribute("type", "success");
            modelMap.addAttribute("pages",
                    new int[CategorieMaterielService.getAllCategorieParPage(page, size).getTotalPages()]);
            return this.showListMateriel(modelMap, page, size);
        }
    }

    @RequestMapping("/deleteCategorieMateriel")
    public String deleteCategorieMateriel(ModelMap modelMap,
            @RequestParam(name = "id") Long id,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {
        CategorieMateriel categorieMateriel = CategorieMaterielService.getCategorieById(id);
        List<Materiel> lst = MaterielRepository.findByCategorieId(categorieMateriel.getId());
        if (lst.size()==0){
            CategorieMaterielService.delete(categorieMateriel);
            modelMap.addAttribute("msg", "Categorie materiel supprimée avec succès");
            modelMap.addAttribute("type", "success");
        }else{
            modelMap.addAttribute("msg", "Cette categorie est utilisée par un ou plusieurs materiels");
            modelMap.addAttribute("type", "danger");
        }
        modelMap.addAttribute("categorieMateriel", new CategorieMateriel());
        modelMap.addAttribute("pages",
                new int[CategorieMaterielService.getAllCategorieParPage(page, size).getTotalPages()]);
        return this.showListMateriel(modelMap, page, size);
    }

}
