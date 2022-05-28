package com.projetIntegraion.spring.blogClub.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   
    @Lob
    private String affiche;
    @ManyToOne
    private Club club;
    @CreationTimestamp
    private Timestamp created_at;
    @UpdateTimestamp
    private Timestamp updated_at;
    public Project(Long id, String affiche, Club club, Timestamp created_at, Timestamp updated_at) {
        this.id = id;
        this.affiche = affiche;
        this.club = club;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
    public Long getId() {
        return id;
    }
    public Project() {
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getAffiche() {
        return affiche;
    }
    public void setAffiche(String affiche) {
        this.affiche = affiche;
    }
    public Club getClub() {
        return club;
    }
    public void setClub(Club club) {
        this.club = club;
    }
    public Timestamp getCreated_at() {
        return created_at;
    }
    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }
    public Timestamp getUpdated_at() {
        return updated_at;
    }
    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }
    @Override   
    public String toString() {
        return "Project [id=" + id + ", affiche=" + affiche + ", club=" + club + ", created_at=" + created_at + ", updated_at=" + updated_at + "]";
    }

}
