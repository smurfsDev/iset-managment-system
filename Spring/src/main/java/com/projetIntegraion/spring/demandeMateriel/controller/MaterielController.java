package com.projetIntegraion.spring.demandeMateriel.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.projetIntegraion.spring.demandeMateriel.entity.CategorieMateriel;
import java.security.Principal;
import java.util.List;

import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.User;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.repository.UserRepository;
import com.projetIntegraion.spring.demandeMateriel.entity.DemandeMateriel;
import com.projetIntegraion.spring.demandeMateriel.entity.DemandeMaterielMateriel;
import com.projetIntegraion.spring.demandeMateriel.entity.Materiel;
import com.projetIntegraion.spring.demandeMateriel.repository.DemandeMaterielMaterielRepository;
import com.projetIntegraion.spring.demandeMateriel.service.CategorieMaterielService;
import com.projetIntegraion.spring.demandeMateriel.service.DemandeMaterielService;
import com.projetIntegraion.spring.demandeMateriel.service.MaterielService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
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
    CategorieMaterielService CategorieMaterielService;

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

    @RequestMapping("/showListMateriel")
    public String showListMateriel(ModelMap modelMap,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {
        Page<Materiel> listMateriel = MaterielService.getAllMaterielParPage(page, size);
        modelMap.addAttribute("listMateriel", listMateriel);
        modelMap.addAttribute("pages", new int[listMateriel.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);

        return "/demandeMateriel/materiel/list";
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

    @RequestMapping("/saveMaterielDemande")
    public String saveMateriel(ModelMap modelMap, Long idMateriel, Long idDemande, HttpServletRequest request,
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

    @RequestMapping("/deleteMaterielDemande")
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

    @RequestMapping("/getMaterielofDemande")
    // getMaterielofDemande
    public String getMaterielofDemande(ModelMap modelMap, HttpServletRequest request, Long idDemande,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {
        Page<DemandeMateriel> listDm = DemandeMaterielService
                .getAllDemandeSalleParPageParResponsableClub(this.getUser(request), page, size);
        modelMap.addAttribute("Dmms", listDm);
        modelMap.addAttribute("pages", new int[listDm.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        DemandeMateriel dm = DemandeMaterielService.getdemandeById(idDemande);
        List<DemandeMaterielMateriel> listDmMateriel = DemandeMaterielMaterielRepository
                .findByDemandeMaterielId(idDemande);
        modelMap.addAttribute("listDmMateriel", listDmMateriel);
        modelMap.addAttribute("dm", dm);
        return "/demandeMateriel/MesDemandes";
    }

    @RequestMapping("/showCretateMaterielForm")
    public String showCretateMaterielForm(ModelMap modelMap) {
        modelMap.addAttribute("materiel", new Materiel());
        modelMap.addAttribute("edit", false);

        List<CategorieMateriel> listCategorieMateriel = CategorieMaterielService.getAllCategorie();
        System.out.println(listCategorieMateriel);
        modelMap.addAttribute("CM", listCategorieMateriel);
        return "/demandeMateriel/materiel/form";
    }

    @RequestMapping("/saveMateriel")
    public String saveMateriel(ModelMap modelMap,
            @Valid Materiel materiel,
            BindingResult bindingResult,
            HttpServletRequest request,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) throws IOException {
        if (bindingResult.hasErrors()) {
            modelMap.addAttribute("org.springframework.validation.BindingResult.materiel", bindingResult);
            modelMap.addAttribute("materiel", materiel);
            List<CategorieMateriel> listCategorieMateriel = CategorieMaterielService.getAllCategorie();

            System.out.println(listCategorieMateriel);
            modelMap.addAttribute("CM", listCategorieMateriel);
            return "/demandeMateriel/materiel/form";
        } else {
            Materiel m = new Materiel();
            m.setTitre(materiel.getTitre());
            m.setDescription(materiel.getDescription());
            m.setQuantite(materiel.getQuantite());
            m.setCategorie(materiel.getCategorie());
            m.setResponsable(this.getUser(request));
            m = MaterielService.save(m);
            modelMap.addAttribute("materiel", new DemandeMateriel());
            modelMap.addAttribute("msg", "Demande de materiel enregistrée avec succès");
            modelMap.addAttribute("type", "success");
            modelMap.addAttribute("pages",
                    new int[MaterielService.getAllMaterielParPage(page, size).getTotalPages()]);
            return this.showListMateriel(modelMap, page, size);
        }
    }

    @RequestMapping("/showEditMaterielForm")
    public String showEditMaterielForm(ModelMap modelMap, Long id) {
        modelMap.addAttribute("materiel", MaterielService.getMaterielById(id));
        modelMap.addAttribute("edit", true);
        List<CategorieMateriel> listCategorieMateriel = CategorieMaterielService.getAllCategorie();
        System.out.println(listCategorieMateriel);
        modelMap.addAttribute("CM", listCategorieMateriel);
        return "/demandeMateriel/materiel/form";
    }

    @RequestMapping("/updateMateriel")
    public String updateMateriel(ModelMap modelMap,
            @Valid Materiel materiel,
            BindingResult bindingResult,
            HttpServletRequest request,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) throws IOException {
        if (bindingResult.hasErrors()) {
            modelMap.addAttribute("org.springframework.validation.BindingResult.materiel", bindingResult);
            modelMap.addAttribute("materiel", materiel);
            List<CategorieMateriel> listCategorieMateriel = CategorieMaterielService.getAllCategorie();

            System.out.println(listCategorieMateriel);
            modelMap.addAttribute("CM", listCategorieMateriel);
            return "/demandeMateriel/materiel/form";
        } else {
            Materiel m = MaterielService.getMaterielById(materiel.getId());
            m.setTitre(materiel.getTitre());
            m.setDescription(materiel.getDescription());
            m.setQuantite(materiel.getQuantite());
            m.setCategorie(materiel.getCategorie());
            m.setResponsable(this.getUser(request));

            m = MaterielService.save(m);
            modelMap.addAttribute("materiel", new DemandeMateriel());
            modelMap.addAttribute("msg", "Demande de materiel enregistrée avec succès");
            modelMap.addAttribute("type", "success");
            modelMap.addAttribute("pages",
                    new int[MaterielService.getAllMaterielParPage(page, size).getTotalPages()]);
            return this.showListMateriel(modelMap, page, size);
        }
    }

    @RequestMapping("/deleteMateriel")
    public String deleteMateriel(ModelMap modelMap, Long id,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {
        MaterielService.deleteById(id);
        modelMap.addAttribute("materiel", new DemandeMateriel());
        modelMap.addAttribute("msg", "Demande de materiel supprimée avec succès");
        modelMap.addAttribute("type", "success");
        modelMap.addAttribute("pages",
                new int[MaterielService.getAllMaterielParPage(page, size).getTotalPages()]);
        return this.showListMateriel(modelMap, page, size);
    }

}
