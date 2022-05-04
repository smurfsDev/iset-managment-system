package com.projetIntegraion.spring.Etudiant.blogClub.service;

import com.projetIntegraion.spring.Etudiant.blogClub.entity.Club;
import com.projetIntegraion.spring.Etudiant.blogClub.repository.ClubRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;



@Service
public class ClubService {
    @Autowired
    private ClubRepository clubRepository;

    public Club save(Club p) {
		return clubRepository.save(p);
	}

	public Boolean existsById(Long id) {
		return clubRepository.existsById(id);
	}

	public Club updateClub(Long id, Club p) {
		return null;
	}

	public void delete(Club p) {
		clubRepository.delete(p);
	}

	public Boolean deleteById(Long id) {
		if (clubRepository.existsById(id)) {
			clubRepository.deleteById(id);
			return true;
		}
		return false;
	}

	public Club getClub(Long id) {
		return clubRepository.existsById(id) == false ? null : clubRepository.findById(id).get();
	}

	// public List<Club> getAllClubs() {
	// 	return clubRepository.findAll();
	// }

	public Page<Club> getAllDemandeCreationClubParPage(int page, int size){
		return clubRepository.findAll(PageRequest.of(page, size));
	};

	// public Page<Club> getClubParNom(String nom, int page, int size){
	// 	return clubRepository.findByNomClubContains(nom, page, size); //(nom, PageRequest.of(page, size));
	// };
}
