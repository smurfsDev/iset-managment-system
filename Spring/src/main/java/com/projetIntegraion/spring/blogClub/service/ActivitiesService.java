package com.projetIntegraion.spring.blogClub.service;

import java.util.List;

import com.projetIntegraion.spring.blogClub.entity.Activity;
import com.projetIntegraion.spring.blogClub.repository.ActivitiesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ActivitiesService {
    @Autowired
     ActivitiesRepository activitiesRepository;

    public Activity save(Activity act) {
		return activitiesRepository.save(act);
	}

	public Boolean existsById(Long id) {
		return activitiesRepository.existsById(id);
	}

	public Activity updateClub(Long id, Activity act) {
		return null;
	}

	public void delete(Activity act) {
		activitiesRepository.delete(act);
	}

	public Boolean deleteById(Long id) {
		if (activitiesRepository.existsById(id)) {
			activitiesRepository.deleteById(id);
			return true;
		}
		return false;
	}

	public Activity getClub(Long id) {
		return activitiesRepository.existsById(id) == false ? null : activitiesRepository.findById(id).get();
	}
	public Activity getActivity(Long id) {
		return activitiesRepository.existsById(id) == false ? null : activitiesRepository.findById(id).get();
	}
	// public List<Club> getAllClubs() {
	// 	return clubRepository.findAll();
	// }
	public List<Activity> getAllActivities() {
	
		return activitiesRepository.findAll();
	}

	public Page<Activity> getAllActivitiesParPage(int page, int size){
		return activitiesRepository.findAll(PageRequest.of(page, size));
	};

	// public Page<Club> getClubParNom(String nom, int page, int size){
	// 	return clubRepository.findByNomClubContains(nom, page, size); //(nom, PageRequest.of(page, size));
	// };
}
