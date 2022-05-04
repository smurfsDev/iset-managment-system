package com.projetIntegraion.spring.Etudiant.blogClub.service;

import com.projetIntegraion.spring.Etudiant.blogClub.repository.UserRepository;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User save(User u) {
		return userRepository.save(u);
	}

	public Boolean existsById(Long id) {
		return userRepository.existsById(id);
	}

	public User updateClub(Long id, User u) {
		return null;
	}

	public void delete(User u) {
		userRepository.delete(u);
	}

	public Boolean deleteById(Long id) {
		if (userRepository.existsById(id)) {
			userRepository.deleteById(id);
			return true;
		}
		return false;
	}

	public User getUser(Long id) {
		return userRepository.existsById(id) == false ? null : userRepository.findById(id).get();
	}

	// public List<Club> getAllClubs() {
	// 	return clubRepository.findAll();
	// }

	public Page<User> getAllUserParPage(int page, int size){
		return userRepository.findAll(PageRequest.of(page, size));
	};

	// public Page<Club> getClubParNom(String nom, int page, int size){
	// 	return clubRepository.findByNomClubContains(nom, page, size); //(nom, PageRequest.of(page, size));
	// };
}

