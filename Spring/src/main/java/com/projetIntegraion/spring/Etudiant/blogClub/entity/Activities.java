package com.projetIntegraion.spring.Etudiant.blogClub.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Activities {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private String paragraphe;
    @ManyToOne
    private Club club;
    public Activities(Long id, String paragraphe, Club club) {
        this.id = id;
        this.paragraphe = paragraphe;
        this.club = club;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getParagraphe() {
        return paragraphe;
    }
    public void setParagraphe(String paragraphe) {
        this.paragraphe = paragraphe;
    }
    public Club getClub() {
        return club;
    }
    public void setClub(Club club) {
        this.club = club;
    }
    @Override
    public String toString() {
        return "Activities [club=" + club + ", id=" + id + ", paragraphe=" + paragraphe + "]";
    }

}
