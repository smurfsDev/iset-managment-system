package com.projetIntegraion.spring.demandePlannificationEvent.service;

import java.util.List;

import com.projetIntegraion.spring.blogClub.entity.Club;
import com.projetIntegraion.spring.demandePlannificationEvent.entity.DemandeAdhesionEvent;
import com.projetIntegraion.spring.demandePlannificationEvent.repository.DemandeAdhesionEventRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class DemandeAdhesionEventService {
    @Autowired
	DemandeAdhesionEventRepository DemandeAdhesionEventRepository;

	public DemandeAdhesionEvent save(DemandeAdhesionEvent p) {
		return DemandeAdhesionEventRepository.save(p);
	}

	public Boolean existsById(Long id) {
		return DemandeAdhesionEventRepository.existsById(id);
	}

	public Boolean existsByIds(Long idEvent, int idEtudiant) {
		return DemandeAdhesionEventRepository.findByEventIdAndEtudiantId(idEvent, idEtudiant)!=null;
	}
	public DemandeAdhesionEvent getByIds(Long idEvent, int idEtudiant) {
		return DemandeAdhesionEventRepository.findByEventIdAndEtudiantId(idEvent, idEtudiant);
	}
	public DemandeAdhesionEvent updateDemandeAdhesionEvent(Long id, DemandeAdhesionEvent p) {
		return null;
	}

	public void delete(DemandeAdhesionEvent p) {
		DemandeAdhesionEventRepository.delete(p);
	}

	public Boolean deleteById(Long id) {
		if (DemandeAdhesionEventRepository.existsById(id)) {
			DemandeAdhesionEventRepository.deleteById(id);
			return true;
		}
		return false;
	}

	public DemandeAdhesionEvent getDemandeAdhesionEvent(Long id) {
		return DemandeAdhesionEventRepository.existsById(id) == false ? null : DemandeAdhesionEventRepository.findById(id).get();
	}

	public List<DemandeAdhesionEvent> getAllDemandeAdhesionEvent() {
		return DemandeAdhesionEventRepository.findAll();
	}

	public Page<DemandeAdhesionEvent> getAllDemandeAdhesionEventParPage(int page, int size){
		return DemandeAdhesionEventRepository.findAll(PageRequest.of(page, size));
	};

	public Page<DemandeAdhesionEvent> getAllDemandeAdhesionEventParPageEtudiant(int id, int page, int size){
		return DemandeAdhesionEventRepository.findByEtudiantId(id, PageRequest.of(page, size));
	};

	public Page<DemandeAdhesionEvent> getAllDemandeAdhesionEventParPageClub(Long id, int page, int size){
		return DemandeAdhesionEventRepository.findByClubId(id, PageRequest.of(page, size));
	};

	public Page<DemandeAdhesionEvent> findNonApprouvedDemandesParClub(Club id,int page, int size){
		return DemandeAdhesionEventRepository.findNonApprouvedDemandesParClub(id, PageRequest.of(page, size));
	} 

}
