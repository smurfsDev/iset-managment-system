package com.projetIntegraion.spring.blogClub.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class Activity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private String paragraphe;
    @ManyToOne
    private Club club;
    @CreationTimestamp
    private Timestamp created_at;
    @UpdateTimestamp
    private Timestamp updated_at;
    public Activity(Long id, String paragraphe, Club club, Timestamp created_at, Timestamp updated_at) {
        this.id = id;
        this.paragraphe = paragraphe;
        this.club = club;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
    public Activity() {
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
        return "Activity [id=" + id + ", paragraphe=" + paragraphe + ", club=" + club + ", created_at=" + created_at
                + ", updated_at=" + updated_at + "]";
    }
}
