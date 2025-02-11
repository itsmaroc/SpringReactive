package com.reactive.Spring.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.reactive.Spring.model.entity.Clients;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommandeDTO {
    private Long id;
    
    @NotNull
    private Long clientid;
    
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate  date;
    
	private String statut;
	
	@NotNull
	@Min(0)
	private Double total;
    
    private Clients client;

    public CommandeDTO(Long id, Long clientid, LocalDate date, String statut, Double total, Clients client) {
        this.id = id;
        this.clientid = clientid;
        this.date = date;
        this.statut = statut;
        this.total = total;
        this.client = client;
    }
}

