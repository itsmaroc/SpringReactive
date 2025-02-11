package com.reactive.Spring.model.entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
@Table(name = "clients")
public class Clients {

	@Id
	private Long clientid;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String adresse;
}
