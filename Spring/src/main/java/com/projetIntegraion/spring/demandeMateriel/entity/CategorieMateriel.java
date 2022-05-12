package com.projetIntegraion.spring.demandeMateriel.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class CategorieMateriel {
	@Id @GeneratedValue 
	private  Long id;
	@NotNull
	@Size(min = 3, max = 30)
	private String titre;
	@OneToMany(mappedBy = "categorie")
	private List<Materiel> materiel;
	public CategorieMateriel( @NotNull @Size(min = 3, max = 30) String titre) {
		super();
		this.titre = titre;
	}
	
	public CategorieMateriel() {
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
	@Override
	public String toString() {
		return "CategorieMateriel [id=" + id + ", titre=" + titre + "]";
	}
	
	
	
}
