package com.projetIntegraion.spring.Etudiant.demandeCreationClub.service;

import java.util.List;

import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.DemandeCreationClub;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.repository.DemandeCreationClubRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class DemandeCreationClubService {
	@Autowired
	DemandeCreationClubRepository DemandeCreationClubRepository;

	public DemandeCreationClub save(DemandeCreationClub p) {
		return DemandeCreationClubRepository.save(p);
	}

	public Boolean existsById(Long id) {
		return DemandeCreationClubRepository.existsById(id);
	}

	public DemandeCreationClub updateDemandeCreationClub(Long id, DemandeCreationClub p) {
		return null;
	}

	public void deleteDemandeCreationClub(DemandeCreationClub p) {
		DemandeCreationClubRepository.delete(p);
	}

	public Boolean deleteDemandeCreationClub(Long id) {
		if (DemandeCreationClubRepository.existsById(id)) {
			DemandeCreationClubRepository.deleteById(id);
			return true;
		}
		return false;
	}

	public DemandeCreationClub getDemandeCreationClub(Long id) {
		return DemandeCreationClubRepository.existsById(id) == false ? null : DemandeCreationClubRepository.findById(id).get();
	}

	public List<DemandeCreationClub> getAllDemandeCreationClub() {
		return DemandeCreationClubRepository.findAll();
	}

	public Page<DemandeCreationClub> getAllDemandeCreationClubParPage(int page, int size){
		return DemandeCreationClubRepository.findAll(PageRequest.of(page, size));
	};

}
