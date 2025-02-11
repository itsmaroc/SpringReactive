package com.reactive.Spring.Services;

import org.springframework.stereotype.Service;

import com.reactive.Spring.model.dao.ProduitsRepository;
import com.reactive.Spring.model.entity.Produits;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProduitService {
	
	ProduitsRepository produitsRepository;
	
	public ProduitService(ProduitsRepository produitsRepository) {
		this.produitsRepository = produitsRepository;
	}
	
	public Flux<Produits> getAllProduits() {
		return produitsRepository.findAll();
	}
	
	public Mono<Produits> getById(Long id) {
		return produitsRepository.findById(id);
	}
	
	public Mono<Produits> updateProduit(Produits produit, Long id) {
		
		return produitsRepository.findById(id)
				.flatMap(produitUpdated -> {
					produitUpdated.setNomProduit(produit.getNomProduit());
					produitUpdated.setDescription(produit.getDescription());
					produitUpdated.setPrix(produit.getPrix());
					produitUpdated.setStock(produit.getStock());
					return produitsRepository.save(produitUpdated);
				});
	}
	
	public void deleteProduit(Long id) {
		produitsRepository.findById(id)
		.flatMap(produit -> {
			return produitsRepository.deleteById(id);
		}).switchIfEmpty(Mono.error(new ClassNotFoundException("Element non trouvé !")));
	}

	public Mono<Long> getCount() {
		return produitsRepository.count();
	}

}
