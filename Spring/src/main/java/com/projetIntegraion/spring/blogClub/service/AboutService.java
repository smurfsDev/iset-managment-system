package com.projetIntegraion.spring.blogClub.service;

import com.projetIntegraion.spring.blogClub.entity.About;
import com.projetIntegraion.spring.blogClub.entity.Club;
import com.projetIntegraion.spring.blogClub.repository.AboutRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

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

	public About getAbout(Long id) {
		return aboutRepository.existsById(id) == false ? null : aboutRepository.findById(id).get();
	}
	
	public List<About> getAllAbouts() {
	
	 return aboutRepository.findAll();
	 }

	// public List<Club> getAllClubs() {
	// 	return clubRepository.findAll();
	// }

	// public Page<About> getAboutsClubPagPage(Long id, int page, int size) {
	// 	return aboutRepository.findByClubId(id, PageRequest.of(page, size));
	// }
	public Page<About> getAllAboutsParPage(int page, int size){
		return aboutRepository.findAll(PageRequest.of(page, size));
	};

	// public Page<Club> getClubParNom(String nom, int page, int size){
	// 	return clubRepository.findByNomClubContains(nom, page, size); //(nom, PageRequest.of(page, size));
	// };

	public List<About> getAboutByClubId(Club club) {
		return aboutRepository.findByClub(club);
	}

	public Page<About> getAllAboutsClubParPageClub(Long id, int page, int size){
		return aboutRepository.findByClubId(id, PageRequest.of(page, size));
	};
}
