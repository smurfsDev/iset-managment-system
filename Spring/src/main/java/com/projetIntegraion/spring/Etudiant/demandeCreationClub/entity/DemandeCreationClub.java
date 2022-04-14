package com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity;

import java.text.ParseException;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class DemandeCreationClub {
	private @Id @GeneratedValue Long id;
	@NotNull
	@Size(min = 3, max = 30)
	private String nomClub;
	@Lob
	private String logo;
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@PastOrPresent
	private Date dateCreation;
	@NotNull
	@Size(min = 3, max = 30)
	private String activite;
	@NotNull
	@Size(min = 3, max = 50)
	private String president;
	@NotNull
	@Size(min = 3, max = 50)
	private String vicePresident;
	private int status;
	@ManyToOne
	private User responsableClub;
	@ManyToOne
	private User admin; 

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public User getAdmin() {
		return this.admin;
	}

	public void setAdmin(User admin) {
		this.admin = admin;
	}

	public DemandeCreationClub() {
		super();
	}

	public DemandeCreationClub(String sujet, String nomClub, String logo, String dateCreation, String activite,
			String president, String vicePresident, Long responsableClubId) throws ParseException {
		super();
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

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public String getActivite() {
		return activite;
	}

	public void setActivite(String activite) {
		this.activite = activite;
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

	public User getResponsableClub() {
		return this.responsableClub;
	}

	public void setResponsableClub(User responsableClub) {
		this.responsableClub = responsableClub;
	}

	@Override
	public String toString() {
		return "{" +
				" id='" + getId() + "'" +
				", nomClub='" + getNomClub() + "'" +
				", logo='" + getLogo() + "'" +
				", dateCreation='" + getDateCreation() + "'" +
				", activite='" + getActivite() + "'" +
				", president='" + getPresident() + "'" +
				", vicePresident='" + getVicePresident() + "'" +
				", status='" + getStatus() + "'" +
				", responsableClub='" + getResponsableClub() + "'" +
				", admin='" + getAdmin() + "'" +
				"}";
	}

}
