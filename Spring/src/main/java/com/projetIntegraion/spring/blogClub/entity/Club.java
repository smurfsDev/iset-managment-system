package com.projetIntegraion.spring.blogClub.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.User;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Club {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private  Long id;
    private String nomClub;
    @NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@PastOrPresent
	private Date dateCreation;
    @ManyToOne
    private User responsableClub;
    private String president;
    private String vicePresident;@CreationTimestamp
    private Timestamp created_at;
    @UpdateTimestamp
    private Timestamp updated_at;

    public Club(Long id, String nomClub, @NotNull @PastOrPresent Date dateCreation, User responsableClub,
            String president, String vicePresident) {
        this.id = id;
        this.nomClub = nomClub;
        this.dateCreation = dateCreation;
        this.responsableClub = responsableClub;
        this.president = president;
        this.vicePresident = vicePresident;
   
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNomClub() {
        return nomClub;
    }
    public void setNomClub(String nomClub) {
        this.nomClub = nomClub;
    }
    public Date getDateCreation() {
        return dateCreation;
    }
    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }
    public User getResponsableClub() {
        return responsableClub;
    }
    public void setResponsableClub(User responsableClub) {
        this.responsableClub = responsableClub;
    }
    public String getPresident() {
        return president;
    }
    public void setPresident(String president) {
        this.president = president;
    }
    public String getVicePresident() {
        return vicePresident;
    }
    public void setVicePresident(String vicePresident) {
        this.vicePresident = vicePresident;
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
    public Club() {
    }
    @Override
    public String toString() {
        return "Club [id=" + id + ", nomClub=" + nomClub + ", dateCreation=" + dateCreation + ", responsableClub="
                + responsableClub + ", president=" + president + ", vicePresident=" + vicePresident + ", created_at="
                + created_at + ", updated_at=" + updated_at + "]";
    }
    
    
    
}
