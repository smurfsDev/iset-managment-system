package com.projetIntegraion.spring.demandeMateriel.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class CategorieMateriel {
	private @Id @GeneratedValue Long id;
	@NotNull
	@Size(min = 3, max = 30)
	private String titre;
	public CategorieMateriel( @NotNull @Size(min = 3, max = 30) String titre) {
		super();
		this.titre = titre;
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
