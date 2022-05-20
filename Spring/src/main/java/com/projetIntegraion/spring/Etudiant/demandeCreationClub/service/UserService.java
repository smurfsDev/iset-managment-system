package com.projetIntegraion.spring.Etudiant.demandeCreationClub.service;

import java.util.List;

import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.User;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired UserRepository UserRepository;
    public List<User> getAllUser() {
        return UserRepository.findAll();
    }
    public Page<User> getAllUserParPage(int page , int size){
        return UserRepository.findAll(PageRequest.of(page, size));
    }

}
