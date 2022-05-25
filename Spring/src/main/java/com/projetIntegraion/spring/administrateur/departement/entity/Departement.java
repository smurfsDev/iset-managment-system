package com.projetIntegraion.spring.administrateur.departement.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.User;
import com.projetIntegraion.spring.demandeSalle.entity.Salle;

@Entity
public class Departement {

	@Id
	@GeneratedValue
	private Long id;
	@NotNull
	@Size(min = 3, max = 30)
	private String titre;
	@OneToOne
	private User ChefDepartment;
	@OneToMany(mappedBy = "departement")
	private List<Salle> Salle;

	public Departement() {
		super();
	}

	public Departement(String titre, User chefDepartment, List<Salle> salle) {
		super();

		this.titre = titre;
		ChefDepartment = chefDepartment;
		Salle = salle;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public User getChefDepartment() {
		return ChefDepartment;
	}

	public void setChefDepartment(User chefDepartment) {
		ChefDepartment = chefDepartment;
	}

	public List<Salle> getSalle() {
		return Salle;
	}

	public void setSalle(List<Salle> salle) {
		Salle = salle;
	}

	@Override
	public String toString() {
		return "Departement [id=" + id + ", titre=" + titre + ", ChefDepartment=" + ChefDepartment + ", Salle=" + Salle
				+ "]";
	}

}
