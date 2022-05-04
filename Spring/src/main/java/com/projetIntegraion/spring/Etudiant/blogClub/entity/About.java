package com.projetIntegraion.spring.Etudiant.blogClub.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


@Entity
public class About {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Club club;
    private String longDescription;
    @CreationTimestamp
    private Timestamp created_at;
    @UpdateTimestamp
    private Timestamp updated_at;
    public About(Long id, Club club, String longDescription, Timestamp created_at, Timestamp updated_at) {
        this.id = id;
        this.club = club;
        this.longDescription = longDescription;
        this.created_at = created_at;
        this.updated_at = updated_at;
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
        return "About [id=" + id + ", club=" + club + ", longDescription=" + longDescription + ", created_at="
                + created_at + ", updated_at=" + updated_at + "]";
    }


}
