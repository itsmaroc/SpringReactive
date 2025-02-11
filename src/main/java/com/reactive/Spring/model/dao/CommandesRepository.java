package com.reactive.Spring.model.dao;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.reactive.Spring.model.entity.Commandes;

import reactor.core.publisher.Flux;

public interface CommandesRepository extends R2dbcRepository<Commandes, Long> {

	Flux<Commandes> findByClientID(Long id);

}
