package com.projetIntegraion.spring.demandeCreationClub;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemandeCreationClubController {

	@Autowired
	DemandeCreationClubService DemandeCreationClubService;

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

	@GetMapping("/dcc")
	public ResponseEntity<List<DemandeCreationClub>> getAll() {
		List<DemandeCreationClub> prods = DemandeCreationClubService.getAllDemandeCreationClub();
		return prods.size() != 0 ? ResponseEntity.ok().body(prods) : ResponseEntity.notFound().build();
	}

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