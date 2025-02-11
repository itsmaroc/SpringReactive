package com.reactive.Spring.model.entity;


import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
@Table(name = "commandes")

public class Commandes {

	@Id
    @Column("commandeid")
    private Long commandeID;

    @Column("datecommande")
    private LocalDate dateCommande;	

    @Column("statut")
    private String statut;

    @Column("total")
    private Double total;

    @Column("clientid")
    private Long clientID;
		
}
