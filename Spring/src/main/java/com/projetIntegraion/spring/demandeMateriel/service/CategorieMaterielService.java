package com.projetIntegraion.spring.demandeMateriel.service;

import java.util.List;

import com.projetIntegraion.spring.demandeMateriel.entity.CategorieMateriel;
import com.projetIntegraion.spring.demandeMateriel.repository.CategorieMaterielRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class CategorieMaterielService {
    @Autowired  CategorieMaterielRepository CategorieMaterielRepository;
    public CategorieMateriel save(CategorieMateriel categorie){
        return CategorieMaterielRepository.save(categorie);
    }
    public boolean existsById(Long id){
        return CategorieMaterielRepository.existsById(id);
    }
    public void delete(CategorieMateriel categorie){
        CategorieMaterielRepository.delete(categorie);
    }
    public boolean deleteById(Long id){
        if (CategorieMaterielRepository.existsById(id)) {
            CategorieMaterielRepository.deleteById(id);
            return true;
        }
        return false;
    }
    public CategorieMateriel getCategorieById(Long id){
        return CategorieMaterielRepository.existsById(id) ? CategorieMaterielRepository.findById(id).get() : null;
    }
    public List<CategorieMateriel> getAllCategorie(){
        return CategorieMaterielRepository.findAll();
    }
    public Page<CategorieMateriel> getAllCategorieParPage(int page , int size){
        return CategorieMaterielRepository.findAll(PageRequest.of(page, size));
    }

}
