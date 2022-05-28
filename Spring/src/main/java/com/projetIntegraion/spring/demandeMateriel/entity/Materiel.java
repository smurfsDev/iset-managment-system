package com.projetIntegraion.spring.demandeMateriel.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.User;

@Entity
public class Materiel {
	@Id
	@GeneratedValue
	private Long id;
	@NotNull
	@Size(min = 3, max = 30)
	private String titre;
	@NotNull
	@PositiveOrZero
	private int quantite;
	@NotNull
	@Size(min = 3, max = 150)
	private String description;
	@ManyToOne
	private User responsable;
	@ManyToOne
	private CategorieMateriel categorie;

	@OneToMany(mappedBy = "materiel", cascade = CascadeType.ALL)
	private List<DemandeMaterielMateriel> demandeMateriel;

	public Materiel(@NotNull @Size(min = 3, max = 30) String titre, @NotNull int quantite,
			@NotNull @Size(min = 3, max = 150) String description) {
		super();

	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getResponsable() {
		return responsable;
	}

	public void setResponsable(User responsable) {
		this.responsable = responsable;
	}

	public CategorieMateriel getCategorie() {
		return categorie;
	}

	public void setCategorie(CategorieMateriel categorie) {
		this.categorie = categorie;
	}

	public List<DemandeMaterielMateriel> getDemandeMateriel() {
		return demandeMateriel;
	}

	public void setDemandeMateriel(List<DemandeMaterielMateriel> demandeMateriel) {
		this.demandeMateriel = demandeMateriel;
	}

	public Long getId() {
		return id;
	}

	public Materiel() {
		super();
	}

	@Override
	public String toString() {
		return "Materiel [id=" + id + ", titre=" + titre + ", quantite=" + quantite + ", description=" + description
				+ ", responsable=" + responsable + ", categorie=" + categorie + ", demandeMateriel=" + demandeMateriel
				+ "]";
	}

}
