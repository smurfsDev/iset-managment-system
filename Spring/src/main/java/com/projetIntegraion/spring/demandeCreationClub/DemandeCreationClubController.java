package com.projetIntegraion.spring.demandeCreationClub;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemandeCreationClubController {

	@Autowired
	DemandeCreationClubService DemandeCreationClubService;

	@PostMapping("/dcc")
	public DemandeCreationClub save(@RequestBody DemandeCreationClub DemandeCreationClub)
	throws ParseException
	{
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

	@GetMapping("/dcc")
	public ResponseEntity<List<DemandeCreationClub>> getAll() {
		List<DemandeCreationClub> prods = DemandeCreationClubService.getAllDemandeCreationClub();
		return prods.size()!=0?ResponseEntity.ok().body(prods):ResponseEntity.notFound().build();
	}

	@DeleteMapping("/dcc/{id}")
	public ResponseEntity<String>  supprimerDemandeCreationClub(@PathVariable(name = "id", required = false) String id) {
		return DemandeCreationClubService.deleteDemandeCreationClub(Long.parseLong(id))?ResponseEntity.ok().body("Produit deleted"):ResponseEntity.notFound().build();
	}

	@PutMapping("/dcc/{id}")
	public ResponseEntity<DemandeCreationClub> editerDemandeCreationClubService(@PathVariable(name = "id", required = false) String id,
			@RequestBody DemandeCreationClub DemandeCreationClub) {
		DemandeCreationClub p = DemandeCreationClubService.updateProduit(Long.parseLong(id), DemandeCreationClub);
		return p!=null?ResponseEntity.ok().body(p):ResponseEntity.notFound().build();
	}

//
//	private final DemandeCreationClubRepository repository;
//
//	DemandeCreationClubController(DemandeCreationClubRepository repository) {
//		this.repository = repository;
//	}
//
//	@GetMapping("/dcc")
//	List<DemandeCreationClub> all() {
//		return repository.findAll();
//	}
//
//	@PostMapping("/dcc")
//	DemandeCreationClub newEmployee(@RequestBody DemandeCreationClub newDemande) {
//		return repository.save(newDemande);
//	}
//
//	@GetMapping("/dcc/{id}")
//	DemandeCreationClub one(@PathVariable Long id) {
////		return repository.findById(id).orElseThrow(() -> new DeamndeNotFoundException(id));
//	}
//
//	@RequestMapping(value = "/produits/{id}", method = RequestMethod.PUT)
//	public ResponseEntity<String> update(@PathVariable(name = "id", required = false) String id,
//			@RequestBody DemandeCreationClub demande) {
//		Boolean exist = repository.existsById(Long.parseLong(id));
//		if (exist) {
//			DemandeCreationClub ret = new DemandeCreationClub();
//			DemandeCreationClub c = new DemandeCreationClub();
//				ret = repository.updateProduit(Long.parseLong(id),produit);
//				c = new Produit();
//				c.setDateCreation(ret.getDateCreation());
//				c.setNomProduit(ret.getNomProduit());
//				c.setPrixProduit(ret.getPrixProduit());
//			return c!=null?ResponseEntity.ok().body(ret.toString()):ResponseEntity.badRequest().build();
//		}
//		return ResponseEntity.notFound().build();
//	}
}
