package com.projetIntegraion.spring;

import java.util.Date;

import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.DemandeAdhesionClub;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.DemandeCreationClub;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.Role;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.User;
import com.projetIntegraion.spring.blogClub.service.ActivitiesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.projetIntegraion.spring.Etudiant.demandeCreationClub.repository.DemandeCreationClubRepository;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.repository.RoleRepository;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.repository.UserRepository;
import com.projetIntegraion.spring.administrateur.departement.entity.Departement;
import com.projetIntegraion.spring.administrateur.departement.repository.DepartementRepository;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	ActivitiesService service;
	@Autowired
	RoleRepository roleRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	DepartementRepository departementRepository;

	@Autowired
	DemandeCreationClubRepository demandeCreationClubRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// if (userRepository.findAll().size() != 0) {
		// List<User> users = userRepository.findAll();
		// for (User user : users) {
		// userRepository.deleteById(user.getId());
		// }
		// }
		// if (roleRepository.findAll().size() != 0) {
		// List<Role> roles = roleRepository.findAll();
		// for (Role role : roles) {
		// System.out.println(role.getId());
		// roleRepository.deleteById(role.getId());
		// }
		// }

		Role role = new Role("ROLE_ADMIN");
		Role role1 = new Role("ROLE_RESPONSABLE");
		Role role2 = new Role("ROLE_STUDENT");
		Role role3 = new Role("ROLE_CHEFDEPARTEMENT");
		roleRepository.save(role1);
		roleRepository.save(role2);
		roleRepository.save(role);
		roleRepository.save(role3);
		Departement departement = new Departement();
		departement.setTitre("Department Informatique");
		Departement departement1 = new Departement();
		departement1.setTitre("Department Electronique");
		Departement departement2 = new Departement();
		departement2.setTitre("Department Mechanique");

		departementRepository.save(departement);
		departementRepository.save(departement1);
		departementRepository.save(departement2);


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

		User student1 = new User();
		student1.setUsername("student1@example.com");
		student1.setPassword(new BCryptPasswordEncoder().encode("password"));
		student1.getRoles().add(role2);

		userRepository.save(admin);
		userRepository.save(responsable);
		userRepository.save(student);
		userRepository.save(student1);

		DemandeCreationClub dcc = new DemandeCreationClub();
		dcc.setNomClub("Club de l'informatique");
		dcc.setLogo(
				"AAABAAEAICAAAAEAIACoEAAAFgAAACgAAAAgAAAAQAAAAAEAIAAAAAAAABAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAg7hBAIO4QQCDuEEAg7hBAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAIO4QQCDuEEAg7hBM4O4QTODuEEAg7hBAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAg7hBAIO4QQuDuEGxg7hBsYO4QQuDuEEAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAIO4QQCDuEEAg7hBWoO4QfeDuEH3g7hBWoO4QQCDuEEAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAg7hBAIO4QRqDuEHJg7hB/4O4Qf+DuEHJg7hBGoO4QQAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAIO4QQCDuEEAg7hBeoO4Qf6DuEH/g7hB/4O4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");
		Date date = new Date(System.currentTimeMillis() - 1000000000);
		dcc.setDateCreation(date);
		dcc.setActivite("activite");
		dcc.setPresident("President");
		dcc.setVicePresident("VicePresident");
		dcc.setResponsableClub(student);

		demandeCreationClubRepository.save(dcc);

	}

	// @Bean
	// public PasswordEncoder getBCPE() {
	// return new BCryptPasswordEncoder();
	// }

}
