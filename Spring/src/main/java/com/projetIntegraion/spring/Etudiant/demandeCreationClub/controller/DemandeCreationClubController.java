// TODO: Fix responses 
// TODO: Fix api call uri 
package com.projetIntegraion.spring.Etudiant.demandeCreationClub.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.text.ParseException;
// import java.util.List;

import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.DemandeCreationClub;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.ResponsePagination;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.repository.DemandeCreationClubRepository;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.service.DemandeCreationClubService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class DemandeCreationClubController {

	@Autowired
	DemandeCreationClubService DemandeCreationClubService;
	@Autowired
	DemandeCreationClubRepository dccr;

	@RequestMapping(value = "/dcc", method = RequestMethod.GET)
	public ResponsePagination employeesPageable(Pageable pageable) {
		Page <DemandeCreationClub> d = dccr.findAll(pageable);
		ResponsePagination res = new ResponsePagination();
		res.setData(d.getContent());
		res.setCurrent_page(d.getPageable().getPageNumber()+1);
		res.setLast_page(d.getTotalPages());
		// res.setNext_page_url(d.hasNext()+"");
		// res.setPrev_page_url(d.hasPrevious()+"");
		res.setNext_page_url(d.hasNext()?"http://localhost:8000/api/dcc?page="+d.getPageable().next().getPageNumber()+"":null);
		res.setPrev_page_url(d.hasPrevious()?"http://localhost:8000/api/dcc?page="+d.getPageable().previousOrFirst().getPageNumber()+"":null);
		return res;
	}
	
	@PostMapping("/dcc")
	public DemandeCreationClub save(@RequestBody DemandeCreationClub DemandeCreationClub)
			throws ParseException {
		DemandeCreationClub dcc = new DemandeCreationClub();
		dcc.setNomClub(DemandeCreationClub.getNomClub());
		dcc.setActivite(DemandeCreationClub.getActivite());
		dcc.setSujet(DemandeCreationClub.getSujet());
		dcc.setLogo(DemandeCreationClub.getLogo());
		dcc.setDateCreation(DemandeCreationClub.getDateCreation());
		dcc.setPresident(DemandeCreationClub.getPresident());
		dcc.setVicePresident(DemandeCreationClub.getVicePresident());
		dcc.setResponsableClubId(DemandeCreationClub.getResponsableClubId());
		DemandeCreationClub saveProduit = DemandeCreationClubService.saveDemandeCreationClub(dcc);
		return saveProduit;
	}
//
//	@GetMapping("/dcc")
//	public ResponseEntity<List<DemandeCreationClub>> getAll() {
//		List<DemandeCreationClub> prods = DemandeCreationClubService.getAllDemandeCreationClub();
//		return prods.size() != 0 ? ResponseEntity.ok().body(prods) : ResponseEntity.notFound().build();
//	}

	@DeleteMapping("/dcc/{id}")
	public ResponseEntity<String> supprimerDemandeCreationClub(@PathVariable(name = "id", required = false) String id) {
		return DemandeCreationClubService.deleteDemandeCreationClub(Long.parseLong(id))
				? ResponseEntity.ok().body("Produit deleted")
				: ResponseEntity.notFound().build();
	}

	@PutMapping("/dcc/{id}")
	public ResponseEntity<DemandeCreationClub> editerDemandeCreationClubService(
			@PathVariable(name = "id", required = false) String id,
			@RequestBody DemandeCreationClub DemandeCreationClub) {
		DemandeCreationClub p = DemandeCreationClubService.updateProduit(Long.parseLong(id), DemandeCreationClub);
		return p != null ? ResponseEntity.ok().body(p) : ResponseEntity.notFound().build();
	}
}
