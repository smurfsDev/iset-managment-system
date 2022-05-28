package com.projetIntegraion.spring.Etudiant.demandeCreationClub.service;

import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.Matiere;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.repository.MatiereRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class MatiereService {
    @Autowired
	MatiereRepository matiereRepository;

	public Matiere save(Matiere m) {
		return matiereRepository.save(m);
	}

	public Boolean existsById(Long id) {
		return matiereRepository.existsById(id);
	}

	public Matiere updateMatiere(Long id, Matiere m) {
		return null;
	}

	public void delete(Matiere m) {
		matiereRepository.delete(m);
	}

	public Boolean deleteById(Long id) {
		if (matiereRepository.existsById(id)) {
			matiereRepository.deleteById(id);
			return true;
		}
		return false;
	}

	public Matiere getMatiere(Long id) {
		return matiereRepository.existsById(id) == false ? null : matiereRepository.findById(id).get();
	}

    public Page<Matiere> getAllMatiereByClasseId(Long id, int page, int size) {
		return matiereRepository.findByClasseId(id, PageRequest.of(page, size));
	}

    public Page<Matiere> getAllMatiereByEnseignantId(Integer id, int page, int size) {
		return matiereRepository.findByEnseignantId(id, PageRequest.of(page, size));
	}

}
