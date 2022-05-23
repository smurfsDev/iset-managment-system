package com.projetIntegraion.spring.Etudiant.demandeCreationClub.service;

import java.util.List;

import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.Role;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.repository.RoleRepository;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
   
	@Autowired RoleRepository roleRepository;
	
    public List<Role> getAllRole() {
        return roleRepository.findAll();
    }

   
    public Role getRole(int id) {
		return roleRepository.existsById(id) == false ? null : roleRepository.findById(id).get();
	}
    public Role save(Role role)  {
		return roleRepository.save(role);
	}

}
