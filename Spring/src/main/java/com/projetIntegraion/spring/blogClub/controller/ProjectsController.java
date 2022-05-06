package com.projetIntegraion.spring.blogClub.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.projetIntegraion.spring.blogClub.entity.Project;
import com.projetIntegraion.spring.blogClub.service.ProjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ProjectsController {
    @Autowired
    private ProjectService projectService;

    @RequestMapping("/showCreateProject")
    public String showCreateProject(ModelMap modelMap,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {
        Page<Project> listProjects = projectService.getAllProjectsParPage(page, size);
        modelMap.addAttribute("listProjects", listProjects);
        modelMap.addAttribute("pages", new int[listProjects.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);

        return "ManageProjects";
    }

    @RequestMapping("/createProject")
    public String createDcc(ModelMap modelMap,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {
        modelMap.addAttribute("project", new Project());
        modelMap.addAttribute("edit", false);
        modelMap.addAttribute("pages",
                new int[projectService.getAllProjectsParPage(page, size).getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        return "createProject";
    }

    @RequestMapping("/saveProject")
    public String saveProject(ModelMap modelMap,
            @RequestParam("affiche") MultipartFile multipartFile,
            @Valid Project project,
            BindingResult bindingResult,
            HttpServletRequest request,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) throws IOException {
        if (bindingResult.hasErrors()) {
            modelMap.addAttribute("org.springframework.validation.BindingResult.Dcc", bindingResult);
            modelMap.addAttribute("listProjects", project);
            return "createProject";
        } else {
            // Project Project = new Project();
            // Project.setAffiche(project.getAffiche());
            // dc.setDateCreation(Dcc.getDateCreation());
            // dc.setActivite(Dcc.getActivite());
            // dc.setPresident(Dcc.getPresident());
            // dc.setVicePresident(Dcc.getVicePresident());
            // dc.setLogo(new String(Base64.encodeBase64(multipartFile.getBytes())));
            //Project project = new  
            Project Project = projectService.save(project);
           //dc = DemandeCreationClubService.save(dc);
           // modelMap.addAttribute("Dcc", new DemandeCreationClub());
            modelMap.addAttribute("msg", "Demande de création de club enregistrée avec succès");
            modelMap.addAttribute("type", "success");
            modelMap.addAttribute("pages",
                    new int[projectService.getAllProjectsParPage(page, size).getTotalPages()]);
            return this.showCreateProject(modelMap, page, size);
        }
    }

    @RequestMapping("/deleteProject")
    public String deleteProject(@RequestParam("id") Long id, ModelMap modelMap,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {

        if (projectService.deleteById(id)) {
            modelMap.addAttribute("type", "danger");
            modelMap.addAttribute("msg", "Affiche supprimée !!");
        } else {
            modelMap.addAttribute("type", "danger");
            modelMap.addAttribute("msg", "Affiche non supprimée : Id non trouvé");
        }
        return this.showCreateProject(modelMap, page, size);

    }

    @RequestMapping("/modifierProject")
    public String showUpdateProject(@RequestParam("id") Long id, ModelMap modelMap,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {
        Project project = projectService.getProject(id);
        modelMap.addAttribute("listProjects", project);
        modelMap.addAttribute("edit", true);
        modelMap.addAttribute("pages",
                new int[projectService.getAllProjectsParPage(page, size).getTotalPages()]);
        return "createProject";
    }

    @RequestMapping("/updateProject")
    public String updateProject(ModelMap modelMap,
            @RequestParam(name = "affiche") MultipartFile multipartFile,
            @Valid Project Project,
            BindingResult bindingResult,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size,
            HttpServletRequest request) throws IOException {
        if (bindingResult.hasErrors()) {
            modelMap.addAttribute("org.springframework.validation.BindingResult.Dcc", bindingResult);
            modelMap.addAttribute("listProjects", Project);
            modelMap.addAttribute("edit", true);
            return "createProject";
        } else {
            // Project project = new Project();
            // project.setId(Project.getId());
            // dc.setNomClub(Dcc.getNomClub());
            // dc.setDateCreation(Dcc.getDateCreation());
            // dc.setActivite(Dcc.getActivite());
            // dc.setPresident(Dcc.getPresident());
            // dc.setVicePresident(Dcc.getVicePresident());
            // if (multipartFile.getSize() != 0) {
            //     dc.setLogo(new String(Base64.encodeBase64(multipartFile.getBytes())));
            // } else {
            //     dc.setLogo(DemandeCreationClubService.getDemandeCreationClub(Dcc.getId()).getLogo());
            // }
             projectService.save(Project);
            modelMap.addAttribute("listProjects", new Project());
            modelMap.addAttribute("pages",
                    new int[projectService.getAllProjectsParPage(page, size).getTotalPages()]);
            modelMap.addAttribute("type", "warning");
            modelMap.addAttribute("msg", "Affiche modifiée avec succès");
            return this.showCreateProject(modelMap, page, size);
        }
    }

    // @RequestMapping("/accept")
    // public String acceptDcc(@RequestParam("id") Long id, ModelMap modelMap,
    //         @RequestParam(name = "page", defaultValue = "0") int page,
    //         @RequestParam(name = "size", defaultValue = "2") int size) {
    //     DemandeCreationClub dc = DemandeCreationClubService.getDemandeCreationClub(id);
    //     dc.setStatus(1);
    //     DemandeCreationClubService.save(dc);
    //     modelMap.addAttribute("Dcc", new DemandeCreationClub());
    //     modelMap.addAttribute("pages",
    //             new int[DemandeCreationClubService.getAllDemandeCreationClubParPage(page, size).getTotalPages()]);
    //     modelMap.addAttribute("type", "success");
    //     modelMap.addAttribute("msg", "Demande de creation de club acceptée avec succès");
    //     return this.showList(modelMap, page, size);
    // }

    // @RequestMapping("/decline")
    // public String declineDcc(@RequestParam("id") Long id, ModelMap modelMap,
    //         @RequestParam(name = "page", defaultValue = "0") int page,
    //         @RequestParam(name = "size", defaultValue = "2") int size) {
    //     DemandeCreationClub dc = DemandeCreationClubService.getDemandeCreationClub(id);
    //     dc.setStatus(2);
    //     DemandeCreationClubService.save(dc);
    //     modelMap.addAttribute("Dcc", new DemandeCreationClub());
    //     modelMap.addAttribute("pages",
    //             new int[DemandeCreationClubService.getAllDemandeCreationClubParPage(page, size).getTotalPages()]);
    //     modelMap.addAttribute("type", "success");
    //     modelMap.addAttribute("msg", "Demande de creation de club refusée avec succès");
    //     return this.showList(modelMap, page, size);
    // }

    // @RequestMapping("/searchProduit")
    // public String searchProduit(@RequestParam("name") String name, ModelMap modelMap,
    //         @RequestParam(name = "page", defaultValue = "0") int page,
    //         @RequestParam(name = "size", defaultValue = "2") int size) {
                
    //     Page<DemandeCreationClub> prods = DemandeCreationClubService.getDemandeCreationClubParNom(name, page, size);
    //     modelMap.addAttribute("Dccs", prods);
    //     modelMap.addAttribute("pages", new int[prods.getTotalPages()]);
    //     modelMap.addAttribute("currentPage", page);
    //     modelMap.addAttribute("name", name);
    //     return "list";
    // }

}

