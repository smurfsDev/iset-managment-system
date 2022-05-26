package com.projetIntegraion.spring;

import java.util.ArrayList;
import java.util.List;

import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.Role;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.User;
import com.projetIntegraion.spring.blogClub.service.ActivitiesService;
import com.projetIntegraion.spring.demandeMateriel.entity.CategorieMateriel;
import com.projetIntegraion.spring.demandeMateriel.entity.DemandeMaterielMateriel;
import com.projetIntegraion.spring.demandeMateriel.entity.Materiel;
import com.projetIntegraion.spring.demandeMateriel.repository.CategorieMaterielRepository;
import com.projetIntegraion.spring.demandeMateriel.repository.MaterielRepository;
import com.projetIntegraion.spring.demandeSalle.entity.Salle;
import com.projetIntegraion.spring.demandeSalle.repository.SalleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	SalleRepository salleRepository;
	@Autowired
	CategorieMaterielRepository categorieMaterielRepository;
	@Autowired
	MaterielRepository materielRepository;

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
		Role role3 = new Role("ROLE_CHEF_DEPARTEMENT");

		roleRepository.save(role1);
		roleRepository.save(role2);
		roleRepository.save(role);
		roleRepository.save(role3);

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

		User cdi = new User();
		cdi.setUsername("cdi@example.com");
		cdi.setPassword(new BCryptPasswordEncoder().encode("password"));
		cdi.getRoles().add(role1);
		User cdm = new User();
		cdm.setUsername("cdm@example.com");
		cdm.setPassword(new BCryptPasswordEncoder().encode("password"));
		cdm.getRoles().add(role1);
		User cde = new User();
		cde.setUsername("cde@example.com");
		cde.setPassword(new BCryptPasswordEncoder().encode("password"));
		cde.getRoles().add(role1);

		userRepository.save(admin);
		userRepository.save(responsable);
		userRepository.save(student);
		userRepository.save(cdi);
		userRepository.save(cdm);
		userRepository.save(cde);

		Departement dep1 = new Departement("Département Informatique", cdi, new ArrayList<Salle>());
		Departement dep2 = new Departement("Département Mathématique", cdm, new ArrayList<Salle>());
		Departement dep3 = new Departement("Département Electrique", cde, new ArrayList<Salle>());
		departementRepository.save(dep1);
		departementRepository.save(dep2);
		departementRepository.save(dep3);

		Salle salle = new Salle("salle1", dep1);
		Salle salle2 = new Salle("salle2", dep1);
		Salle salle3 = new Salle("salle3", dep2);
		Salle salle4 = new Salle("salle4", dep2);
		Salle salle5 = new Salle("salle5", dep3);
		Salle salle6 = new Salle("salle6", dep3);
		salleRepository.save(salle);
		salleRepository.save(salle2);
		salleRepository.save(salle3);
		salleRepository.save(salle4);
		salleRepository.save(salle5);
		salleRepository.save(salle6);

		CategorieMateriel cat1 = new CategorieMateriel("informatique");
		CategorieMateriel cat2 = new CategorieMateriel("bureautique");
		CategorieMateriel cat3 = new CategorieMateriel("robotique");
		categorieMaterielRepository.save(cat1);
		categorieMaterielRepository.save(cat2);
		categorieMaterielRepository.save(cat3);

		Materiel mat1 = new Materiel("ordinateur", 12, "aaaaaaaa", cdi, cat1, new ArrayList<DemandeMaterielMateriel>());
		Materiel mat2 = new Materiel("imprimante", 12, "aaaaaaaa", cdi, cat1, new ArrayList<DemandeMaterielMateriel>());
		Materiel mat3 = new Materiel("scanner", 12, "aaaaaaaa", cde, cat2, new ArrayList<DemandeMaterielMateriel>());
		Materiel mat4 = new Materiel("tablette", 12, "aaaaaaaa", cde, cat2, new ArrayList<DemandeMaterielMateriel>());
		Materiel mat5 = new Materiel("robot", 12, "aaaaaaaa", cdm, cat3, new ArrayList<DemandeMaterielMateriel>());
		Materiel mat6 = new Materiel("aaa", 12, "aaaaaaaa", cdm, cat3, new ArrayList<DemandeMaterielMateriel>());
		materielRepository.save(mat1);
		materielRepository.save(mat2);
		materielRepository.save(mat3);
		materielRepository.save(mat4);
		materielRepository.save(mat5);
		materielRepository.save(mat6);

	}

	// @Bean
	// public PasswordEncoder getBCPE() {
	// return new BCryptPasswordEncoder();
	// }

}
