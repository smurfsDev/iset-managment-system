package com.projetIntegraion.spring.blogClub.service;

import java.util.Optional;

import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.User;
import com.projetIntegraion.spring.blogClub.entity.Club;
import com.projetIntegraion.spring.blogClub.repository.ClubRepository;

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

	public Page<Club> getAllClubParPage(int page, int size){
		return clubRepository.findAll(PageRequest.of(page, size));
	};

	// public Page<Club> getClubParNom(String nom, int page, int size){
	// 	return clubRepository.findByNomClubContains(nom, page, size); //(nom, PageRequest.of(page, size));
	// };

	public Optional<Club> getClubParResponsable(int id){
		return clubRepository.findByResponsableClubId(id);
	};

	public Boolean isThisClubOwner(Long id, User idResponsable){
		return clubRepository.findByResponsableClub(idResponsable).equals(clubRepository.findById(id));
	};
}
