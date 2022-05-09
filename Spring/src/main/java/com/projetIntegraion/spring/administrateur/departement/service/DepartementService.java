package com.projetIntegraion.spring.administrateur.departement.service;

import java.util.List;

import com.projetIntegraion.spring.administrateur.departement.entity.Departement;
import com.projetIntegraion.spring.administrateur.departement.repository.DepartementRepository;
import com.projetIntegraion.spring.demandeSalle.entity.Salle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class DepartementService {

    @Autowired DepartementRepository departementRepository;
    public Departement save(Departement departement){
        return departementRepository.save(departement);
    }
    public boolean existsById(Long id){
        return departementRepository.existsById(id);
    }
    public void delete(Departement departement){
        departementRepository.delete(departement);
    }
    public Departement deleteById(Long id){
        if (departementRepository.existsById(id)) {
            departementRepository.deleteById(id);
        }
        return null;
    }
    public Departement getDepartementById(Long id){
        return departementRepository.existsById(id) ? departementRepository.findById(id).get() : null;
    }
    public List<Departement> getAllDepartement(){
        return departementRepository.findAll();
    }
    public Page<Departement> getAllDepartementParPage(int page , int size){
        return departementRepository.findAll(PageRequest.of(page, size));
    }
    public List<Salle> getAllSalleByDepartement(Long id){
        return departementRepository.findById(id).get().getSalle();
    }

    


}
