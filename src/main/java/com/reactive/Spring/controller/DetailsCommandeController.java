package com.reactive.Spring.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reactive.Spring.Services.DetailsCommandeService;
import com.reactive.Spring.model.entity.DetailsCommandes;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/detailsCommandes")
public class DetailsCommandeController {

	DetailsCommandeService detailsCommandeService;
	
	public DetailsCommandeController(DetailsCommandeService detailsCommandeService) {
		this.detailsCommandeService = detailsCommandeService;
	}
	
	@GetMapping("/{id}")
	public Flux<DetailsCommandes> getDetailsCommandeByIdProduit(@PathVariable Long id) {
		return detailsCommandeService.getDetailsCommandeByIdCommande(id);
	}
	
	@PutMapping("/{id}")
	public Mono<DetailsCommandes> updateDetailsCommande(@RequestBody DetailsCommandes detailsCommande, @PathVariable Long id) {
		return detailsCommandeService.updateDetailsCommandeById(detailsCommande, id);
	}
	
	@DeleteMapping("/id")
	public void deleteDetailsCommande(@PathVariable Long id) {
		detailsCommandeService.deleteDetailsCommandeByIdCommande(id);
	}
}
