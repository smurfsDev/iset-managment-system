package com.projetIntegraion.spring.demandeSalle.service;

import java.util.List;

import com.projetIntegraion.spring.demandeSalle.entity.Salle;
import com.projetIntegraion.spring.demandeSalle.repository.SalleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class SalleService {
    @Autowired SalleRepository salleRepository;
    public Salle save(Salle salle){
        return salleRepository.save(salle);
    }
    public boolean existsById(Long id){
        return salleRepository.existsById(id);
    }
    public void delete(Salle salle){
        salleRepository.delete(salle);
    }
    public Salle deleteById(Long id){
        if (salleRepository.existsById(id)) {
            salleRepository.deleteById(id);
        }
        return null;
    }
    public Salle getSalleById(Long id){
        return salleRepository.existsById(id) ? salleRepository.findById(id).get() : null;
    }
    public List<Salle> getAllSalle(){
        return salleRepository.findAll();
    }
    public Page<Salle> getAllSalleParPage(int page , int size){
        return salleRepository.findAll(PageRequest.of(page, size));
    }
    
}
