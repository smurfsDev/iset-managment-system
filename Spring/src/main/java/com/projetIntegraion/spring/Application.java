package com.projetIntegraion.spring;

import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.Role;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.User;
import com.projetIntegraion.spring.blogClub.service.ActivitiesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.repository.RoleRepository;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.repository.UserRepository;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	ActivitiesService service;
	@Autowired
	RoleRepository roleRepository;

	@Autowired
	UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// if (userRepository.findAll().size() != 0) {
		// 	List<User> users = userRepository.findAll();
		// 	for (User user : users) {
		// 		userRepository.deleteById(user.getId());
		// 	}
		// }
		// if (roleRepository.findAll().size() != 0) {
		// 	List<Role> roles = roleRepository.findAll();
		// 	for (Role role : roles) {
		// 		System.out.println(role.getId());
		// 		roleRepository.deleteById(role.getId());
		// 	}
		// }


		Role role = new Role("ROLE_ADMIN");
		Role role1 = new Role("ROLE_RESPONSABLE");
		Role role2 = new Role("ROLE_STUDENT");
		roleRepository.save(role1);
		roleRepository.save(role2);
		roleRepository.save(role);

		User admin = new User();
		admin.setUsername("admin@example.com");
		admin.setPassword(new BCryptPasswordEncoder().encode("password"));
		admin.getRoles().add(role);

		User responsable = new User();
		responsable.setUsername("responsable@example.com");
		responsable.setPassword(new BCryptPasswordEncoder().encode("password"));
		responsable.getRoles().add(role1);

		User student = new User();
		student.setUsername("student@example.com");
		student.setPassword(new BCryptPasswordEncoder().encode("password"));
		student.getRoles().add(role2);

		userRepository.save(admin);
		userRepository.save(responsable);
		userRepository.save(student);

	}

	// @Bean
	// public PasswordEncoder getBCPE() {
	// return new BCryptPasswordEncoder();
	// }

}
