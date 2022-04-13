package com.projetIntegraion.spring.Etudiant.demandeCreationClub.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        return "create";
    }

    @RequestMapping("/saveDcc")
    public String saveDcc(ModelMap modelMap,
    @RequestParam("image") MultipartFile multipartFile,
    @ModelAttribute("Dcc") DemandeCreationClub dcc,
            HttpServletRequest request) throws IOException {
        DemandeCreationClub dc = new DemandeCreationClub();
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        dc.setNomClub(dcc.getNomClub());
        dc.setLogo(fileName);
        dc.setDateCreation(dcc.getDateCreation());
        dc.setActivite(dcc.getActivite());
        dc.setPresident(dcc.getPresident());
        dc.setVicePresident(dcc.getVicePresident());
        // dc.setStatus(dcc.getStatus());
        // dc.setResponsableClub(dcc.getResponsableClub());
        // dc.setAdmin(dcc.getAdmin());
        dc = DemandeCreationClubService.save(dc);
        String uploadDir = "user-photos/" + dc.getId();
    
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        // this.doUpload(request, dcc);
        return "redirect:/listeDcc";
    }





    // private void doUpload(HttpServletRequest request, DemandeCreationClub dcc) {

    // // Root Directory.
    // String uploadRootPath = request.getServletContext().getRealPath("upload");

    // File uploadRootDir = new File(uploadRootPath);
    // // Create directory if it not exists.
    // if (!uploadRootDir.exists()) {
    // uploadRootDir.mkdirs();
    // }
    // MultipartFile[] fileDatas = dcc.getLogo();
    // //
    // List<File> uploadedFiles = new ArrayList<File>();
    // List<String> failedFiles = new ArrayList<String>();

    // for (MultipartFile fileData : fileDatas) {

    // // Client File Name
    // String name = fileData.getOriginalFilename();
    // if (name != null && name.length() > 0) {
    // try {
    // // Create the file at server
    // File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator +
    // name);

    // BufferedOutputStream stream = new BufferedOutputStream(new
    // FileOutputStream(serverFile));
    // stream.write(fileData.getBytes());
    // stream.close();
    // //
    // uploadedFiles.add(serverFile);
    // System.out.println("Write file: " + serverFile);
    // } catch (Exception e) {
    // System.out.println("Error Write file: " + name);
    // failedFiles.add(name);
    // }
    // }
    // }
    // }

}
