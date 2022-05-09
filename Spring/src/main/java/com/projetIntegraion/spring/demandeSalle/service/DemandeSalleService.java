package com.projetIntegraion.spring.demandeSalle.service;

import java.util.List;

import com.projetIntegraion.spring.demandeSalle.entity.DemandeSalle;
import com.projetIntegraion.spring.demandeSalle.repository.DemandeSalleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class DemandeSalleService {
    @Autowired DemandeSalleRepository demandeSalleRepository;
    public DemandeSalle save(DemandeSalle demandeSalle){
        return demandeSalleRepository.save(demandeSalle);
    }
    public boolean existsById(Long id){
        return demandeSalleRepository.existsById(id);
    }
    public void delete(DemandeSalle demandeSalle){
        demandeSalleRepository.delete(demandeSalle);
    }
    public DemandeSalle deleteById(Long id){
        if (demandeSalleRepository.existsById(id)) {
            demandeSalleRepository.deleteById(id);
        }
        return null;
    }
    public DemandeSalle getDemandeSalleById(Long id){
        return demandeSalleRepository.existsById(id) ? demandeSalleRepository.findById(id).get() : null;
    }
    public List<DemandeSalle> getAllDemandeSalle(){
        return demandeSalleRepository.findAll();
    }
    public Page<DemandeSalle> getAllDemandeSalleParPage(int page , int size){
        return demandeSalleRepository.findAll(PageRequest.of(page, size));
    }


}
