package com.projetIntegraion.spring.security;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.projetIntegraion.spring.Etudiant.demandeCreationClub.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

class UserForm{
    private String username;
    private String password;
    private String confirmedPassword;
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getConfirmedPassword() {
        return confirmedPassword;
    }
    public void setConfirmedPassword(String confirmedPassword) {
        this.confirmedPassword = confirmedPassword;
    }
    
} 
@Controller
public class SecurityController {
    @Autowired
    UserService userService;
@GetMapping("/accessDenied")
public String geterror() {
 return "accessDenied";
 }

@PostMapping("/accessDenied")
public String posterror() {
 return "accessDenied";
 }

 @GetMapping("/login")
 public String login(ModelMap modelMap)
 {
    modelMap.addAttribute("login", true);
 return "login";
 } 
 @GetMapping("/logout")
 public String logout(HttpServletRequest request) throws ServletException
 {
 request.logout();
 return "redirect:/login";
 }

 @GetMapping("/register")
 public String register(ModelMap modelMap) {
   modelMap.addAttribute("user", new UserForm());
   modelMap.addAttribute("login", false);
   System.out.println("logiiin : "+modelMap.getAttribute("login"));
   return "login";
 }
 @PostMapping("/register")
 public String register(@Valid UserForm user,
   BindingResult bindingResult,
   ModelMap modelMap) {
   if (bindingResult.hasErrors()) {
     return "register";
   }
  // UserForm userForm = new UserForm();
//    userForm.setUsername(user.getUsername());
//    userForm.setPassword(user.getPassword());
//    userForm.setConfirmedPassword(user.getPassword());
   userService.saveUser(user.getUsername(),user.getPassword(),user.getConfirmedPassword());
   modelMap.addAttribute("login", true);
   return "login";
 }
} 
