package com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;

@Entity
public class Matiere {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Min(value=1, message="must be equal or greater than 1")  
    private double coefficient;
    @NotNull
	@Size(min = 3, max = 30)
    private String nom;
    @NotNull
    @Min(value=1, message="must be equal or greater than 1")  
    @Max(value=2, message="must be equal or less than 2")  
    private int semestre;
    @ManyToOne
    private Classe classe;
    @ManyToOne
    private User enseignant;
    public Matiere() {
    }
    public Matiere(Long id, double coefficient, @Size(min = 3, max = 30) String nom, int semestre, Classe classe,
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
    public double getCoefficient() {
        return coefficient;
    }
    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public int getSemestre() {
        return semestre;
    }
    public void setSemestre(int semestre) {
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
