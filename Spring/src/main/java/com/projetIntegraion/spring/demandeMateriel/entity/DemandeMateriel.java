package com.projetIntegraion.spring.demandeMateriel.entity;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.User;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

@Entity
public class DemandeMateriel {
	@Id @GeneratedValue (strategy = GenerationType.IDENTITY)
	private  Long id;
    @Nullable
	private String reponse;
    private int status=0;
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
    @ManyToOne
    private User idResponsableClub;
    @ManyToOne
    private User idDestinataire;
    @OneToMany(mappedBy="demandeMateriel", cascade = CascadeType.ALL)
    private List<DemandeMaterielMateriel> materiel;
	public DemandeMateriel(String reponse, int status, @NotNull @FutureOrPresent Date dateEmploi,
			@NotNull @FutureOrPresent Date dateDeRemise, User idResponsableClub, User idDestinataire,
			List<DemandeMaterielMateriel> materiel) {
		super();
		this.reponse = reponse;
		this.status = status;
		this.dateEmploi = dateEmploi;
		this.dateDeRemise = dateDeRemise;
		this.idResponsableClub = idResponsableClub;
		this.idDestinataire = idDestinataire;
		this.materiel = materiel;
	}
	public String getReponse() {
		return reponse;
	}
	public void setReponse(String reponse) {
		this.reponse = reponse;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
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
	public User getIdResponsableClub() {
		return idResponsableClub;
	}
	public void setIdResponsableClub(User idResponsableClub) {
		this.idResponsableClub = idResponsableClub;
	}
	public User getIdDestinataire() {
		return idDestinataire;
	}
	public void setIdDestinataire(User idDestinataire) {
		this.idDestinataire = idDestinataire;
	}
	public List<DemandeMaterielMateriel> getMateriel() {
		return materiel;
	}
	public void setMateriel(List<DemandeMaterielMateriel> materiel) {
		this.materiel = materiel;
	}
	public Long getId() {
		return id;
	}
	@Override
	public String toString() {
		return "DemandeMateriel [id=" + id + ", reponse=" + reponse + ", status=" + status + ", dateEmploi="
				+ dateEmploi + ", dateDeRemise=" + dateDeRemise + ", idResponsableClub=" + idResponsableClub
				+ ", idDestinataire=" + idDestinataire + ", materiel=" + materiel + "]";
	}
    

}
