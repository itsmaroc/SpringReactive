package com.reactive.Spring.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reactive.Spring.Services.CommandeService;
import com.reactive.Spring.dto.CommandeDTO;
import com.reactive.Spring.model.entity.Commandes;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/commandes")
public class CommandeController {

	private final CommandeService commandeService;

    public CommandeController(CommandeService commandeService) {
        this.commandeService = commandeService;
    }
	
	@GetMapping("")
	public Flux<CommandeDTO> getAllCommandes() {
		return commandeService.getAllCommandes();
	}
	
	@GetMapping("/{id}")
	public Mono<Commandes> getClientById(@PathVariable("id") Long id) {
		return commandeService.getById(id);
	}
	
	@PostMapping("/addcommande") 
	public Mono<Commandes> addCommande(@Valid @RequestBody CommandeDTO commande) {

		System.out.println("Check Commande");
		System.out.println(commande.getDate());
		return commandeService.addCommande(commande);
	}
	
	@PutMapping("/{id}")
	public Mono<Commandes> updateClient(@PathVariable("id") Long id, @RequestBody Commandes commande) {
		return commandeService.updateCommandes(commande, id);
	}
	
	@DeleteMapping("/{id}")
	public void deleteCommande(@PathVariable Long id) {
		commandeService.deleteCommandes(id);
	}
	

    @GetMapping("/total")
    public	Mono<Long> getCountCommandes() {
    	return commandeService.getCount();
    }
	
}
