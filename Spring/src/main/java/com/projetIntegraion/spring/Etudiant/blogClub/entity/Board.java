package com.projetIntegraion.spring.Etudiant.blogClub.entity;

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
public class Board {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String post;
    @Lob
    private String photo;
    @ManyToOne
    private Club club;
    @CreationTimestamp
    private Timestamp created_at;
    @UpdateTimestamp
    private Timestamp updated_at;
    public Board(Long id, String name, String post, String photo, Club club, Timestamp created_at, Timestamp updated_at) {
        this.id = id;
        this.name = name;
        this.post = post;
        this.photo = photo;
        this.club = club;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
    public Board() {
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPost() {
        return post;
    }
    public void setPost(String post) {
        this.post = post;
    }
    public String getPhoto() {
        return photo;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
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
        return "Board [id=" + id + ", name=" + name + ", post=" + post + ", photo=" + photo + ", club=" + club
                + ", created_at=" + created_at + ", updated_at=" + updated_at + "]";
    }

}
