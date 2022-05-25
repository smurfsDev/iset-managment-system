package com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Role {
   // @Id
    @Column(name = "role_id")
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
   // private Long id;
    private @Id @GeneratedValue Long id;
    private String name;
//     @OneToMany(mappedBy="role", cascade = CascadeType.ALL)
// private List<UserRole> Role;
    public Long getId() {
    return id;
    }
    public Role(String name) {
    super(); 
    this.name = name;
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
    public Role() {
    }
    
    // public Role(Integer id, String name) {
    //     this.id = id;
    //     this.name = name;
    // }
    



}
