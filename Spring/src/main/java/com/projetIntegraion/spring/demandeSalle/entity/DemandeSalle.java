package com.projetIntegraion.spring.demandeSalle.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;

import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.User;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

@Entity
public class DemandeSalle {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @FutureOrPresent
    private Date dateEmploi;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @FutureOrPresent
    private Date dateDeRemise;
    @OneToOne
    private User responsable;
    @Nullable
    private String reponse;
    private int status = 0;
    @OneToMany(mappedBy = "demandeSalle", cascade = CascadeType.ALL)
    private List<DemandeSalleSalle> Salle;

    public DemandeSalle() {
        super();
    }

    public DemandeSalle(Date dateEmploi, Date dateDeRemise, User responsable, String reponse, int status,
            List<DemandeSalleSalle> salle) {
        super();
        this.dateEmploi = dateEmploi;
        this.dateDeRemise = dateDeRemise;
        this.responsable = responsable;
        this.reponse = reponse;
        this.status = status;
        Salle = salle;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateEmploi() {
        return dateEmploi;
    }

    public void setDateEmploi(Date dateEmploi) {
        this.dateEmploi = dateEmploi;
    }

    public Date getDateDeRemise() {
        return dateDeRemise;
    }

    public void setDateDeRemise(Date dateDeRemise) {
        this.dateDeRemise = dateDeRemise;
    }

    public User getResponsable() {
        return responsable;
    }

    public void setResponsable(User responsable) {
        this.responsable = responsable;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public List<DemandeSalleSalle> getSalle() {
        return Salle;
    }

    public void setSalle(List<DemandeSalleSalle> salle) {
        Salle = salle;
    }

    @Override
    public String toString() {
        return "DemandeSalle [id=" + id + ", dateEmploi=" + dateEmploi + ", dateDeRemise=" + dateDeRemise
                + ", responsable=" + responsable + ", reponse=" + reponse + ", Salle=" + Salle + "]";
    }

}
