package com.projetIntegraion.spring.demandeMateriel.service;

import java.util.List;

import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.User;
import com.projetIntegraion.spring.demandeMateriel.entity.DemandeMateriel;
import com.projetIntegraion.spring.demandeMateriel.repository.demandeMaterielRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class DemandeMaterielService {

	@Autowired
	demandeMaterielRepository demandeMaterielRepository;

	public DemandeMateriel save(DemandeMateriel demande) {
		return demandeMaterielRepository.save(demande);
	}

	public boolean existsById(Long id) {
		return demandeMaterielRepository.existsById(id);
	}

	public void delete(DemandeMateriel demande) {
		demandeMaterielRepository.delete(demande);
	}

	public boolean deleteById(Long id) {
		if (demandeMaterielRepository.existsById(id)) {
			demandeMaterielRepository.deleteById(id);
			return true;
		}
		return false;
	}

	public DemandeMateriel getdemandeById(Long id) {
		return demandeMaterielRepository.existsById(id) ? demandeMaterielRepository.findById(id).get() : null;
	}

	public List<DemandeMateriel> getAlldemande() {
		return demandeMaterielRepository.findAll();
	}

	public Page<DemandeMateriel> getAllDemandeParPage(int page, int size) {
		return demandeMaterielRepository.findAll(PageRequest.of(page, size));
	}

	public Page<DemandeMateriel> getAllDemandeSalleParPageParResponsableClub(User use, int page, int size) {
		return demandeMaterielRepository.findByResponsableClub(use, PageRequest.of(page, size));
	}

	public Page<DemandeMateriel> getAllDemandeSalleParPageParDestinataire(User use, int page, int size) {
		return demandeMaterielRepository.findByDestinataire(use, PageRequest.of(page, size));
	}

}
