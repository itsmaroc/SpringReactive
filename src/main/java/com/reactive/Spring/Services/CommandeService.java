package com.reactive.Spring.Services;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.reactive.Spring.dto.CommandeDTO;
import com.reactive.Spring.model.dao.ClientsRepository;
import com.reactive.Spring.model.dao.CommandesRepository;
import com.reactive.Spring.model.entity.Clients;
import com.reactive.Spring.model.entity.Commandes;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CommandeService {
	
	CommandesRepository commandesRepository;
	ClientsRepository clientsRepository;
	
	public CommandeService(CommandesRepository commandesRepository, ClientsRepository clientRepository) {
		this.commandesRepository = commandesRepository;
		this.clientsRepository = clientRepository;
	}
	
	public Flux<CommandeDTO> getAllCommandes() {
		return commandesRepository.findAll()
	            .flatMap(commande -> clientsRepository.findById(commande.getClientID()) 
		                .defaultIfEmpty(new Clients())
		                .map(client -> new CommandeDTO(
		                        commande.getCommandeID(),
		                        commande.getClientID(),
		                        commande.getDateCommande(),
		                        commande.getStatut(),
		                        commande.getTotal(),
		                        client)
		                ));
				
	}
	
	public Mono<Commandes> getById(Long id) {
		return commandesRepository.findById(id);
	}
	
	public Mono<Commandes> addCommande(CommandeDTO commande) {
		
		System.out.println("Check Service Controller :");
		System.out.println(commande.getClientid());
		Commandes commandesave = new Commandes();
		commande.setClientid(commande.getClientid());
		commande.setDate(commande.getDate());
		commande.setStatut(commande.getStatut());
		commande.setTotal(commande.getTotal());
		return commandesRepository.save(commandesave);
	}
	
	public Mono<Commandes> updateCommandes(Commandes commande, Long id) {
	    return commandesRepository.findById(id)
	        .flatMap(existingCommande -> {
	            log.info("Commande existante: {}", existingCommande);
	            log.info("Nouvelle valeur de clientID: {}", commande.getClientID());

	            if (commande.getClientID() == null) {
	                return Mono.error(new IllegalArgumentException("clientID ne peut pas être NULL"));
	            }

	            existingCommande.setClientID(commande.getClientID());
	            existingCommande.setDateCommande(commande.getDateCommande());
	            existingCommande.setStatut(commande.getStatut());
	            existingCommande.setTotal(commande.getTotal());

	            return commandesRepository.save(existingCommande);
	        });
	}

	
	public void deleteCommandes(Long id) {
		commandesRepository.existsById(id)
				.flatMap(commade -> commandesRepository.deleteById(id))
				.switchIfEmpty(Mono.error(new NotFoundException()));
	}
	
	public Flux<Commandes> getCommandesByIdClient(Long id) {
		return commandesRepository.findByClientID(id);
	}

	public Mono<Long> getCount() {
		return commandesRepository.count();
	}

}
