package com.bus.trans.controller;

import com.bus.trans.dto.ClientDTO;
import com.bus.trans.model.Carte;
import com.bus.trans.model.Client;
import com.bus.trans.service.ClientService;

import com.bus.trans.util.DTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    // Obtenir un client par son ID et retourner un DTO
    @GetMapping("/{id}")
    public ClientDTO getClientById(@PathVariable Long id) {
        Client client = clientService.getClientById(id);
        if (client == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Client non trouvé");
        }
        return DTOConverter.convertToClientDTO(client);
    }

    // Ajouter une carte à un client
    @PostMapping("/{clientId}/cartes")
    public Carte addCarteToClient(@PathVariable Long clientId, @RequestBody Carte carte) {
        Carte newCarte = clientService.addCarteToClient(clientId, carte);
        if (newCarte == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Client non trouvé");
        }
        return newCarte;
    }

    // Mettre à jour une carte (désactiver ou activer)
    @PutMapping("/cartes/{carteId}")
    public Carte updateCarte(@PathVariable Long carteId, @RequestBody Carte carteDetails) {
        Carte updatedCarte = clientService.updateCarte(carteId, carteDetails);
        if (updatedCarte == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Carte non trouvée");
        }
        return updatedCarte;
    }

    // Obtenir un client par son RFID
    @GetMapping("/rfid/{rfid}")
    public Client getClientByRFID(@PathVariable String rfid) {
        Client client = clientService.getClientByRFID(rfid);
        if (client == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Client non trouvé pour ce RFID");
        }
        return client;
    }
}
