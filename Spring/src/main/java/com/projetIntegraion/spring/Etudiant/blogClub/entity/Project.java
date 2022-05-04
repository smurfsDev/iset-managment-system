package com.projetIntegraion.spring.Etudiant.blogClub.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    private String affiche;
    @ManyToOne
    private Club club;
    public Project(Long id, String affiche, Club club) {
        this.id = id;
        this.affiche = affiche;
        this.club = club;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getAffiche() {
        return affiche;
    }
    public void setAffiche(String affiche) {
        this.affiche = affiche;
    }
    public Club getClub() {
        return club;
    }
    public void setClub(Club club) {
        this.club = club;
    }
    @Override
    public String toString() {
        return "Project [club=" + club + ", id=" + id + ", affiche=" + affiche + "]";
    }
    

}
