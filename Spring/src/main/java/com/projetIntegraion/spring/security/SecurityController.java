package com.projetIntegraion.spring.security;

import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.Classe;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.Role;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.User;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.UserRole;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.repository.ClasseRepository;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.repository.RoleRepository;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.repository.UserRepository;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.repository.UserRoleRepository;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.service.RoleService;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.service.UserService;
import com.projetIntegraion.spring.administrateur.departement.entity.Departement;
import com.projetIntegraion.spring.administrateur.departement.repository.DepartementRepository;
import com.projetIntegraion.spring.annotation.equalPasswordsConstraint;
import com.projetIntegraion.spring.annotation.uniqueConstraint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@equalPasswordsConstraint.List({
        @equalPasswordsConstraint(field = "password", fieldMatch = "confirmedPassword", message = "Les mots de passe ne sont pas identiques!")
})

class UserForm {
    @NotNull
    @Email
    @uniqueConstraint
    private String username;
    @NotNull
    @Size(min = 8, max = 15)
    private String password;
    @NotNull
    @Size(min = 8, max = 15)
    private String confirmedPassword;

    public UserForm(@NotNull String username, @NotNull @Size(min = 8, max = 15) String password,
            @NotNull String confirmedPassword) {
        this.username = username;
        this.password = password;
        this.confirmedPassword = confirmedPassword;
    }

    public UserForm() {
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmedPassword() {
        return this.confirmedPassword;
    }

    public void setConfirmedPassword(String confirmedPassword) {
        this.confirmedPassword = confirmedPassword;
    }

}

@Controller
public class SecurityController {
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DepartementRepository departementRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private ClasseRepository classeRepository;

    @GetMapping("/accessDenied")
    public String geterror() {
        return "accessDenied";
    }

    @PostMapping("/accessDenied")
    public String posterror() {
        return "accessDenied";
    }

    @GetMapping("/login")
    public String login(ModelMap modelMap,
            @RequestParam(name = "error", defaultValue = "0") int error,
            @RequestParam(name = "logout", defaultValue = "0") int logout) {
        if (error == 1) {
            System.out.println("error : " + error);
            modelMap.addAttribute("error", "Veuillez vérifier vos identifiants");
            return "login";
        }
        if (error==2) {
            modelMap.addAttribute("error", "Votre compte n'est pas encore activé");
            return "login";
        }
        if (logout == 1) {
            System.out.println("error : " + error);

            modelMap.addAttribute("error", "Vous avez été déconnecté");
            return "login";
        }
        System.out.println("error : " + error);

        return "login";
    }

    @GetMapping("/tesa")
    public String tesa(HttpServletRequest request) {
        return userRepository.findUserWithName(request.getUserPrincipal().getName()).get().getRoles().iterator().next().toString();
        // get first element of a set

        // return userRepository.findUserWithName(request.getUserPrincipal().getName()).get().getId().toString();
    }

    // @GetMapping("/login")
    // public String login(ModelMap modelMap)
    // {
    // modelMap.addAttribute("login", true);
    // // List<Role> roles ;
    // // roles = roleService.getAllRole();
    // // modelMap.addAttribute("roles", roles);

    // return "login";
    // }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) throws ServletException {
        request.logout();
        return "redirect:/login";
    }

    @GetMapping("/register")
    public String showRegister(ModelMap modelMap) {
        List<Role> roles = roleRepository.findAll();
        List<Departement> departements = departementRepository.findAll();
        List<Classe> classes = classeRepository.findAll();
        modelMap.addAttribute("roles", roles);
        System.out.println("deps : " + departements);
        modelMap.addAttribute("deps", departements);
        modelMap.addAttribute("classes", classes);
        modelMap.addAttribute("userForm", new UserForm());
        modelMap.addAttribute("reg", true);

        return "login";
    }

    @PostMapping("/register")
    public String register(@Valid UserForm userForm, BindingResult bindingResult, Long role,int dep,int classe, ModelMap modelMap) {
        if (bindingResult.hasErrors()) {
            List<Role> roles = roleRepository.findAll();
            List<Departement> departements = departementRepository.findAll();
            List<Classe> classes = classeRepository.findAll();
            modelMap.addAttribute("deps", departements);
            System.out.println(roles);
            modelMap.addAttribute("roles", roles);
            modelMap.addAttribute("classes", classes);    
            modelMap.addAttribute("reg", true);
            modelMap.addAttribute("userForm", userForm);
            return "login";
        }
        User su = userService.saveUser(userForm.getUsername(), userForm.getPassword(), userForm.getConfirmedPassword(), role);

        if (role==4) {
            UserRole ur = userRoleRepository.findByRoleIdAndUserId(role, su.getId()).get();
            ur.setDepartement(dep);
            userRoleRepository.save(ur);
        }
        if (role==3) {
            UserRole ur = userRoleRepository.findByRoleIdAndUserId(role, su.getId()).get();
            ur.setDepartement(dep);
            ur.setClasse(classe);
            userRoleRepository.save(ur);
        }

        return "redirect:/login";
    }
    // @GetMapping("/register")
    // public String register(ModelMap modelMap) {
    // modelMap.addAttribute("user", new UserForm());
    // // modelMap.addAttribute("r", new Role());
    // modelMap.addAttribute("login", false);
    // // System.out.println("logiiin : "+modelMap.getAttribute("login"));
    // // List<Role> roles ;
    // // roles = roleService.getAllRole();
    // // modelMap.addAttribute("roles", roles);
    // List<Role> roles = roleRepository.findAll();
    // modelMap.addAttribute("roles", roles);
    // return "login";
    // }
    // @PostMapping("/register")
    // public String register(@Valid UserForm user,Long role,
    // BindingResult bindingResult,
    // ModelMap modelMap) {
    // if (bindingResult.hasErrors()) {
    // List<Role> roles = roleRepository.findAll();
    // System.out.println(roles);
    // modelMap.addAttribute("roles", roles);
    // return "register";
    // }
    // // UserForm userForm = new UserForm();
    // // userForm.setUsername(user.getUsername());
    // // userForm.setPassword(user.getPassword());
    // // userForm.setConfirmedPassword(user.getPassword());
    // userService.saveUser(user.getUsername(),user.getPassword(),user.getConfirmedPassword(),role);

    // //Role r1 = roleService.getRole(r.getId());
    // // System.out.println(r1.getName() );
    // modelMap.addAttribute("login", true);
    // return "login";
    // }
}
