package com.projetIntegraion.spring.Etudiant.demandeCreationClub.service;

import java.util.List;

import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.DemandeAdhesionClub;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.repository.DemandeAdhesionClubRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class DemandeAdhesionClubService {
	@Autowired
	DemandeAdhesionClubRepository DemandeAdhesionClubRepository;

	public DemandeAdhesionClub save(DemandeAdhesionClub p) {
		return DemandeAdhesionClubRepository.save(p);
	}

	public Boolean existsById(Long id) {
		return DemandeAdhesionClubRepository.existsById(id);
	}

	public Boolean existsByIds(Long idClub, int idEtudiant) {
		return DemandeAdhesionClubRepository.findByClubIdAndEtudiantId(idClub, idEtudiant)!=null;
	}

	public DemandeAdhesionClub updateDemandeAdhesionClub(Long id, DemandeAdhesionClub p) {
		return null;
	}

	public void delete(DemandeAdhesionClub p) {
		DemandeAdhesionClubRepository.delete(p);
	}

	public Boolean deleteById(Long id) {
		if (DemandeAdhesionClubRepository.existsById(id)) {
			DemandeAdhesionClubRepository.deleteById(id);
			return true;
		}
		return false;
	}

	public DemandeAdhesionClub getDemandeAdhesionClub(Long id) {
		return DemandeAdhesionClubRepository.existsById(id) == false ? null : DemandeAdhesionClubRepository.findById(id).get();
	}

	public List<DemandeAdhesionClub> getAllDemandeAdhesionClub() {
		return DemandeAdhesionClubRepository.findAll();
	}

	public Page<DemandeAdhesionClub> getAllDemandeAdhesionClubParPage(int page, int size){
		return DemandeAdhesionClubRepository.findAll(PageRequest.of(page, size));
	};

	public Page<DemandeAdhesionClub> getAllDemandeAdhesionClubParPageEtudiant(int id, int page, int size){
		return DemandeAdhesionClubRepository.findByEtudiantId(id, PageRequest.of(page, size));
	};

	public Page<DemandeAdhesionClub> getAllDemandeAdhesionClubParPageClub(Long id, int page, int size){
		return DemandeAdhesionClubRepository.findByClubId(id, PageRequest.of(page, size));
	};
}
