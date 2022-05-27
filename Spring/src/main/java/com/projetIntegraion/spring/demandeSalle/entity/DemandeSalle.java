package com.projetIntegraion.spring.demandeSalle.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

@Entity
public class DemandeSalle {
    @Id @GeneratedValue 
	private  Long id;
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
    private Long idResponsable;
    @Nullable
    private String reponse;
    @OneToMany(mappedBy="demandeSalle", cascade = CascadeType.ALL)
    private List<DemandeSalleSalle> Salle;
    public DemandeSalle() {
        super();
    }
    
    public DemandeSalle(Date dateEmploi, Date dateDeRemise, Long idResponsable, String reponse, List<DemandeSalleSalle> salle) {
        super();
        this.dateEmploi = dateEmploi;
        this.dateDeRemise = dateDeRemise;
        this.idResponsable = idResponsable;
        this.reponse = reponse;
        Salle = salle;
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
    public Long getIdResponsable() {
        return idResponsable;
    }
    public void setIdResponsable(Long idResponsable) {
        this.idResponsable = idResponsable;
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
        return "DemandeSalle [id=" + id + ", dateEmploi=" + dateEmploi + ", dateDeRemise=" + dateDeRemise + ", idResponsable=" + idResponsable + ", reponse=" + reponse + ", Salle=" + Salle + "]";
    }
    
}
