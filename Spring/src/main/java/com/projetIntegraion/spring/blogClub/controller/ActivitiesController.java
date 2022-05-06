package com.projetIntegraion.spring.blogClub.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.projetIntegraion.spring.blogClub.entity.About;
import com.projetIntegraion.spring.blogClub.entity.Activity;
import com.projetIntegraion.spring.blogClub.service.AboutService;
import com.projetIntegraion.spring.blogClub.service.ActivitiesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ActivitiesController {
    @Autowired
    private ActivitiesService activitiesService;
   
    

    @RequestMapping("/showCreateActivity")
    public String showActivities(ModelMap modelMapAct,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size ) {
               
        Page<Activity> list = activitiesService.getAllActivitiesParPage(page, size);
         modelMapAct.addAttribute("ListAct", list);
        //System.out.println("listDcc"+list);
        modelMapAct.addAttribute("pages", new int[list.getTotalPages()]);
        modelMapAct.addAttribute("currentPage", page);
        System.out.println("Model "+modelMapAct.toString());
        System.out.println("page = "+page);
        System.out.println("Listaaa = "+modelMapAct.get("ListAct").toString());
        System.out.println("page Model = "+modelMapAct.get("currentPage"));
        System.out.println("Pagess = "+modelMapAct.get("pages").toString());
        return "ManageActivities";
    }
   @RequestMapping("/createActivity")
    public String createActivity(ModelMap modelMapAct,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {
        modelMapAct.addAttribute("activity", new Activity());
        modelMapAct.addAttribute("edit", false);
        modelMapAct.addAttribute("pages",
                new int[activitiesService.getAllActivitiesParPage(page, size).getTotalPages()]);
        modelMapAct.addAttribute("currentPage", page);
        return "createActivities";
    }
    @RequestMapping("/saveActivity")
    public String saveActivity(ModelMap modelMapAct,
           
            @Valid Activity activity,
            BindingResult bindingResult,
            HttpServletRequest request,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) throws IOException {
        if (bindingResult.hasErrors()) {
            modelMapAct.addAttribute("org.springframework.validation.BindingResult.About", bindingResult);
            modelMapAct.addAttribute("Activity", activity);
            return "createActivity";
        } else {
           /* About about = new About();
            System.out.println("About: "+About.toString());
            about.setLongDescription(About.getLongDescription());
            about.setClub(About.getClub());*/
            Activity newActivity = activitiesService.save(activity);
           // modelMapAct.addAttribute("About", newAbout);
            modelMapAct.addAttribute("msg", "About enregistrée avec succès");
            modelMapAct.addAttribute("type", "success");
            modelMapAct.addAttribute("pages",
                    new int[activitiesService.getAllActivitiesParPage(page, size).getTotalPages()]);
            System.out.println("modelMapAct = " + modelMapAct.toString());
                    return this.showActivities(modelMapAct, page, size);
        }
    }
    @RequestMapping("/deleteActivities")
    public String deleteActivities(@RequestParam("id") Long id, ModelMap modelMapAct,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {

        if (activitiesService.deleteById(id)) {
            modelMapAct.addAttribute("type", "danger");
            modelMapAct.addAttribute("msg", "Activité supprimé!");
        } else {
            modelMapAct.addAttribute("type", "danger");
            modelMapAct.addAttribute("msg", "Activité non supprimée : Id non trouvé");
        }
        return this.showActivities(modelMapAct, page, size);

    }
    @RequestMapping("/modifierActivities")
    public String showUpdateActivities(@RequestParam("id") Long id, ModelMap modelMapAct,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {
        Activity activity = activitiesService.getActivity(id);
        modelMapAct.addAttribute("activity", activity);
        modelMapAct.addAttribute("edit", true);
        modelMapAct.addAttribute("pages",
                new int[activitiesService.getAllActivitiesParPage(page, size).getTotalPages()]);
        return "createActivities";
    }

    @RequestMapping("/updateActivities")
    public String updateActivities(ModelMap modelMapAct,
            
            @Valid Activity Activity,
            BindingResult bindingResult,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size,
            HttpServletRequest request) throws IOException {
        if (bindingResult.hasErrors()) {
            modelMapAct.addAttribute("org.springframework.validation.BindingResult.Dcc", bindingResult);
            modelMapAct.addAttribute("Activity", Activity);
            modelMapAct.addAttribute("edit", true);
            return "createActivities";
        } else {
            Activity activity = new Activity();
            activity.setId(Activity.getId());
            activity.setParagraphe(Activity.getParagraphe());
            activity.setClub(Activity.getClub());
            
            Activity newUpdatedActivity = activitiesService.save(activity);
            
            //dc = DemandeCreationClubService.save(dc);
            //modelMapAct.addAttribute("About", newUpdatedAbout);
            modelMapAct.addAttribute("pages",
                    new int[activitiesService.getAllActivitiesParPage(page, size).getTotalPages()]);
            modelMapAct.addAttribute("type", "warning");
            modelMapAct.addAttribute("msg", "Activité modifiée avec succès");
            return this.showActivities(modelMapAct, page, size);
        }
    }

}
