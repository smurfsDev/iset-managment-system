package com.projetIntegraion.spring.blogClub.controller;

import java.util.List;

import com.projetIntegraion.spring.blogClub.entity.About;
import com.projetIntegraion.spring.blogClub.entity.Activity;
import com.projetIntegraion.spring.blogClub.entity.Board;
import com.projetIntegraion.spring.blogClub.entity.Club;
import com.projetIntegraion.spring.blogClub.entity.Project;
import com.projetIntegraion.spring.blogClub.service.AboutService;
import com.projetIntegraion.spring.blogClub.service.ActivitiesService;
import com.projetIntegraion.spring.blogClub.service.BoardService;
import com.projetIntegraion.spring.blogClub.service.ClubService;
import com.projetIntegraion.spring.blogClub.service.ProjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageWebController {

    @Autowired
    AboutService aboutService;
    @Autowired
    ActivitiesService activitiesService;
    @Autowired
    BoardService boardService;
    @Autowired 
    ProjectService projectService;
    @Autowired
    ClubService clubService;
    @RequestMapping("/blogClub")
    public String showPage(ModelMap modelMap,
        @RequestParam(name = "idClub", defaultValue = "1") Long idClub
    ) {
               
       // Page<About> listDcc = aboutService.getAllAboutsParPage(page, size);
       Club c = clubService.getClub(idClub);
       List<About> Abouts = aboutService.getAboutByClubId(c);
       List<Activity> Activities = activitiesService.getActivityByClubId(c);
       List<Board> Boards = boardService.getBoardByClubId(c);
       List<Project> Projects = projectService.getProjectByClubId(c);
       System.out.println("Abouts : " + Abouts);
        System.out.println("Activities : " + Activities);
        System.out.println("Boards : " + Boards);
        System.out.println("Projects : " + Projects);
        System.out.println("idClub : " + idClub);

         modelMap.addAttribute("Abouts", Abouts);
        modelMap.addAttribute("Activities", Activities);
        modelMap.addAttribute("Boards", Boards);
        modelMap.addAttribute("Projects", Projects);
        //modelMap.addAttribute("pages", new int[listDcc.getTotalPages()]);
        //modelMap.addAttribute("currentPage", page);
        //System.out.println("abouts: "+modelMap.toString());
       // System.out.println("page = "+page);
       
        return "WebsiteClub";
    }
}
