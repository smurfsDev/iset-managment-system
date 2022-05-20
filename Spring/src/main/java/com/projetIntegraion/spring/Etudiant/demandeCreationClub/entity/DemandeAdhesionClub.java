package com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.projetIntegraion.spring.blogClub.entity.Club;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
@Entity
public class DemandeAdhesionClub {
    private @Id @GeneratedValue Long id;
    private int status;
    @NotNull
	private String message;
    @ManyToOne
	private User etudiant;
	@ManyToOne
	private Club club;

    public DemandeAdhesionClub() {
    }

    public DemandeAdhesionClub(Long id, int status, String message, User etudiant, Club club) {
        this.id = id;
        this.status = status;
        this.message = message;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    @Override
    public String toString() {
        return "DemandeAdhesionClub [club=" + club + ", etudiant=" + etudiant + ", id=" + id + ", message=" + message
                + ", status=" + status + "]";
    }
    
    

    
    
    
    
}
