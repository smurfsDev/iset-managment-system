package com.projetIntegraion.spring;

import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.Role;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.User;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.service.RoleService;
import com.projetIntegraion.spring.blogClub.service.ActivitiesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.repository.RoleRepository;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.repository.UserRepository;
import java.util.List;

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
		if (roleRepository.findAll().size() != 0) {
			List<Role> roles = roleRepository.findAll();
			for (Role role : roles) {
				System.out.println(role.getId());
				roleRepository.deleteById(role.getId());
			}
		}

		if (userRepository.findAll().size() != 0) {
			List<User> users = userRepository.findAll();
			for (User user : users) {
				userRepository.deleteById(user.getId());
			}
		}

		Role role = new Role("ROLE_ADMIN");
		Role role1 = new Role("ROLE_RESPONSABLE");
		Role role2 = new Role("ROLE_STUDENT");
		roleRepository.save(role1);
		roleRepository.save(role2);
		roleRepository.save(role);

	}

	// @Bean
	// public PasswordEncoder getBCPE() {
	// return new BCryptPasswordEncoder();
	// }

}
