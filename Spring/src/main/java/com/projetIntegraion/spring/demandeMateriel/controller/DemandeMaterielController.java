package com.projetIntegraion.spring.demandeMateriel.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.User;
import com.projetIntegraion.spring.demandeMateriel.entity.CategorieMateriel;
import com.projetIntegraion.spring.demandeMateriel.entity.DemandeMateriel;
import com.projetIntegraion.spring.demandeMateriel.service.CategorieMaterielService;
import com.projetIntegraion.spring.demandeMateriel.service.DemandeMaterielService;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.service.UserServise;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DemandeMaterielController {

	@Autowired
    private DemandeMaterielService DemandeMaterielService;
    @Autowired
    private  CategorieMaterielService CategorieMaterielService;
    @Autowired
    private  UserServise UserServise;
	@RequestMapping("/listeDm")
    public String showList(ModelMap modelMap,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {
        Page<DemandeMateriel> listDm = DemandeMaterielService.getAllDemandeParPage(page, size);
        modelMap.addAttribute("Dmms", listDm);
        modelMap.addAttribute("pages", new int[listDm.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);

        return "/demandeMateriel/list";
    }
	@RequestMapping("/showCreateDm")
	public String showCreateDm(ModelMap modelMap,
	@RequestParam(name = "page", defaultValue = "0") int page,
	@RequestParam(name = "size", defaultValue = "2") int size) {
        modelMap.addAttribute("Dmm", new DemandeMateriel());
        modelMap.addAttribute("edit", false);
        List<CategorieMateriel> listCategorieMateriel = CategorieMaterielService.getAllCategorie();
        System.out.println(listCategorieMateriel);
        modelMap.addAttribute("CM", listCategorieMateriel);
        List<User> listUser = UserServise.getAllUser();
        modelMap.addAttribute("Users", listUser);

        modelMap.addAttribute("pages",
        new int[DemandeMaterielService.getAllDemandeParPage(page, size).getTotalPages()]);
        
        modelMap.addAttribute("currentPage", page);
		return "/demandeMateriel/form";
	}
	@RequestMapping("/showEditDm")
	public String showEditDm(ModelMap modelMap,
	@RequestParam(name = "page", defaultValue = "0") int page,
	@RequestParam(name = "size", defaultValue = "2") int size,
	@RequestParam(name = "id") Long id) {
		DemandeMateriel Dm = DemandeMaterielService.getdemandeById(id);
        if(id != null) {
            Long Categ = Dm.getIdCategorie();
            CategorieMateriel C = CategorieMaterielService.getCategorieById(Categ);
            modelMap.addAttribute("Cat", C);
            User dest = Dm.getDestinataire();
            modelMap.addAttribute("dest", dest);
        }
		modelMap.addAttribute("Dmm", Dm);

        List<CategorieMateriel> listCategorieMateriel = CategorieMaterielService.getAllCategorie();
        modelMap.addAttribute("CM", listCategorieMateriel);
        List<User> listUser = UserServise.getAllUser();
        modelMap.addAttribute("Users", listUser);
		modelMap.addAttribute("edit", true);
		modelMap.addAttribute("pages", new int[DemandeMaterielService.getAllDemandeParPage(page, size).getTotalPages()]);
		return "/demandeMateriel/form";
	}
	@RequestMapping("/saveDm")
	public String saveDm(ModelMap modelMap,
            @Valid DemandeMateriel Dmm,
            BindingResult bindingResult,
            HttpServletRequest request,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) throws IOException {
        if (bindingResult.hasErrors()) {
            modelMap.addAttribute("org.springframework.validation.BindingResult.Dmm", bindingResult);
            modelMap.addAttribute("Dmm", Dmm);
            List<CategorieMateriel> listCategorieMateriel = CategorieMaterielService.getAllCategorie();
            modelMap.addAttribute("CM", listCategorieMateriel);
            List<User> listUser = UserServise.getAllUser();
            modelMap.addAttribute("Users", listUser);

            return "/demandeMateriel/form";
        } else {
            DemandeMateriel dm = new DemandeMateriel();
            dm.setDateEmploi(Dmm.getDateEmploi());
            dm.setDateDeRemise(Dmm.getDateDeRemise());
            dm.setIdCategorie(Dmm.getIdCategorie());
			dm.setDestinataire(Dmm.getDestinataire());
			dm.setResponsableClub(Dmm.getResponsableClub());
            dm = DemandeMaterielService.save(dm);
            modelMap.addAttribute("Dmm", new DemandeMateriel());
            modelMap.addAttribute("msg", "Demande de materiel enregistrée avec succès");
            modelMap.addAttribute("type", "success");
            modelMap.addAttribute("pages",
                    new int[DemandeMaterielService.getAllDemandeParPage(page, size).getTotalPages()]);
            return this.showList(modelMap, page, size);
        }
	}
	@RequestMapping("/updateDmm")
    public String updateDmm(ModelMap modelMap,
            @Valid DemandeMateriel Dmm,
            BindingResult bindingResult,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size,
            HttpServletRequest request) throws IOException {
        if (bindingResult.hasErrors()) {
            modelMap.addAttribute("org.springframework.validation.BindingResult.Dmm", bindingResult);
            modelMap.addAttribute("Dmm", Dmm);
            List<CategorieMateriel> listCategorieMateriel = CategorieMaterielService.getAllCategorie();
            modelMap.addAttribute("CM", listCategorieMateriel);
            List<User> listUser = UserServise.getAllUser();
            modelMap.addAttribute("Users", listUser);
            modelMap.addAttribute("edit", true);
            return "/demandeMateriel/form";
        } else {
            DemandeMateriel dm = new DemandeMateriel();
            dm.setId(Dmm.getId());
            dm.setDateEmploi(Dmm.getDateEmploi());
            dm.setDateDeRemise(Dmm.getDateDeRemise());
            dm.setIdCategorie(Dmm.getIdCategorie());
			dm.setDestinataire(Dmm.getDestinataire());
			dm.setResponsableClub(Dmm.getResponsableClub());
            dm = DemandeMaterielService.save(dm);
            modelMap.addAttribute("Dmm", new DemandeMateriel());
            modelMap.addAttribute("pages",
                    new int[DemandeMaterielService.getAllDemandeParPage(page, size).getTotalPages()]);
            modelMap.addAttribute("type", "warning");
            modelMap.addAttribute("msg", "Demande de creation de demande Materiel modifiée avec succès");
            return this.showList(modelMap, page, size);
        }
    }
	@RequestMapping("/deleteDmm")
	public String deleteDmm(ModelMap modelMap,
	@RequestParam(name = "id") Long id, 
	@RequestParam(name = "page", defaultValue = "0") int page,
	@RequestParam(name = "size", defaultValue = "2") int size) {
		DemandeMaterielService.deleteById(id);
		modelMap.addAttribute("type", "danger");
		modelMap.addAttribute("msg", "Demande de creation de demande Materiel supprimée avec succès");
		return this.showList(modelMap, page, size);

	}
    

	



}
