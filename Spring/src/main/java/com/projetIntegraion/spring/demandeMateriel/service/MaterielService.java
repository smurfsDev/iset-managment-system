package com.projetIntegraion.spring.demandeMateriel.service;

import java.util.List;

import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.User;
import com.projetIntegraion.spring.demandeMateriel.entity.Materiel;
import com.projetIntegraion.spring.demandeMateriel.repository.MaterielRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class MaterielService {
    @Autowired
    MaterielRepository MaterielRepository;

    public Materiel save(Materiel materiel) {
        return MaterielRepository.save(materiel);
    }

    public boolean existsById(Long id) {
        return MaterielRepository.existsById(id);
    }

    public void delete(Materiel materiel) {
        MaterielRepository.delete(materiel);
    }

    public boolean deleteById(Long id) {
        if (MaterielRepository.existsById(id)) {
            MaterielRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Materiel getMaterielById(Long id) {
        return MaterielRepository.existsById(id) ? MaterielRepository.findById(id).get() : null;
    }

    public List<Materiel> getAllMateriel() {
        return MaterielRepository.findAll();
    }

    public List<Materiel> getAllMaterielByIdCategorie(Long idCategorie) {
        return MaterielRepository.findByCategorieId(idCategorie);
    }

    public Page<Materiel> getAllMaterielParPage(int page, int size) {
        return MaterielRepository.findAll(PageRequest.of(page, size));
    }

    public List<User> getAllResponsableMateriel() {
        return MaterielRepository.findByResponsable();
    }

}
