package com.projetIntegraion.spring.demandePlannificationEvent.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.User;
import com.projetIntegraion.spring.blogClub.entity.Club;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;

@Entity
public class DemandeAdhesionEvent {
    private @Id @GeneratedValue Long id;
    private int status;
    // @NotNull
	// @Size(min = 3, max = 30)
	// private String message;
    @ManyToOne
	private User etudiant;
	@ManyToOne
	private Club club;
    public DemandeAdhesionEvent() {
    }
    public DemandeAdhesionEvent(Long id, int status, User etudiant, Club club) {
        this.id = id;
        this.status = status;
        this.etudiant = etudiant;
        this.club = club;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public User getEtudiant() {
        return etudiant;
    }
    public void setEtudiant(User etudiant) {
        this.etudiant = etudiant;
    }
    public Club getClub() {
        return club;
    }
    public void setClub(Club club) {
        this.club = club;
    }

    

}
