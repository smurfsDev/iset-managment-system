package com.projetIntegraion.spring.demandeMateriel.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name = "demande_materiel_materiel")

public class DemandeMaterielMateriel implements  Serializable{

    @Id
    @ManyToOne
    @JoinColumn(name = "idDemmande")
    private DemandeMateriel demandeMateriel;
    @Id
    @ManyToOne
    @JoinColumn(name = "idMateriel")
    private Materiel materiel;
    private int quantite =0;
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
    
    
}
