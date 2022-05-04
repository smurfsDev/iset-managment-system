package com.projetIntegraion.spring.Etudiant.blogClub.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

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
    public Board(Long id, String name, String post, String photo, Club club) {
        this.id = id;
        this.name = name;
        this.post = post;
        this.photo = photo;
        this.club = club;
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
    @Override
    public String toString() {
        return "Board [club=" + club + ", id=" + id + ", name=" + name + ", post=" + post + ", photo=" + photo + "]";
    }
    
}
