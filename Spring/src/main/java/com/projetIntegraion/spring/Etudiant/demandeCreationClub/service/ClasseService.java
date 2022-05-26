package com.projetIntegraion.spring.Etudiant.demandeCreationClub.service;

import java.util.List;

import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.Classe;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.repository.ClasseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ClasseService {
	@Autowired
	ClasseRepository classeRepository;

	public Classe save(Classe p) {
		return classeRepository.save(p);
	}

	public Boolean existsById(Long id) {
		return classeRepository.existsById(id);
	}

	public Classe updateClasse(Long id, Classe p) {
		return null;
	}

	public void delete(Classe p) {
		classeRepository.delete(p);
	}

	public Boolean deleteById(Long id) {
		if (classeRepository.existsById(id)) {
			classeRepository.deleteById(id);
			return true;
		}
		return false;
	}

	public Classe getClasse(Long id) {
		return classeRepository.existsById(id) == false ? null : classeRepository.findById(id).get();
	}

	public List<Classe> getAllClasse() {
		return classeRepository.findAll();
	}

	public Page<Classe> getAllClasseParPage(int page, int size){
		return classeRepository.findAll(PageRequest.of(page, size));
	};

	public Page<Classe> getAllClasseById(Long id, int page, int size) {
		return classeRepository.findByDepartementId(id, PageRequest.of(page, size));
	}

}
