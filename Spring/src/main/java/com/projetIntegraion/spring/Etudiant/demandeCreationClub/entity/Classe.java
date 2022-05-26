package com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.projetIntegraion.spring.administrateur.departement.entity.Departement;


@Entity
public class Classe {
    private @Id @GeneratedValue Long id;
    @NotNull
	@Size(min = 3, max = 50)
	private String abreviation;

    @NotNull
	@Size(min = 3, max = 50)
	private String nom;

    @OneToOne
    private Departement departement;



    public Classe() {
    }


    public Classe(Long id, String abreviation, String nom, Departement departement) {
        this.id = id;
        this.abreviation = abreviation;
        this.nom = nom;
        this.departement = departement;
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAbreviation() {
        return this.abreviation;
    }

    public void setAbreviation(String abreviation) {
        this.abreviation = abreviation;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Departement getDepartement() {
        return this.departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", abreviation='" + getAbreviation() + "'" +
            ", nom='" + getNom() + "'" +
            ", departement='" + getDepartement() + "'" +
            "}";
    }



}
