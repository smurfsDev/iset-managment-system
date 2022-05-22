package com.projetIntegraion.spring;



import com.projetIntegraion.spring.blogClub.service.ActivitiesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Application {



	// public static void main(String[] args) {
	// 	SpringApplication.run(Application.class, args);
	// }
	// @Autowired
	// private ClubService service;
	// @Autowired
	// private UserService userSer;
	@Autowired
	ActivitiesService service;
	public static void main(String[] args) {
	SpringApplication.run(Application.class, args);
//	System.out.println(service.getAllActivities());
		//System.out.println("Act"+service.getAllActivitiesParPage(page, size))
	}
	
	
	@Bean
	 public PasswordEncoder getBCPE()  {
	 return new BCryptPasswordEncoder();
	 }
	 
	// @Override
	// public void run(String... args) throws Exception {
	// TODO Auto-generated method stub
	// User user = new User();
	// userSer.save(user);
	// Club c1 = new Club(1L,"nomClub", new Date(), user, "president", "vicePresident");
	
	// service.save(c1);
	// Club c = null;
	// c= service.getClub(3L);
	// System.out.println(c);
	
	//service.saveProduit(prod2);
	//service.saveProduit(prod3);
	//} 


}
