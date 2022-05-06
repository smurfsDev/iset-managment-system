package com.projetIntegraion.spring.blogClub.service;

import com.projetIntegraion.spring.blogClub.entity.Project;
import com.projetIntegraion.spring.blogClub.repository.ProjectRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository ProjectRepository;

    public Project save(Project p) {
		return ProjectRepository.save(p);
	}

	public Boolean existsById(Long id) {
		return ProjectRepository.existsById(id);
	}

	public Project updateClub(Long id, Project p) {
		return null;
	}

	public void delete(Project p) {
		ProjectRepository.delete(p);
	}

	public Boolean deleteById(Long id) {
		if (ProjectRepository.existsById(id)) {
			ProjectRepository.deleteById(id);
			return true;
		}
		return false;
	}

	public Project getClub(Long id) {
		return ProjectRepository.existsById(id) == false ? null : ProjectRepository.findById(id).get();
	}

	// public List<Project> getAllClubs() {
	// 	return ProjectRepository.findAll();
	// }
	public Project getProject(Long id) {
		return ProjectRepository.existsById(id) == false ? null : ProjectRepository.findById(id).get();
	}
	public Page<Project> getAllProjectsParPage(int page, int size){
		return ProjectRepository.findAll(PageRequest.of(page, size));
	};

	// public Page<Project> getClubParNom(String nom, int page, int size){
	// 	return ProjectRepository.findByNomClubContains(nom, page, size); //(nom, PageRequest.of(page, size));
	// };
}
