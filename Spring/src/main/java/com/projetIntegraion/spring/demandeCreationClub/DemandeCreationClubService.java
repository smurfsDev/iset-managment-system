package com.projetIntegraion.spring.demandeCreationClub;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemandeCreationClubService {
	@Autowired
	DemandeCreationClubRepository DemandeCreationClubRepository;

	public DemandeCreationClub saveDemandeCreationClub(DemandeCreationClub p) {
		return DemandeCreationClubRepository.save(p);
	}

	public Boolean existsById(Long id) {
		return DemandeCreationClubRepository.existsById(id);
	}

	public DemandeCreationClub updateProduit(Long id, DemandeCreationClub p) {
		if (DemandeCreationClubRepository.existsById(id)) {
			DemandeCreationClub a = DemandeCreationClubRepository.getById(id);
			a.setNomClub(p.getNomClub() != null ? p.getNomClub() : a.getNomClub());
			a.setActivite(p.getActivite() != null ? p.getActivite() : a.getActivite());
			a.setSujet(p.getSujet() != null ? p.getSujet() : a.getSujet());
			a.setLogo(p.getLogo() != null ? p.getLogo() : a.getLogo());
			a.setDateCreation(p.getDateCreation() != null ? p.getDateCreation() : a.getDateCreation());
			a.setPresident(p.getPresident() != null ? p.getPresident() : a.getPresident());
			a.setVicePresident(p.getVicePresident() != null ? p.getVicePresident() : a.getVicePresident());
			this.saveDemandeCreationClub(a);
			return a;
		}
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
}
