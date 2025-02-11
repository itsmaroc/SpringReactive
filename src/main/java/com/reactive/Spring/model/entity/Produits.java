package com.reactive.Spring.model.entity;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
@Table(name = "produits")
public class Produits {
	
	@Id
	private long produitID;

	private String nomProduit;

	private String description;

	private Double prix;

	private BigDecimal stock;

}
