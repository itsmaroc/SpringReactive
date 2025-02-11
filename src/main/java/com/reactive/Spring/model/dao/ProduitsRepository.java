package com.reactive.Spring.model.dao;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.reactive.Spring.model.entity.Produits;

public interface ProduitsRepository extends R2dbcRepository<Produits, Long> {

}
