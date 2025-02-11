package com.reactive.Spring.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reactive.Spring.Services.ClientService;
import com.reactive.Spring.model.entity.Clients;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/clients")
public class reactiveController {
	
	private final ClientService clientService;
	
	public reactiveController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("")
    public Flux<Clients> listClients() {
        return clientService.getAllClients();
    }
    
    @GetMapping("/{id}")
    public Mono<Clients> getClient(@PathVariable(value = "id") Long id) {
    	return clientService.getClientById(id);
    }
    
    @PutMapping("/{id}")
    private Mono<Clients> updateClient(@PathVariable(value = "id") Long id, @RequestBody Clients updatedClient) {
		return clientService.updateClient(id, updatedClient);
	}
    
    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable(value = "id") Long id) {
    	clientService.deleteClient(id);
    }
    
    @GetMapping("/criteria/{search}")
    public Flux<Clients> getClientByCriteria(@PathVariable("search") String search) {
    	return clientService.getClientByCriteria(search);
    }
    
    @GetMapping("/total")
    public	Mono<Long> getCountClients() {
    	return clientService.getCount();
    }
}
 