



package com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users_roles")

public class UserRole implements Serializable{
    // @Id @GeneratedValue
    // private  Integer id;
    //private @Id @GeneratedValue Integer id;
    @EmbeddedId
    UserRoleKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @MapsId("roleId")
    @JoinColumn(name = "role_id")
    Role role;

     @NotNull
    private Integer status=0;
    
    private Integer classe=0;
    
    private Integer departement=0;


    
    public UserRole() {
    }

    public UserRoleKey getId() {
        return id;
    }

    public void setId(UserRoleKey id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getClasse() {
        return classe;
    }

    public void setClasse(Integer classe) {
        this.classe = classe;
    }

    public Integer getDepartement() {
        return departement;
    }

    public void setDepartement(Integer departement) {
        this.departement = departement;
    }

    public UserRole(UserRoleKey id, User user, Role role, @NotNull Integer status, Integer classe,
            Integer departement) {
        this.id = id;
        this.user = user;
        this.role = role;
        this.status = status;
        this.classe = classe;
        this.departement = departement;
    }

    

    // // @Id
    // @ManyToOne
    // @JoinColumn(name = "userId")
    // private User user;
    // // @Id
    // @ManyToOne
    // @JoinColumn(name = "roleId")
    // private Role role;

    // @NotNull
    // private Integer status;
    
    // private Integer classe;
    
    // private Integer departement;

    // public Integer getId() {
    //     return id;
    // }

    // public void setId(Integer id) {
    //     this.id = id;
    // }

    // public User getUser() {
    //     return user;
    // }

    // public void setUser(User user) {
    //     this.user = user;
    // }

    // public Role getRole() {
    //     return role;
    // }

    // public void setRole(Role role) {
    //     this.role = role;
    // }

    // public Integer getStatus() {
    //     return status;
    // }

    // public void setStatus(Integer status) {
    //     this.status = status;
    // }

    // public Integer getClasse() {
    //     return classe;
    // }

    // public void setClasse(Integer classe) {
    //     this.classe = classe;
    // }

    // public Integer getDepartement() {
    //     return departement;
    // }

    // public void setDepartement(Integer departement) {
    //     this.departement = departement;
    // }

    // public UserRole(Integer id, User user, Role role) {
    //     this.id = id;
    //     this.user = user;
    //     this.role = role;
    //     this.status = 0;
    //     this.classe = null;
    //     this.departement = null;
    // }
    
}
