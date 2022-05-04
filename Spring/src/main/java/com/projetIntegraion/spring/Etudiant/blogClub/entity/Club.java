package com.projetIntegraion.spring.Etudiant.blogClub.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.User;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Club {
    private @Id @GeneratedValue Long id;
    private String nomClub;
    @NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@PastOrPresent
	private Date dateCreation;
    @ManyToOne
    private User responsableClub;
    private String president;
    private String vicePresident;
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
    @Override
    public String toString() {
        return "Club [dateCreation=" + dateCreation + ", id=" + id + ", nomClub=" + nomClub + ", president=" + president
                + ", responsableClub=" + responsableClub + ", vicePresident=" + vicePresident + "]";
    }
    
    
    
}
