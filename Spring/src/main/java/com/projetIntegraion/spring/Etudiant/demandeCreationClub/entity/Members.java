package com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.projetIntegraion.spring.blogClub.entity.Club;

@Entity
public class Members {
    private @Id @GeneratedValue Long id;

    @OneToOne
	private User etudiant;
    @OneToOne
    private Club club;


    public Members() {
    }


    public Members(Long id, User etudiant, Club club) {
        this.id = id;
        this.etudiant = etudiant;
        this.club = club;
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getEtudiant() {
        return this.etudiant;
    }

    public void setEtudiant(User etudiant) {
        this.etudiant = etudiant;
    }

    public Club getClub() {
        return this.club;
    }

    public void setClub(Club club) {
        this.club = club;
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", etudiant='" + getEtudiant() + "'" +
            ", club='" + getClub() + "'" +
            "}";
    }
    
}
