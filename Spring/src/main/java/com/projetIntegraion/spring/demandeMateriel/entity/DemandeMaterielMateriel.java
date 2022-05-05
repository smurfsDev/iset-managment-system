package com.projetIntegraion.spring.demandeMateriel.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name = "demande_materiel_materiel")

public class DemandeMaterielMateriel {

    @Id
    @ManyToOne
    @JoinColumn(name = "idDemmande")
    private DemandeMateriel demandeMateriel;
    @Id
    @ManyToOne
    @JoinColumn(name = "idMateriel")
    private Materiel materiel;
    private int quantite = 0;

}
