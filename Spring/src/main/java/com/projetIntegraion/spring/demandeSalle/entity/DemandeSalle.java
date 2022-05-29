package com.projetIntegraion.spring.demandeSalle.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.User;
import com.projetIntegraion.spring.demandeMateriel.annotation.myDateConstraint;

import org.springframework.lang.Nullable;

@myDateConstraint.List({
        @myDateConstraint(DateDemploi = "dateEmploi", DateDeRemise = "dateDeRemise", message = "Date remise doit etre apres date d'emploi!")
})
@Entity
public class DemandeSalle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String dateEmploi;
    @NotNull
    private String dateDeRemise;
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

    public DemandeSalle(String dateEmploi, String dateDeRemise, User responsable, String reponse, int status,
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

    public String getDateEmploi() {
        return dateEmploi;
    }

    public void setDateEmploi(String dateEmploi) {
        this.dateEmploi = dateEmploi;
    }

    public String getDateDeRemise() {
        return dateDeRemise;
    }

    public void setDateDeRemise(String dateDeRemise) {
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
