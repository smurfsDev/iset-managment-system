package com.projetIntegraion.spring;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {



	// public static void main(String[] args) {
	// 	SpringApplication.run(Application.class, args);
	// }
	// @Autowired
	// private ClubService service;
	// @Autowired
	// private UserService userSer;
	
	public static void main(String[] args) {
	SpringApplication.run(Application.class, args);
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
