package com.reactive.Spring.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reactive.Spring.Services.ProduitService;
import com.reactive.Spring.model.entity.Produits;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/produits")
public class ProduitController {

	ProduitService produitService;
	
	public ProduitController(ProduitService produitService) {
		this.produitService = produitService;
	}
	
	@GetMapping("") 
	public Flux<Produits> getAllProduits() {
		return produitService.getAllProduits();
	}
	
	@GetMapping("/{id}") 
	public Mono<Produits> getProduitById(Long id) {
		return produitService.getById(id);
	}
	
	@PutMapping("/{id}")
	public Mono<Produits> updateProduit(@RequestBody Produits produit, @PathVariable Long id) {
		return produitService.updateProduit(produit, id);
	}
	
	@DeleteMapping("/{id}")
	public void deleteProduit(@PathVariable Long id) {
		produitService.deleteProduit(id);
	}
	

    @GetMapping("/total")
    public	Mono<Long> getCountProduits() {
    	return produitService.getCount();
    }
}
