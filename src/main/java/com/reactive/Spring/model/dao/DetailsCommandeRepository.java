package com.reactive.Spring.model.dao;

import org.reactivestreams.Publisher;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.reactive.Spring.model.entity.DetailsCommandes;

import reactor.core.publisher.Flux;

public interface DetailsCommandeRepository extends R2dbcRepository<DetailsCommandes, Long>{

	Flux<DetailsCommandes> findByCommandeID(Long id);

	Publisher<? extends Object> deleteByCommandeID(Long id);

}
