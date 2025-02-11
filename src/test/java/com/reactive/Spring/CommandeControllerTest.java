package com.reactive.Spring;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.reactive.Spring.Services.CommandeService;
import com.reactive.Spring.controller.CommandeController;
import com.reactive.Spring.dto.CommandeDTO;
import com.reactive.Spring.model.entity.Commandes;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

class CommandeControllerTest {

    private WebTestClient webTestClient;
    private CommandeService commandeService;
    
    @BeforeEach
    void setUp() {
        commandeService = Mockito.mock(CommandeService.class);
        CommandeController controller = new CommandeController(commandeService);
        webTestClient = WebTestClient.bindToController(controller).build();
    }

    @Test
    void getAllCommandes_ShouldReturnFlux() {
        CommandeDTO dto = new CommandeDTO(1L, 123L, LocalDate.now(), "PENDING", 99.99, null);
        Mockito.when(commandeService.getAllCommandes()).thenReturn(Flux.just(dto));

        webTestClient.get().uri("/")
            .exchange()
            .expectStatus().isOk()
            .expectBodyList(CommandeDTO.class)
            .hasSize(1);
    }

    @Test
    void getCommandeById_ValidId_ShouldReturnCommande() {
        Commandes commande = new Commandes();
        commande.setCommandeID(1L);
        commande.setDateCommande(LocalDate.now());
        commande.setStatut("PENDING");
        commande.setTotal(99.99);
        commande.setClientID(123L);
        Mockito.when(commandeService.getById(1L)).thenReturn(Mono.just(commande));

        webTestClient.get().uri("/1")
            .exchange()
            .expectStatus().isOk()
            .expectBody()
            .jsonPath("$.commandeID").isEqualTo(1);
    }

    @Test
    void addCommande_ValidInput_ShouldCreate() {
        CommandeDTO dto = new CommandeDTO(null, 123L, LocalDate.now(), "PENDING", 99.99, null);
        Commandes saved = new Commandes();
        saved.setCommandeID(1L);
        saved.setDateCommande(dto.getDate());
        saved.setStatut(dto.getStatut());
        saved.setTotal(dto.getTotal());
        saved.setClientID(dto.getClientid());
        
        Mockito.when(commandeService.addCommande(dto)).thenReturn(Mono.just(saved));

        webTestClient.post().uri("/addcommande")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(dto)
            .exchange()
            .expectStatus().isOk()
            .expectBody()
            .jsonPath("$.clientID").isEqualTo(123);
    }

    @Test
    void addCommande_InvalidInput_ShouldReject() {
        // Test avec clientid null
        CommandeDTO invalidDto = new CommandeDTO(null, null, LocalDate.now(), "PENDING", 99.99, null);

        webTestClient.post().uri("/addcommande")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(invalidDto)
            .exchange()
            .expectStatus().isBadRequest();
    }

    @Test
    void deleteCommande_ShouldCallService() {
        Mockito.doNothing().when(commandeService).deleteCommandes(1L);

        webTestClient.delete().uri("/1")
            .exchange()
            .expectStatus().isNoContent();
    }
}
