package com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;

@Entity
public class Matiere {

	private @Id @GeneratedValue Long id;

    @NotNull
    private int coefficient;
    @NotNull
	@Size(min = 3, max = 30)
    private String nom;
    @NotNull
    private double semestre;
    @ManyToOne
    private Classe classe;
    @ManyToOne
    private User enseignant;
    public Matiere() {
    }
    public Matiere(Long id, int coefficient, @Size(min = 3, max = 30) String nom, double semestre, Classe classe,
            User enseignant) {
        this.id = id;
        this.coefficient = coefficient;
        this.nom = nom;
        this.semestre = semestre;
        this.classe = classe;
        this.enseignant = enseignant;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getCoefficient() {
        return coefficient;
    }
    public void setCoefficient(int coefficient) {
        this.coefficient = coefficient;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public double getSemestre() {
        return semestre;
    }
    public void setSemestre(double semestre) {
        this.semestre = semestre;
    }
    public Classe getClasse() {
        return classe;
    }
    public void setClasse(Classe classe) {
        this.classe = classe;
    }
    public User getEnseignant() {
        return enseignant;
    }
    public void setEnseignant(User enseignant) {
        this.enseignant = enseignant;
    }

    


    

    
}
