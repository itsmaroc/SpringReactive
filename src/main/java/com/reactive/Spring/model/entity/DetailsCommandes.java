package com.reactive.Spring.model.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
@Table(name = "detailsCommandes")

public class DetailsCommandes {

	@Id
	private long detailID;
	
	private long commandeID;
	
	private long produitID;
	
	private Integer quantite;
	
	private Double prixUnitaire;
	
	private Integer SousTotal;
}
