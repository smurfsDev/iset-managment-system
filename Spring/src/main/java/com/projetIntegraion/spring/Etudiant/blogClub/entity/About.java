package com.projetIntegraion.spring.Etudiant.blogClub.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class About {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Club club;
    private String longDescription;
    public About(Long id, Club club, String longDescription) {
        this.id = id;
        this.club = club;
        this.longDescription = longDescription;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Club getClub() {
        return club;
    }
    public void setClub(Club club) {
        this.club = club;
    }
    public String getLongDescription() {
        return longDescription;
    }
    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }
    @Override
    public String toString() {
        return "About [club=" + club + ", id=" + id + ", longDescription=" + longDescription + "]";
    }



}
