package com.projetIntegraion.spring.demandeMateriel.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.User;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.repository.UserRepository;
import com.projetIntegraion.spring.demandeMateriel.entity.DemandeMateriel;
import com.projetIntegraion.spring.demandeMateriel.entity.DemandeMaterielMateriel;
import com.projetIntegraion.spring.demandeMateriel.entity.Materiel;
import com.projetIntegraion.spring.demandeMateriel.repository.DemandeMaterielMaterielRepository;
import com.projetIntegraion.spring.demandeMateriel.service.DemandeMaterielService;
import com.projetIntegraion.spring.demandeMateriel.service.MaterielService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @Autowired
    private UserRepository userRepository;

    public User getUser(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        User user = userRepository.findUserWithName(principal.getName()).get();
        return user;
    }

    @RequestMapping("/materiel")
    public String getMateriel(ModelMap modelMap) {
        List<Materiel> listMateriel = MaterielService.getAllMateriel();
        modelMap.addAttribute("Materiels", listMateriel);
        return "materiel";
    }

    @RequestMapping("/showMateriel")
    public String getMaterielParCategorie(ModelMap modelMap,
            @RequestParam(name = "id", defaultValue = "0") Long idCategorie,
            @RequestParam(name = "idDemande", defaultValue = "0") Long idDemande) {
        List<Materiel> listMateriel = MaterielService.getAllMaterielByIdCategorie(idCategorie);
        modelMap.addAttribute("Materiels", listMateriel);
        modelMap.addAttribute("edit", false);
        return "/demandeMateriel/MaterielForm";
    }

    @RequestMapping("/saveMateriel")
    public String saveMateriel(ModelMap modelMap, HttpServletRequest request, Long idMateriel, Long idDemande,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {
        DemandeMaterielMateriel materiel = new DemandeMaterielMateriel();
        Materiel Mat = MaterielService.getMaterielById(idMateriel);
        DemandeMateriel dm = DemandeMaterielService.getdemandeById(idDemande);
        materiel.setMateriel(Mat);
        materiel.setDemandeMateriel(dm);
        if (DemandeMaterielMaterielRepository.findByDemandeMaterielIdAndMaterielId(idDemande, idMateriel) == null) {
            Page<DemandeMateriel> listDm = DemandeMaterielService
                    .getAllDemandeSalleParPageParResponsableClub(this.getUser(request), page, size);
            modelMap.addAttribute("Dmms", listDm);
            modelMap.addAttribute("pages", new int[listDm.getTotalPages()]);
            modelMap.addAttribute("currentPage", page);
            DemandeMaterielMaterielRepository.save(materiel);
            return "/demandeMateriel/list";
        } else {
            return this.getMaterielParCategorie(modelMap, idMateriel, idDemande);
        }
    }

    @RequestMapping("/deleteMateriel")
    public String deleteMateriel(ModelMap modelMap, HttpServletRequest request, Long idMateriel, Long idDemande,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {
        Page<DemandeMateriel> listDm = DemandeMaterielService
                .getAllDemandeSalleParPageParResponsableClub(this.getUser(request), page, size);
        modelMap.addAttribute("Dmms", listDm);
        modelMap.addAttribute("pages", new int[listDm.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        DemandeMaterielMaterielRepository.deleteById(idMateriel);
        return "/demandeMateriel/list";
    }

    @RequestMapping("/setQuantite")
    public String setQuantite(ModelMap modelMap, HttpServletRequest request, Long idMateriel, Long idDemande,
            int quantite,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {
        Page<DemandeMateriel> listDm = DemandeMaterielService
                .getAllDemandeSalleParPageParResponsableClub(this.getUser(request), page, size);
        modelMap.addAttribute("Dmms", listDm);
        modelMap.addAttribute("pages", new int[listDm.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        // DemandeMaterielMateriel materiel =
        // DemandeMaterielMaterielRepository.findByMaterielId(idMateriel);
        DemandeMaterielMateriel Dm = DemandeMaterielMaterielRepository.findByDemandeMaterielIdAndMaterielId(idDemande,
                idMateriel);
        if (Dm != null) {
            Dm.setQuantite(quantite);
            DemandeMaterielMaterielRepository.save(Dm);
            return "/demandeMateriel/list";
        }
        return null;

    }
}
