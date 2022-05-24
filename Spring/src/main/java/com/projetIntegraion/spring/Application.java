package com.projetIntegraion.spring;



import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.Role;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.service.RoleService;
import com.projetIntegraion.spring.blogClub.service.ActivitiesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Application implements CommandLineRunner {



	// public static void main(String[] args) {
	// 	SpringApplication.run(Application.class, args);
	// }
	// @Autowired
	// private ClubService service;
	// @Autowired
	// private UserService userSer;
	@Autowired
	ActivitiesService service;
	@Autowired
	RoleService roleService;
	public static void main(String[] args) {
	SpringApplication.run(Application.class, args);
	// Role r = new Role("ADMIN");
	// 	roleService.save(r);
//	System.out.println(service.getAllActivities());
		//System.out.println("Act"+service.getAllActivitiesParPage(page, size))
	}
	@Override
	public void run(String... args) throws Exception {
		Role r = new Role("ADMIN");
		roleService.save(r);
		Role r1 = new Role("STUDENT");
		roleService.save(r1);
		Role r2 = new Role("RESPONSABLE");
		roleService.save(r2);
	// TODO Auto-generated method stub
	// User user = new User();
	// userSer.save(user);
	// Club c1 = new Club(1L,"nomClub", new Date(), user, "president", "vicePresident");
	
	// service.save(c1);
	// Club c = null;
	// c= service.getClub(3L);
	// System.out.println(c);
	
	// service.saveProduit(prod2);
	// service.saveProduit(prod3);
	} 


	
	// @Bean
	//  public PasswordEncoder getBCPE()  {
	//  return new BCryptPasswordEncoder();
	//  }
	 
	
}
