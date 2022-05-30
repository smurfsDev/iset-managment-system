package com.projetIntegraion.spring.demandePlannificationEvent.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.User;
import com.projetIntegraion.spring.blogClub.entity.Club;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class DemandeEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
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
    @Size(min = 3, max = 255)
    private String description;
    private int status;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @FutureOrPresent
    private Date dateEvent;
    @CreationTimestamp
    private Timestamp created_at;
    @UpdateTimestamp
    private Timestamp updated_at;
    public DemandeEvent() {
    }
    public DemandeEvent(Long id, User responsableClub, User admin, Club club,
            @NotNull @Size(min = 3, max = 30) String nomEvent, @NotNull String description, int status,
            @NotNull @FutureOrPresent Date dateEvent, Timestamp created_at, Timestamp updated_at) {
        this.id = id;
        this.responsableClub = responsableClub;
        this.admin = admin;
        this.club = club;
        this.nomEvent = nomEvent;
        this.description = description;
        this.status = status;
        this.dateEvent = dateEvent;
        this.created_at = created_at;
        this.updated_at = updated_at;
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
    public Date getDateEvent() {
        return dateEvent;
    }
    public void setDateEvent(Date dateEvent) {
        this.dateEvent = dateEvent;
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
