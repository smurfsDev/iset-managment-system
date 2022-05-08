package com.projetIntegraion.spring.blogClub.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.projetIntegraion.spring.blogClub.entity.Board;
import com.projetIntegraion.spring.blogClub.service.BoardService;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class BoardsController {
    @Autowired
    private BoardService boardService;
   
    

    @RequestMapping("/showCreateBoard")
    public String showBoard(ModelMap modelMap,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size ) {
               
        Page<Board> Boards = boardService.getAllBoardsParPage(page, size);
         modelMap.addAttribute("Board", Boards);
        
        modelMap.addAttribute("pages", new int[Boards.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
      //  System.out.println("abouts: "+modelMap.toString());
        System.out.println("page = "+page);
        System.out.println("currentPage = "+modelMap.get("currentPage").toString());
        System.out.println("siizee = "+modelMap.get("pages").toString());
        return "ManageBoard";
    }
   @RequestMapping("/createBoard")
    public String createAbout(ModelMap modelMap,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {
        modelMap.addAttribute("Board", new Board());
        modelMap.addAttribute("edit", false);
        modelMap.addAttribute("pages",
                new int[boardService.getAllBoardsParPage(page, size).getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        return "createBoard";
    }
    @RequestMapping("/saveBoard")
    public String saveBoard(ModelMap modelMap,
            @RequestParam("photo") MultipartFile multipartFile,
            @Valid Board Board,
            BindingResult bindingResult,
            HttpServletRequest request,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) throws IOException {
        // if (bindingResult.hasErrors()) {
        //     modelMap.addAttribute("org.springframework.validation.BindingResult.Board", bindingResult);
        //     modelMap.addAttribute("Board", Board);
        //     return "createBoard";
        // } else {
           /* About about = new About();
            System.out.println("About: "+About.toString());
            about.setLongDescription(About.getLongDescription());
            about.setClub(About.getClub());*/
            //Project project = new  
            Board b = new Board();
            b.setName(Board.getName());
            b.setPost(Board.getPost());
            b.setPhoto(new String(Base64.encodeBase64(multipartFile.getBytes())));
           // p.setAffiche(new String(multipartFile.getBytes()));
            //System.out.println("p.getAffiche()" + project.getAffiche());
            // p = projectService.save(p);
          
            b = boardService.save(b);
           // modelMap.addAttribute("About", newAbout);
            modelMap.addAttribute("msg", "Membre de bureau enregistrée avec succès");
            modelMap.addAttribute("type", "success");
            modelMap.addAttribute("pages",
                    new int[boardService.getAllBoardsParPage(page, size).getTotalPages()]);
            System.out.println("modelMap = " + modelMap.toString());
                    return this.showBoard(modelMap, page, size);
     //   }
    }
    @RequestMapping("/deleteBoard")
    public String deleteBoard(@RequestParam("id") Long id, ModelMap modelMap,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {

        if (boardService.deleteById(id)) {
            modelMap.addAttribute("type", "danger");
            modelMap.addAttribute("msg", "Membre de supprimé!");
        } else {
            modelMap.addAttribute("type", "danger");
            modelMap.addAttribute("msg", "About non supprimée : Id non trouvé");
        }
        return this.showBoard(modelMap, page, size);

    }
    @RequestMapping("/modifierBoard")
    public String showUpdateAbout(@RequestParam("id") Long id, ModelMap modelMap,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size) {
        Board Board = boardService.getBoard(id);
        modelMap.addAttribute("Board", Board);
        modelMap.addAttribute("edit", true);
        modelMap.addAttribute("pages",
                new int[boardService.getAllBoardsParPage(page, size).getTotalPages()]);
        
                return "createBoard";
    }

    @RequestMapping("/updateBoard")
    public String updateAbout(ModelMap modelMap,
            @RequestParam(name = "photo") MultipartFile multipartFile,
            @Valid Board Board,
            BindingResult bindingResult,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size,
            HttpServletRequest request) throws IOException {
        // if (bindingResult.hasErrors()) {
        //     modelMap.addAttribute("org.springframework.validation.BindingResult.Board", bindingResult);
        //     modelMap.addAttribute("Board", Board);
        //     modelMap.addAttribute("edit", true);
        //     return "createBoard";
        // } else {
            Board board = new Board();
            board.setId(Board.getId());
            board.setName(Board.getName());
            board.setClub(Board.getClub());
            board.setPost(Board.getPost());
            if (multipartFile.getSize() != 0) {
                board.setPhoto(new String(Base64.encodeBase64(multipartFile.getBytes())));
            } else {
                board.setPhoto(boardService.getBoard(board.getId()).getPhoto());
            }
           // board.setPhoto(photo);
            boardService.save(board);
            
            modelMap.addAttribute("pages",
                    new int[boardService.getAllBoardsParPage(page, size).getTotalPages()]);
            modelMap.addAttribute("type", "warning");
            modelMap.addAttribute("msg", "Demande de creation de club modifiée avec succès");
            return this.showBoard(modelMap, page, size);
      //  }
    }

}

