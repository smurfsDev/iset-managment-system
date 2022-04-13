package com.projetIntegraion.spring.Etudiant.demandeCreationClub.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.tomcat.util.codec.binary.Base64;
import javax.servlet.http.HttpServletRequest;

import com.projetIntegraion.spring.FileUploadUtil;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.DemandeCreationClub;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.User;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.service.DemandeCreationClubService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

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

    @RequestMapping("/showCreateDcc")
    public String createDcc(ModelMap modelMap) {
        DemandeCreationClub dcc = new DemandeCreationClub();
        modelMap.addAttribute("Dcc", dcc);
        modelMap.addAttribute("dcc", new DemandeCreationClub());
        return "form";
    }

    @RequestMapping("/saveDcc")
    public String saveDcc(ModelMap modelMap,
    @RequestParam("image") MultipartFile multipartFile,
    @ModelAttribute("Dcc") DemandeCreationClub dcc,
            HttpServletRequest request) throws IOException {
        DemandeCreationClub dc = new DemandeCreationClub();
        dc.setNomClub(dcc.getNomClub());
        dc.setDateCreation(dcc.getDateCreation());
        dc.setActivite(dcc.getActivite());
        dc.setPresident(dcc.getPresident());
        dc.setVicePresident(dcc.getVicePresident());
        dc.setLogo(new String(Base64.encodeBase64(multipartFile.getBytes())));
        dc = DemandeCreationClubService.save(dc);

        return "redirect:/listeDcc";
    }

    @RequestMapping("/deleteDcc")
    public String deleteDcc(@RequestParam("id") Long id,ModelMap modelMap) {
        
        if(DemandeCreationClubService.deleteById(id)){
            modelMap.addAttribute("type","danger");
            modelMap.addAttribute("msg", "Demande de creation de club supprimée avec succès");
        }else{
            modelMap.addAttribute("type","danger");
            modelMap.addAttribute("msg", "Demande de creation de club non supprimée : Id non trouvé");
        }
        return this.showList(modelMap, 0, 2);
        
    }
}
