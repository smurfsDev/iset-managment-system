package com.projetIntegraion.spring.demandeSalle.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.projetIntegraion.spring.administrateur.departement.entity.Departement;

@Entity
public class Salle {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    @Size(min = 3, max = 30)
    private String titre;
    @ManyToOne
    private Departement departement;
    @OneToMany(mappedBy = "salle", cascade = CascadeType.ALL)
    private List<DemandeSalleSalle> demandeSalle;

    public Salle() {
        super();
    }

    public Salle(String titre, Departement departement) {
        super();
        this.titre = titre;
        this.departement = departement;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }

    public List<DemandeSalleSalle> getDemandeSalle() {
        return demandeSalle;
    }

    public void setDemandeSalle(List<DemandeSalleSalle> demandeSalle) {
        this.demandeSalle = demandeSalle;
    }

}
