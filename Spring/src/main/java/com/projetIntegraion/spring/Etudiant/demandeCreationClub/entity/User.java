package com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity;


import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import java.util.HashSet;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "USER")
public class User implements Serializable, UserDetails {
// @Id
// @GeneratedValue(strategy = GenerationType.IDENTITY)
// private Integer id;
private @Id @GeneratedValue Integer id;
private String username;
private String password;
@CreationTimestamp

private Timestamp created_at;
@UpdateTimestamp

private Timestamp updated_at;

@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"),
inverseJoinColumns = @JoinColumn(name = "role_id"))
// @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
//     private List<UserRole> User;


private Set<Role> roles = new HashSet<>();
public Integer getId() {
 return id;
 }
public void setId(Integer id) {
 this.id = id;
 }
public String getUsername() {
 return username;
 }
@Override
public boolean isAccountNonExpired() {
 return false;
 }
@Override
public boolean isAccountNonLocked() {
 return false;
 }
@Override
public boolean isCredentialsNonExpired() {
	 return false;
	 }
	@Override
	public boolean isEnabled() {
	 return false;
	 }
	public void setUsername(String username) {
	 this.username = username;
	 }
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<Role> roles = this.getRoles();
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		for (Role role : roles) {
		authorities.add(new SimpleGrantedAuthority(role.getName()));
		} return authorities;
	 }
	public String getPassword() {
	 return password;
	 }
	public void setPassword(String password) {
	 this.password = password;
	 }
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
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