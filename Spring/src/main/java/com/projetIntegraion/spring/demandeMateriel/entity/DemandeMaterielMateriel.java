package com.projetIntegraion.spring.demandeMateriel.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "demande_materiel_materiel")

public class DemandeMaterielMateriel implements  Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @Id
    @ManyToOne
    @JoinColumn(name = "idDemmande")
    private DemandeMateriel demandeMateriel;
    // @Id
    @ManyToOne
    @JoinColumn(name = "idMateriel")
    private Materiel materiel;
    @PositiveOrZero
    private int quantite = 0;

    public DemandeMateriel getDemandeMateriel() {
        return demandeMateriel;
    }

    public void setDemandeMateriel(DemandeMateriel demandeMateriel) {
        this.demandeMateriel = demandeMateriel;
    }

    public Materiel getMateriel() {
        return materiel;
    }

    public void setMateriel(Materiel materiel) {
        this.materiel = materiel;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
