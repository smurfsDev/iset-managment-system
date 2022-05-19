package com.projetIntegraion.spring.Etudiant.demandeCreationClub.service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.HashSet;

import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.Role;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.User;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.repository.UserRepository;
import com.projetIntegraion.spring.security.SecurityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired UserRepository UserRepository;
    private RoleRepository roleRepository;

	@Autowired
	SecurityConfig securityConfig;
    public List<User> getAllUser() {
        return UserRepository.findAll();
    }
    public Page<User> getAllUserParPage(int page , int size){
        return UserRepository.findAll(PageRequest.of(page, size));
    }
    
	@Autowired
	public UserService(UserRepository userRepository,RoleRepository roleRepository) {
	 this.UserRepository = userRepository;
	 this.roleRepository = roleRepository;
	 }
	@Override
	public UserDetails loadUserByUsername(String username) throws
	UsernameNotFoundException {
	 Objects.requireNonNull(username);
	 User user = UserRepository.findUserWithName(username)
	 .orElseThrow(() -> new UsernameNotFoundException("User not found"));
	 return user;
	 }

	 public User saveUser(String username, String password, String confirmedPassword) {
		User appUser = new User();
		if (UserRepository.findUserWithName(username).isPresent() == true)
		throw new RuntimeException("User already exists");
		if (!password.equals(confirmedPassword))
		throw new RuntimeException("Please confirm your password");
		appUser.setUsername(username);
		Set<Role> roles = new HashSet<Role>();
		Role r = new Role("ROLE_USER");
		roleRepository.save(r);
		roles.add(r);
		appUser.setRoles(roles);
		appUser.setPassword(securityConfig.passwordEncoder().encode(password));
		UserRepository.save(appUser);
		return appUser;
		} 

}
