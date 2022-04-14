package com.projetIntegraion.spring.Etudiant.demandeCreationClub.controller;

import java.io.IOException;
import org.apache.tomcat.util.codec.binary.Base64;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.DemandeCreationClub;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.service.DemandeCreationClubService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class DemandeCreationClubController {
    @Autowired
    private DemandeCreationClubService DemandeCreationClubService;

    @RequestMapping("/listeDcc")
    public String showList(ModelMap modelMap,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {
        Page<DemandeCreationClub> listDcc = DemandeCreationClubService.getAllDemandeCreationClubParPage(page, size);
        modelMap.addAttribute("Dccs", listDcc);
        modelMap.addAttribute("pages", new int[listDcc.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);

        return "list";
    }

    @RequestMapping("/showCreateDcc")
    public String createDcc(ModelMap modelMap,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {
        modelMap.addAttribute("Dcc", new DemandeCreationClub());
        modelMap.addAttribute("edit", false);
        modelMap.addAttribute("pages",
                new int[DemandeCreationClubService.getAllDemandeCreationClubParPage(page, size).getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        return "form";
    }

    @RequestMapping("/saveDcc")
    public String saveDcc(ModelMap modelMap,
            @RequestParam("image") MultipartFile multipartFile,
            @Valid DemandeCreationClub Dcc,
            BindingResult bindingResult,
            HttpServletRequest request,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) throws IOException {
        if (bindingResult.hasErrors()) {
            modelMap.addAttribute("org.springframework.validation.BindingResult.Dcc", bindingResult);
            modelMap.addAttribute("Dcc", Dcc);
            return "form";
        } else {
            DemandeCreationClub dc = new DemandeCreationClub();
            dc.setNomClub(Dcc.getNomClub());
            dc.setDateCreation(Dcc.getDateCreation());
            dc.setActivite(Dcc.getActivite());
            dc.setPresident(Dcc.getPresident());
            dc.setVicePresident(Dcc.getVicePresident());
            dc.setLogo(new String(Base64.encodeBase64(multipartFile.getBytes())));
            dc = DemandeCreationClubService.save(dc);
            modelMap.addAttribute("Dcc", new DemandeCreationClub());
            modelMap.addAttribute("msg", "Demande de création de club enregistrée avec succès");
            modelMap.addAttribute("type", "success");
            modelMap.addAttribute("pages",
                    new int[DemandeCreationClubService.getAllDemandeCreationClubParPage(page, size).getTotalPages()]);
            return this.showList(modelMap, page, size);
        }
    }

    @RequestMapping("/deleteDcc")
    public String deleteDcc(@RequestParam("id") Long id, ModelMap modelMap,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {

        if (DemandeCreationClubService.deleteById(id)) {
            modelMap.addAttribute("type", "danger");
            modelMap.addAttribute("msg", "Demande de creation de club supprimée avec succès");
        } else {
            modelMap.addAttribute("type", "danger");
            modelMap.addAttribute("msg", "Demande de creation de club non supprimée : Id non trouvé");
        }
        return this.showList(modelMap, page, size);

    }

    @RequestMapping("/modifierDcc")
    public String showUpdateDcc(@RequestParam("id") Long id, ModelMap modelMap,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {
        DemandeCreationClub Dcc = DemandeCreationClubService.getDemandeCreationClub(id);
        modelMap.addAttribute("Dcc", Dcc);
        modelMap.addAttribute("edit", true);
        modelMap.addAttribute("pages",
                new int[DemandeCreationClubService.getAllDemandeCreationClubParPage(page, size).getTotalPages()]);
        return "form";
    }

    @RequestMapping("/updateDcc")
    public String updateDcc(ModelMap modelMap,
            @RequestParam(name = "image") MultipartFile multipartFile,
            @Valid DemandeCreationClub Dcc,
            BindingResult bindingResult,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size,
            HttpServletRequest request) throws IOException {
        if (bindingResult.hasErrors()) {
            modelMap.addAttribute("org.springframework.validation.BindingResult.Dcc", bindingResult);
            modelMap.addAttribute("Dcc", Dcc);
            modelMap.addAttribute("edit", true);
            return "form";
        } else {
            DemandeCreationClub dc = new DemandeCreationClub();
            dc.setId(Dcc.getId());
            dc.setNomClub(Dcc.getNomClub());
            dc.setDateCreation(Dcc.getDateCreation());
            dc.setActivite(Dcc.getActivite());
            dc.setPresident(Dcc.getPresident());
            dc.setVicePresident(Dcc.getVicePresident());
            if (multipartFile.getSize() != 0) {
                dc.setLogo(new String(Base64.encodeBase64(multipartFile.getBytes())));
            } else {
                dc.setLogo(DemandeCreationClubService.getDemandeCreationClub(Dcc.getId()).getLogo());
            }
            dc = DemandeCreationClubService.save(dc);
            modelMap.addAttribute("Dcc", new DemandeCreationClub());
            modelMap.addAttribute("pages",
                    new int[DemandeCreationClubService.getAllDemandeCreationClubParPage(page, size).getTotalPages()]);
            modelMap.addAttribute("type", "warning");
            modelMap.addAttribute("msg", "Demande de creation de club modifiée avec succès");
            return this.showList(modelMap, page, size);
        }
    }

    @RequestMapping("/accept")
    public String acceptDcc(@RequestParam("id") Long id, ModelMap modelMap,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {
        DemandeCreationClub dc = DemandeCreationClubService.getDemandeCreationClub(id);
        dc.setStatus(1);
        DemandeCreationClubService.save(dc);
        modelMap.addAttribute("Dcc", new DemandeCreationClub());
        modelMap.addAttribute("pages",
                new int[DemandeCreationClubService.getAllDemandeCreationClubParPage(page, size).getTotalPages()]);
        modelMap.addAttribute("type", "success");
        modelMap.addAttribute("msg", "Demande de creation de club acceptée avec succès");
        return this.showList(modelMap, page, size);
    }

}
