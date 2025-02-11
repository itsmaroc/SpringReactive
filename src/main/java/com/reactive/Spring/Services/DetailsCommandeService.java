package com.reactive.Spring.Services;

import org.springframework.stereotype.Service;

import com.reactive.Spring.model.dao.DetailsCommandeRepository;
import com.reactive.Spring.model.entity.DetailsCommandes;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DetailsCommandeService {
	
	DetailsCommandeRepository detailsCommandeRepository;
	
	public DetailsCommandeService(DetailsCommandeRepository detailsCommandeRepository) {
		this.detailsCommandeRepository = detailsCommandeRepository;
	}
	
	public Flux<DetailsCommandes> getDetailsCommandeByIdCommande(Long id) {
		return detailsCommandeRepository.findByCommandeID(id);
	}

	
	public Mono<DetailsCommandes> updateDetailsCommandeById(DetailsCommandes detailsCommande, Long id) {
		return detailsCommandeRepository.findById(id)
				.flatMap(detailCommandeUpdated -> {
					detailCommandeUpdated.setPrixUnitaire(detailsCommande.getPrixUnitaire());
					detailCommandeUpdated.setQuantite(detailsCommande.getQuantite());
					detailCommandeUpdated.setSousTotal(detailsCommande.getSousTotal());
					return detailsCommandeRepository.save(detailCommandeUpdated);
					
				}).switchIfEmpty(Mono.error(new ClassNotFoundException("Le détails de la commande non trouvé !")));
	}
	
	public void deleteDetailsCommandeByIdCommande(Long id) {
		detailsCommandeRepository.findByCommandeID(id)
						.flatMap(commadeToDelete -> {
							return detailsCommandeRepository.deleteByCommandeID(id);
						})
						.switchIfEmpty(Mono.error(new ClassNotFoundException("le Details de la commande dans l'id est " + id + " non trouvé !")));
	}

}
