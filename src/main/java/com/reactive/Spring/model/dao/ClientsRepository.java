package com.reactive.Spring.model.dao;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.reactive.Spring.model.entity.Clients;

import reactor.core.publisher.Flux;

@Repository
public interface ClientsRepository extends R2dbcRepository<Clients, Long> {
	
	Flux<Clients> findByNomContainsIgnoreCase(String nom);	
}
