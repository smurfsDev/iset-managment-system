package com.projetIntegraion.spring.demandeSalle.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.User;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.repository.UserRepository;
import com.projetIntegraion.spring.administrateur.departement.entity.Departement;
import com.projetIntegraion.spring.administrateur.departement.service.DepartementService;
import com.projetIntegraion.spring.demandeSalle.entity.DemandeSalle;
import com.projetIntegraion.spring.demandeSalle.entity.DemandeSalleSalle;
import com.projetIntegraion.spring.demandeSalle.entity.Salle;
import com.projetIntegraion.spring.demandeSalle.repository.DemandeSalleSalleRepository;
import com.projetIntegraion.spring.demandeSalle.service.DemandeSalleService;
import com.projetIntegraion.spring.demandeSalle.service.SalleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DemandeSalleController {
    @Autowired
    private DemandeSalleService demandeSalleService;
    @Autowired
    private DepartementService departementService;
    @Autowired
    private SalleService salleService;
    @Autowired
    private DemandeSalleSalleRepository demandeSalleSalleRepository;
    @Autowired
    private UserRepository userRepository;

    public User getUser(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        User user = userRepository.findUserWithName(principal.getName()).get();
        return user;
    }

    @RequestMapping("/listeDS")
    public String getDemandeSalle(ModelMap modelMap, HttpServletRequest request,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {
        Page<DemandeSalle> listDemandeSalle = demandeSalleService
                .getAllDemandeSalleParPageParResponsableClub(this.getUser(request), page, size);
        modelMap.addAttribute("demandeSalle", listDemandeSalle);
        modelMap.addAttribute("pages", new int[listDemandeSalle.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        return "demandeSalle/list";
    }

    @RequestMapping("/listeDsDestinataire")
    public String getMesDemandeSalle(ModelMap modelMap, HttpServletRequest request,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {
        Page<DemandeSalle> listDemandeSalle = demandeSalleService
                .getAllDemandeSalleParPageParDestinataire(this.getUser(request).getId(), page, size);
        modelMap.addAttribute("demandeSalle", listDemandeSalle);

        modelMap.addAttribute("pages", new int[listDemandeSalle.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        return "demandeSalle/mesDemandes";
    }

    @RequestMapping("/ShowcreateDS")
    public String showCreateDs(ModelMap modelMap,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {

        modelMap.addAttribute("demandeSalle", new DemandeSalle());
        modelMap.addAttribute("edit", false);
        List<Departement> listDepartement = departementService.getAllDepartement();
        System.out.println(listDepartement);
        modelMap.addAttribute("Departements", listDepartement);
        List<Salle> salles = salleService.getAllSalle();
        modelMap.addAttribute("sallesdep", salles);
        System.out.println(salles);
        modelMap.addAttribute("pages",
                new int[demandeSalleService.getAllDemandeSalleParPage(page, size).getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        return "demandeSalle/form";
    }

    @RequestMapping("/showEditDS")
    public String showEditDs(ModelMap modelMap, Long id,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {
        DemandeSalle demandeSalle = demandeSalleService.getDemandeSalleById(id);
        modelMap.addAttribute("demandeSalle", demandeSalle);
        modelMap.addAttribute("edit", true);
        List<Departement> listDepartement = departementService.getAllDepartement();
        System.out.println(listDepartement);
        modelMap.addAttribute("Departements", listDepartement);
        List<Salle> salles = salleService.getAllSalle();
        modelMap.addAttribute("sallesdep", salles);
        modelMap.addAttribute("pages",
                new int[demandeSalleService.getAllDemandeSalleParPage(page, size).getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        return "demandeSalle/form";
    }

    @RequestMapping("/saveDS")
    public String saveDS(ModelMap modelMap,
            @Valid DemandeSalle demandeSalle,
            BindingResult bindingResult,
            HttpServletRequest request,
            Long salle,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        if (salle == null) {
            bindingResult.rejectValue("salle", "error.salle", "Veuillez choisir une salle");
            modelMap.addAttribute("errorMsg", "Veuillez choisir une salle");
        }
        if (bindingResult.hasErrors()) {

            modelMap.addAttribute("org.springframework.validation.BindingResult.demandeSalle", bindingResult);
            modelMap.addAttribute("demandeSalle", demandeSalle);
            List<Departement> listDepartement = departementService.getAllDepartement();
            System.out.println(listDepartement);
            modelMap.addAttribute("Departements", listDepartement);
            List<Salle> salles = salleService.getAllSalle();
            modelMap.addAttribute("sallesdep", salles);
            System.out.println(salles);
            return "demandeSalle/form";
        } else {

            DemandeSalle dm = new DemandeSalle();
            DemandeSalleSalle dms = new DemandeSalleSalle();
            dm.setDateEmploi(demandeSalle.getDateEmploi());
            dm.setDateDeRemise(demandeSalle.getDateDeRemise());
            dm.setResponsable(this.getUser(request));
            dms.setDemandeSalle(dm);
            dms.setSalle(salleService.getSalleById(salle));
            demandeSalleService.save(dm);
            demandeSalleSalleRepository.save(dms);
            dm = demandeSalleService.save(dm);
            // modelMap.addAttribute("demandeSalle", new DemandeSalle());
            modelMap.addAttribute("msg", "Demande de Salle enregistrée avec succès");
            modelMap.addAttribute("type", "success");
            Page<DemandeSalle> listDemandeSalle = demandeSalleService
                    .getAllDemandeSalleParPageParResponsableClub(this.getUser(request), page, size);
            modelMap.addAttribute("demandeSalle", listDemandeSalle);
            modelMap.addAttribute("pages",
                    new int[demandeSalleService.getAllDemandeSalleParPage(page, size).getTotalPages()]);

            return "redirect:/listeDS?page=" + page + "&size=" + size;
        }
    }

    @RequestMapping("updateDS")
    public String updateDS(ModelMap modelMap,
            @Valid DemandeSalle demandeSalle,
            BindingResult bindingResult,
            HttpServletRequest request,
            Long salle,
            Long id,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) throws IOException {
        if (salle == null) {
            bindingResult.rejectValue("salle", "error.salle", "Veuillez choisir une salle");
            modelMap.addAttribute("errorMsg", "Veuillez choisir une salle");
        }
        if (bindingResult.hasErrors()) {
            modelMap.addAttribute("org.springframework.validation.BindingResult.demandeSalle", bindingResult);
            modelMap.addAttribute("demandeSalle", demandeSalle);
            modelMap.addAttribute("edit", true);
            List<Departement> listDepartement = departementService.getAllDepartement();
            System.out.println(listDepartement);
            modelMap.addAttribute("Departements", listDepartement);
            List<Salle> salles = salleService.getAllSalle();
            modelMap.addAttribute("sallesdep", salles);
            System.out.println(salles);
            return "demandeSalle/form";
        } else {
            DemandeSalle dm = new DemandeSalle();
            DemandeSalleSalle dms = demandeSalleSalleRepository.findByDemandeSalleId(demandeSalle.getId());
            dms.setDemandeSalle(dm);
            dm.setId(id);
            dm.setDateEmploi(demandeSalle.getDateEmploi());
            dm.setDateDeRemise(demandeSalle.getDateDeRemise());
            dm.setResponsable(this.getUser(request));
            // dm.setSalle((demandeSalle.getSalle()));
            dms.setSalle(salleService.getSalleById(salle));
            dm = demandeSalleService.save(dm);
            demandeSalleSalleRepository.save(dms);
            modelMap.addAttribute("demandeSalle", new DemandeSalle());
            modelMap.addAttribute("msg", "Demande de Salle modifiée avec succès");
            modelMap.addAttribute("type", "success");
            modelMap.addAttribute("pages",
                    new int[demandeSalleService.getAllDemandeSalleParPage(page, size).getTotalPages()]);

            return "redirect:/listeDS?page=" + page + "&size=" + size;
        }
    }

    @RequestMapping("/deleteDS")
    public String deleteDS(ModelMap modelMap, Long id,
            HttpServletRequest request,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {
        DemandeSalle demandeSalle = demandeSalleService.getDemandeSalleById(id);
        demandeSalleService.delete(demandeSalle);
        modelMap.addAttribute("msg", "Demande de Salle supprimée avec succès");
        modelMap.addAttribute("type", "success");
        modelMap.addAttribute("pages",
                new int[demandeSalleService.getAllDemandeSalleParPage(page, size).getTotalPages()]);
        return this.getDemandeSalle(modelMap, request, page, size);
    }

    @RequestMapping("setReponseDS")
    public String setReponseDS(ModelMap modelMap, Long idDemande, String reponse,
            HttpServletRequest request,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {
        DemandeSalle demandeSalle = demandeSalleService.getDemandeSalleById(idDemande);
        demandeSalle.setReponse(reponse);
        demandeSalleService.save(demandeSalle);
        modelMap.addAttribute("msg", "Demande de Salle modifiée avec succès");
        modelMap.addAttribute("type", "success");
        Page<DemandeSalle> listDemandeSalle = demandeSalleService
                .getAllDemandeSalleParPageParDestinataire(this.getUser(request).getId(), page, size);
        modelMap.addAttribute("demandeSalle", listDemandeSalle);

        modelMap.addAttribute("pages", new int[listDemandeSalle.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        return "demandeSalle/mesDemandes";
    }

    @RequestMapping("/approveDS")
    public String approveDS(ModelMap modelMap, Long id,
            HttpServletRequest request,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {
        DemandeSalle demandeSalle = demandeSalleService.getDemandeSalleById(id);
        demandeSalle.setStatus(1);
        demandeSalleService.save(demandeSalle);
        modelMap.addAttribute("msg", "Demande de Salle acceptée avec succès");
        modelMap.addAttribute("type", "success");
        Page<DemandeSalle> listDemandeSalle = demandeSalleService
                .getAllDemandeSalleParPageParDestinataire(this.getUser(request).getId(), page, size);
        modelMap.addAttribute("demandeSalle", listDemandeSalle);

        modelMap.addAttribute("pages", new int[listDemandeSalle.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        return "demandeSalle/mesDemandes";
    }

    @RequestMapping("/refuseDS")
    public String refuseDS(ModelMap modelMap, Long id,
            HttpServletRequest request,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {
        DemandeSalle demandeSalle = demandeSalleService.getDemandeSalleById(id);
        demandeSalle.setStatus(2);
        demandeSalleService.save(demandeSalle);
        modelMap.addAttribute("msg", "Demande de Salle refusée avec succès");
        modelMap.addAttribute("type", "success");
        Page<DemandeSalle> listDemandeSalle = demandeSalleService
                .getAllDemandeSalleParPageParDestinataire(this.getUser(request).getId(), page, size);
        modelMap.addAttribute("demandeSalle", listDemandeSalle);

        modelMap.addAttribute("pages", new int[listDemandeSalle.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        return "demandeSalle/mesDemandes";
    }

}
