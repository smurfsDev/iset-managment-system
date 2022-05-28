package com.projetIntegraion.spring.avis.entity;

import javax.persistence.Entity;


import javax.persistence.GeneratedValue;
import javax.persistence.Id;



@Entity
public class avis<LongText> {
    @Id @GeneratedValue 
    private Long id;
    private String titre;
    private String content;
    private String image;


    public avis() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "avis [content=" + content + ", id=" + id + ", titre=" + titre + "]";
    }



}
