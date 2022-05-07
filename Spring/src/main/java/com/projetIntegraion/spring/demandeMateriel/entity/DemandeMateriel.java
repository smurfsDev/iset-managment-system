package com.projetIntegraion.spring.demandeMateriel.entity;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;

import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.User;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

@Entity
public class DemandeMateriel {
	@Id @GeneratedValue 
	private  Long id;
    @Nullable
	private String reponse;
    private int status =0;
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
    private User ResponsableClub;
    @ManyToOne
    private User Destinataire;
    @NotNull
    private int idCategorie;

    @OneToMany(mappedBy="demandeMateriel", cascade = CascadeType.ALL)
    private List<DemandeMaterielMateriel> materiel;

	public DemandeMateriel(String reponse, int status, @NotNull @FutureOrPresent Date dateEmploi,
			@NotNull @FutureOrPresent Date dateDeRemise, User ResponsableClub, User Destinataire,@NotNull int idCategorie,
			List<DemandeMaterielMateriel> materiel) throws ParseException {
		super();
	}
    public DemandeMateriel() {
        super();
    }

    
    public void setId(Long id) {
		this.id = id;
	}
	public int getIdCategorie() {
		return idCategorie;
	}

	public void setIdCategorie(int idCategorie) {
		this.idCategorie = idCategorie;
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
	public User getResponsableClub() {
		return ResponsableClub;
	}
	public void setResponsableClub(User ResponsableClub) {
		this.ResponsableClub = ResponsableClub;
	}
	public User getDestinataire() {
		return Destinataire;
	}
	public void setDestinataire(User Destinataire) {
		this.Destinataire = Destinataire;
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
				+ dateEmploi + ", dateDeRemise=" + dateDeRemise + ", ResponsableClub=" + ResponsableClub
				+ ", Destinataire=" + Destinataire + ", materiel=" + materiel + "]";
	}
    

}
