package com.projetIntegraion.spring.demandePlannificationEvent.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.User;
import com.projetIntegraion.spring.blogClub.entity.Club;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class DemandeEvent {

    private @Id @GeneratedValue Long id;
    
    @ManyToOne
	private User responsableClub;
	@ManyToOne
	private User admin; 
    @ManyToOne
	private Club club; 
    @NotNull
    @Size(min = 3, max = 30)
    private String nomEvent;
    @NotNull
    private String description;
    private int status;
    @FutureOrPresent
    private Date DateEvent;
    @CreationTimestamp
    private Timestamp created_at;
    @UpdateTimestamp
    private Timestamp updated_at;
    
    public DemandeEvent() {
    }
    
    @Override
    public String toString() {
        return "DemandeEvent [admin=" + admin + ", club=" + club + ", created_at=" + created_at + ", description="
                + description + ", id=" + id + ", nomEvent=" + nomEvent + ", responsableClub=" + responsableClub
                + ", status=" + status + ", updated_at=" + updated_at + "]";
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public User getResponsableClub() {
        return responsableClub;
    }
    public void setResponsableClub(User responsableClub) {
        this.responsableClub = responsableClub;
    }
    public User getAdmin() {
        return admin;
    }
    public void setAdmin(User admin) {
        this.admin = admin;
    }
    public Club getClub() {
        return club;
    }
    public void setClub(Club club) {
        this.club = club;
    }
    public String getNomEvent() {
        return nomEvent;
    }
    public void setNomEvent(String nomEvent) {
        this.nomEvent = nomEvent;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public Timestamp getCreated_at() {
        return created_at;
    }
    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }
    public Timestamp getUpdated_at() {
        return updated_at;
    }
    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }
    

}
