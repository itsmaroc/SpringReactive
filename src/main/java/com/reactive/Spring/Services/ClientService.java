package com.reactive.Spring.Services;

import org.springframework.stereotype.Service;

import com.reactive.Spring.model.dao.ClientsRepository;
import com.reactive.Spring.model.entity.Clients;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClientService {

	private final ClientsRepository clientsRepository;

    public ClientService(ClientsRepository clientsRepository) {
        this.clientsRepository = clientsRepository;
    }

   
    public Flux<Clients> getAllClients() {
        return clientsRepository.findAll();
    }

    public Mono<Clients> getClientById(Long id) {
        return clientsRepository.findById(id);
    }
			
    public Mono<Clients> createClient(Clients client) {
        return clientsRepository.save(client);
    }

    public Mono<Clients> updateClient(Long id, Clients updatedClient) {
        return clientsRepository.findById(id)
                .flatMap(existingClient -> {
                    existingClient.setNom(updatedClient.getNom());
                    existingClient.setPrenom(updatedClient.getPrenom());
                    existingClient.setTelephone(updatedClient.getTelephone());
                    existingClient.setEmail(updatedClient.getEmail());
                    existingClient.setAdresse(updatedClient.getAdresse());
                    return clientsRepository.save(existingClient);
                });
    }

    public Mono<Void> deleteClient(Long id) {
        return clientsRepository.deleteById(id);
    }
    
    public Flux<Clients> getClientByCriteria(String search) {
    	return clientsRepository.findByNomContainsIgnoreCase(search);
    }


	public Mono<Long> getCount() {
		return clientsRepository.count();
	}
	
}
