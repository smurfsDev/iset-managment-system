package com.projetIntegraion.spring.demandeSalle.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "demande_salle_salle")
public class DemandeSalleSalle {
    @Id @GeneratedValue
    private  Long id;

    // @Id
    @ManyToOne
    @JoinColumn(name = "idDemmande")
    private DemandeSalle demandeSalle;
    // @Id
    @ManyToOne
    @JoinColumn(name = "idSalle")
    private Salle salle;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public DemandeSalle getDemandeSalle() {
        return demandeSalle;
    }
    public void setDemandeSalle(DemandeSalle demandeSalle) {
        this.demandeSalle = demandeSalle;
    }
    public Salle getSalle() {
        return salle;
    }
    public void setSalle(Salle salle) {
        this.salle = salle;
    }
    

}
