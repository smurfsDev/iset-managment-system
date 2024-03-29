package com.projetIntegraion.spring.demandeMateriel.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.User;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.repository.UserRepository;
import com.projetIntegraion.spring.demandeMateriel.entity.CategorieMateriel;
import com.projetIntegraion.spring.demandeMateriel.entity.DemandeMateriel;
import com.projetIntegraion.spring.demandeMateriel.repository.demandeMaterielRepository;
import com.projetIntegraion.spring.demandeMateriel.service.CategorieMaterielService;
import com.projetIntegraion.spring.demandeMateriel.service.DemandeMaterielService;
import com.projetIntegraion.spring.demandeMateriel.service.MaterielService;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.service.UserService;

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
        private CategorieMaterielService CategorieMaterielService;
        @Autowired
        private UserService UserServise;
        @Autowired
        private demandeMaterielRepository DemandeMaterielRepository;
        @Autowired
        private MaterielService materielService;
        @Autowired
        private UserRepository userRepository;

        public User getUser(HttpServletRequest request) {
                Principal principal = request.getUserPrincipal();
                User user = userRepository.findUserWithName(principal.getName()).get();
                return user;
        }

        @RequestMapping("/listeDm")
        public String showList(ModelMap modelMap, HttpServletRequest request,
                        @RequestParam(name = "page", defaultValue = "0") int page,
                        @RequestParam(name = "size", defaultValue = "2") int size) {
                Page<DemandeMateriel> listDm = DemandeMaterielService
                                .getAllDemandeSalleParPageParResponsableClub(this.getUser(request), page, size);
                modelMap.addAttribute("Dmms", listDm);
                modelMap.addAttribute("pages", new int[listDm.getTotalPages()]);
                modelMap.addAttribute("currentPage", page);

                return "/demandeMateriel/list";
        }

        @RequestMapping("/listeDmDestinataire")
        public String showListDestinataire(ModelMap modelMap, HttpServletRequest request,
                        @RequestParam(name = "page", defaultValue = "0") int page,
                        @RequestParam(name = "size", defaultValue = "2") int size) {
                Page<DemandeMateriel> listDm = DemandeMaterielService
                                .getAllDemandeMaterielParPageParDestinataire(this.getUser(request), page, size);
                List<String> titreMateriel = DemandeMaterielRepository
                                .findMaterielByDestinataireId(this.getUser(request).getId());
                List<Integer> quantiteMateriel = DemandeMaterielRepository
                                .findQuantiteByDestinataireId(this.getUser(request).getId());
                modelMap.addAttribute("Dmms", listDm);
                modelMap.addAttribute("titreMateriel", titreMateriel);
                modelMap.addAttribute("quantiteMateriel", quantiteMateriel);
                modelMap.addAttribute("pages", new int[listDm.getTotalPages()]);
                modelMap.addAttribute("currentPage", page);

                return "/demandeMateriel/mesDemandes";
        }

        @RequestMapping("/showCreateDm")
        public String showCreateDm(ModelMap modelMap, HttpServletRequest request,
                        @RequestParam(name = "page", defaultValue = "0") int page,
                        @RequestParam(name = "size", defaultValue = "2") int size) {
                modelMap.addAttribute("Dmm", new DemandeMateriel());
                modelMap.addAttribute("edit", false);

                List<CategorieMateriel> listCategorieMateriel = CategorieMaterielService.getAllCategorie();
                System.out.println(listCategorieMateriel);
                modelMap.addAttribute("CM", listCategorieMateriel);

                System.out.println(listCategorieMateriel);
                List<User> listUser = materielService.getAllResponsableMateriel();
                modelMap.addAttribute("Users", listUser);

                modelMap.addAttribute("pages",
                                new int[DemandeMaterielService
                                                .getAllDemandeMaterielParPageParDestinataire(this.getUser(request),
                                                                page, size)
                                                .getTotalPages()]);

                modelMap.addAttribute("currentPage", page);
                return "/demandeMateriel/form";
        }

        @RequestMapping("/showEditDm")
        public String showEditDm(ModelMap modelMap, HttpServletRequest request,
                        @RequestParam(name = "page", defaultValue = "0") int page,
                        @RequestParam(name = "size", defaultValue = "2") int size,
                        @RequestParam(name = "id") Long id) {
                DemandeMateriel Dm = DemandeMaterielService.getdemandeById(id);
                if (id != null) {
                        Long Categ = Dm.getIdCategorie();
                        CategorieMateriel C = CategorieMaterielService.getCategorieById(Categ);
                        modelMap.addAttribute("Cat", C);
                        User dest = Dm.getDestinataire();
                        modelMap.addAttribute("dest", dest);
                }
                modelMap.addAttribute("Dmm", Dm);

                List<CategorieMateriel> listCategorieMateriel = CategorieMaterielService.getAllCategorie();
                modelMap.addAttribute("CM", listCategorieMateriel);
                // List<User> listUser = UserServise.getAllUser();
                // modelMap.addAttribute("Users", listUser);
                List<User> listUser = materielService.getAllResponsableMateriel();
                modelMap.addAttribute("Users", listUser);
                modelMap.addAttribute("edit", true);
                modelMap.addAttribute("pages",
                                new int[DemandeMaterielService
                                                .getAllDemandeMaterielParPageParDestinataire(this.getUser(request),
                                                                page, size)
                                                .getTotalPages()]);
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

                        System.out.println(listCategorieMateriel);
                        modelMap.addAttribute("CM", listCategorieMateriel);

                        // List<User> listUser = UserServise.getAllUser();
                        // modelMap.addAttribute("Users", listUser);
                        List<User> listUser = materielService.getAllResponsableMateriel();
                        modelMap.addAttribute("Users", listUser);
                        return "/demandeMateriel/form";
                } else {
                        DemandeMateriel dm = new DemandeMateriel();
                        dm.setDateEmploi(Dmm.getDateEmploi());
                        dm.setDateDeRemise(Dmm.getDateDeRemise());
                        dm.setIdCategorie(Dmm.getIdCategorie());
                        dm.setDestinataire(Dmm.getDestinataire());
                        dm.setResponsableClub(this.getUser(request));
                        dm = DemandeMaterielService.save(dm);
                        modelMap.addAttribute("Dmm", new DemandeMateriel());
                        modelMap.addAttribute("msg", "Demande de materiel enregistrée avec succès");
                        modelMap.addAttribute("type", "success");
                        modelMap.addAttribute("pages",
                                        new int[DemandeMaterielService
                                                        .getAllDemandeMaterielParPageParDestinataire(
                                                                        this.getUser(request), page, size)
                                                        .getTotalPages()]);
                        return this.showList(modelMap, request, page, size);
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
                        // List<User> listUser = UserServise.getAllUser();
                        User dest = Dmm.getDestinataire();
                        modelMap.addAttribute("dest", dest);
                        Long Categ = Dmm.getIdCategorie();
                        CategorieMateriel C = CategorieMaterielService.getCategorieById(Categ);
                        modelMap.addAttribute("Cat", C);

                        // modelMap.addAttribute("Users", listUser);
                        List<User> listUser = materielService.getAllResponsableMateriel();
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
                        dm.setResponsableClub(this.getUser(request));
                        dm = DemandeMaterielService.save(dm);
                        modelMap.addAttribute("Dmm", new DemandeMateriel());
                        modelMap.addAttribute("pages",
                                        new int[DemandeMaterielService
                                                        .getAllDemandeMaterielParPageParDestinataire(
                                                                        this.getUser(request), page, size)
                                                        .getTotalPages()]);
                        modelMap.addAttribute("type", "warning");
                        modelMap.addAttribute("msg", "Demande de creation de demande Materiel modifiée avec succès");
                        return this.showList(modelMap, request, page, size);
                }
        }

        @RequestMapping("/deleteDmm")
        public String deleteDmm(ModelMap modelMap, HttpServletRequest request,
                        @RequestParam(name = "id") Long id,
                        @RequestParam(name = "page", defaultValue = "0") int page,
                        @RequestParam(name = "size", defaultValue = "2") int size) {
                DemandeMaterielService.deleteById(id);
                modelMap.addAttribute("type", "danger");
                modelMap.addAttribute("msg", "Demande de creation de demande Materiel supprimée avec succès");
                return this.showList(modelMap, request, page, size);

        }

        @RequestMapping("/setReponse")
        public String setReponse(ModelMap modelMap,
                        Long idDemande, String reponse,
                        @RequestParam(name = "page", defaultValue = "0") int page,
                        @RequestParam(name = "size", defaultValue = "2") int size,
                        HttpServletRequest request) {
                DemandeMateriel dm = DemandeMaterielService.getdemandeById(idDemande);
                System.out.println(dm);

                dm.setReponse(reponse);
                DemandeMaterielService.save(dm);
                System.out.println(dm);

                modelMap.addAttribute("type", "success");
                modelMap.addAttribute("msg", "reponse ajouter avec succès");
                Page<DemandeMateriel> listDm = DemandeMaterielService
                                .getAllDemandeMaterielParPageParDestinataire(this.getUser(request), page, size);
                List<String> titreMateriel = DemandeMaterielRepository
                                .findMaterielByDestinataireId(this.getUser(request).getId());
                List<Integer> quantiteMateriel = DemandeMaterielRepository
                                .findQuantiteByDestinataireId(this.getUser(request).getId());
                modelMap.addAttribute("Dmms", listDm);
                modelMap.addAttribute("titreMateriel", titreMateriel);
                modelMap.addAttribute("quantiteMateriel", quantiteMateriel);
                modelMap.addAttribute("pages", new int[listDm.getTotalPages()]);
                modelMap.addAttribute("currentPage", page);

                return "/demandeMateriel/mesDemandes";
        }

        @RequestMapping("/approveDmm")
        public String approveDmm(ModelMap modelMap,
                        @RequestParam(name = "id") Long id,
                        @RequestParam(name = "page", defaultValue = "0") int page,
                        @RequestParam(name = "size", defaultValue = "2") int size,
                        HttpServletRequest request) {
                DemandeMateriel dm = DemandeMaterielService.getdemandeById(id);
                dm.setStatus(1);
                DemandeMaterielService.save(dm);
                modelMap.addAttribute("type", "success");
                modelMap.addAttribute("msg", "Demande Materiel approuvée avec succès");
                Page<DemandeMateriel> listDm = DemandeMaterielService
                                .getAllDemandeMaterielParPageParDestinataire(this.getUser(request), page, size);
                List<String> titreMateriel = DemandeMaterielRepository
                                .findMaterielByDestinataireId(this.getUser(request).getId());
                List<Integer> quantiteMateriel = DemandeMaterielRepository
                                .findQuantiteByDestinataireId(this.getUser(request).getId());
                modelMap.addAttribute("Dmms", listDm);
                modelMap.addAttribute("titreMateriel", titreMateriel);
                modelMap.addAttribute("quantiteMateriel", quantiteMateriel);
                modelMap.addAttribute("pages", new int[listDm.getTotalPages()]);
                modelMap.addAttribute("currentPage", page);

                return "/demandeMateriel/mesDemandes";
        }

        @RequestMapping("/refuseDmm")
        public String refuseDmm(ModelMap modelMap,
                        @RequestParam(name = "id") Long id,
                        @RequestParam(name = "page", defaultValue = "0") int page,
                        @RequestParam(name = "size", defaultValue = "2") int size,
                        HttpServletRequest request) {
                DemandeMateriel dm = DemandeMaterielService.getdemandeById(id);
                dm.setStatus(2);
                DemandeMaterielService.save(dm);
                modelMap.addAttribute("type", "success");
                modelMap.addAttribute("msg", "Demande Materiel refusée avec succès");
                Page<DemandeMateriel> listDm = DemandeMaterielService
                                .getAllDemandeMaterielParPageParDestinataire(this.getUser(request), page, size);
                List<String> titreMateriel = DemandeMaterielRepository
                                .findMaterielByDestinataireId(this.getUser(request).getId());
                List<Integer> quantiteMateriel = DemandeMaterielRepository
                                .findQuantiteByDestinataireId(this.getUser(request).getId());
                modelMap.addAttribute("Dmms", listDm);
                modelMap.addAttribute("titreMateriel", titreMateriel);
                modelMap.addAttribute("quantiteMateriel", quantiteMateriel);
                modelMap.addAttribute("pages", new int[listDm.getTotalPages()]);
                modelMap.addAttribute("currentPage", page);

                return "/demandeMateriel/mesDemandes";
        }

}
