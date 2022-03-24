package com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class User{
	private @Id @GeneratedValue Long id;
	private String name,email,password,remember_token;
	private Timestamp email_verified_at;
    @CreationTimestamp
    private Timestamp created_at;
    @UpdateTimestamp
    private Timestamp updated_at;
	public User(Long id, String name, String email, String password, String remember_token,
			Timestamp email_verified_at) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.remember_token = remember_token;
		this.email_verified_at = email_verified_at;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRemember_token() {
		return remember_token;
	}
	public void setRemember_token(String remember_token) {
		this.remember_token = remember_token;
	}
	public Timestamp getEmail_verified_at() {
		return email_verified_at;
	}
	public void setEmail_verified_at(Timestamp email_verified_at) {
		this.email_verified_at = email_verified_at;
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
    
    
    
}