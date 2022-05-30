package com.projetIntegraion.spring.demandePlannificationEvent.service;

import java.util.List;

import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.User;
import com.projetIntegraion.spring.demandePlannificationEvent.entity.DemandeEvent;
import com.projetIntegraion.spring.demandePlannificationEvent.repository.DemandeEventRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

@Service
public class DemandeEventService {
    @Autowired
	DemandeEventRepository DemandeEventRepository;

	public DemandeEvent save(DemandeEvent p) {
		return DemandeEventRepository.save(p);
	}

	public Boolean existsById(Long id) {
		return DemandeEventRepository.existsById(id);
	}

	public DemandeEvent updateDemandeEvent(Long id, DemandeEvent p) {
		return null;
	}

	public void delete(DemandeEvent p) {
		DemandeEventRepository.delete(p);
	}

	public Boolean deleteById(Long id) {
		if (DemandeEventRepository.existsById(id)) {
			DemandeEventRepository.deleteById(id);
			return true;
		}
		return false;
	}

	public DemandeEvent getDemandeEvent(Long id) {
		return DemandeEventRepository.existsById(id) == false ? null : DemandeEventRepository.findById(id).get();
	}

	public List<DemandeEvent> getAllDemandeEvent() {
		return DemandeEventRepository.findAll();
	}

	public Page<DemandeEvent> getAllDemandeEventParPage(int page, int size){
		return DemandeEventRepository.findAll(PageRequest.of(page, size));
	};

	public Page<DemandeEvent> getAuthUserDemandsParPage(User id, int page, int size){
		return DemandeEventRepository.findByResponsableClub(id, PageRequest.of(page, size));
	};

	public Page<DemandeEvent> findApprouvedEvent(int page, int size){
		return DemandeEventRepository.findApprouvedEvent( PageRequest.of(page, size));
	} 

	
}
