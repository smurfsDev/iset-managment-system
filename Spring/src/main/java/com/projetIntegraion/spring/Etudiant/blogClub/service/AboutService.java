package com.projetIntegraion.spring.Etudiant.blogClub.service;

import com.projetIntegraion.spring.Etudiant.blogClub.entity.About;
import com.projetIntegraion.spring.Etudiant.blogClub.repository.AboutRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AboutService {
    @Autowired
    private AboutRepository aboutRepository;

    public About save(About a) {
		return aboutRepository.save(a);
	}

	public Boolean existsById(Long id) {
		return aboutRepository.existsById(id);
	}

	public About updateAbout(Long id, About a) {
		return null;
	}

	public void delete(About a) {
		aboutRepository.delete(a);
	}

	public Boolean deleteById(Long id) {
		if (aboutRepository.existsById(id)) {
			aboutRepository.deleteById(id);
			return true;
		}
		return false;
	}

	public About getClub(Long id) {
		return aboutRepository.existsById(id) == false ? null : aboutRepository.findById(id).get();
	}

	// public List<Club> getAllClubs() {
	// 	return clubRepository.findAll();
	// }

	// public Page<About> getAllDemandeCreationClubParPage(int page, int size){
	// 	return clubRepository.findAll(PageRequest.of(page, size));
	// };

	// public Page<Club> getClubParNom(String nom, int page, int size){
	// 	return clubRepository.findByNomClubContains(nom, page, size); //(nom, PageRequest.of(page, size));
	// };
}
