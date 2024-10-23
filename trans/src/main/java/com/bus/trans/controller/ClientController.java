package com.bus.trans.controller;

import com.bus.trans.dto.CarteDTO;
import com.bus.trans.dto.ClientDTO;
import com.bus.trans.model.Carte;
import com.bus.trans.model.Client;
import com.bus.trans.service.ClientService;
import com.bus.trans.util.DTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public ResponseEntity<List<ClientDTO>> getAllClients() {
        List<Client> clients = clientService.getAllClients();
        List<ClientDTO> clientDTOs = DTOConverter.convertToClientDTOs(clients);
        return ResponseEntity.ok(clientDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable Long id) {
        Client client = clientService.getClientById(id);
        if (client == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Client non trouvé");
        }
        return ResponseEntity.ok(DTOConverter.convertToClientDTO(client));
    }

    @PostMapping("/{clientId}/cartes")
    public ResponseEntity<CarteDTO> addCarteToClient(@PathVariable Long clientId, @RequestBody CarteDTO carteDTO) {
        Carte carte = DTOConverter.convertToCarte(carteDTO);
        carte.setClient(clientService.getClientById(clientId));  // Associe le client à la carte
        Carte newCarte = clientService.addCarteToClient(clientId, carte);
        return ResponseEntity.ok(DTOConverter.convertToCarteDTO(newCarte));
    }

    @PutMapping("/cartes/{carteId}")
    public ResponseEntity<CarteDTO> updateCarte(@PathVariable Long carteId, @RequestBody CarteDTO carteDetails) {
        Carte updatedCarte = clientService.updateCarte(carteId, DTOConverter.convertToCarte(carteDetails));
        if (updatedCarte == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Carte non trouvée");
        }
        return ResponseEntity.ok(DTOConverter.convertToCarteDTO(updatedCarte));
    }

    @GetMapping("/rfid/{rfid}")
    public ResponseEntity<ClientDTO> getClientByRFID(@PathVariable String rfid) {
        Client client = clientService.getClientByRFID(rfid);
        if (client == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Client non trouvé pour ce RFID");
        }
        // Convert to DTO to avoid circular references
        return ResponseEntity.ok(DTOConverter.convertToClientDTO(client));
    }

    @PostMapping
    public ResponseEntity<ClientDTO> createClient(@RequestBody ClientDTO clientDTO) {
        Client client = DTOConverter.convertToClient(clientDTO);
        Client newClient = clientService.createClient(client);
        return new ResponseEntity<>(DTOConverter.convertToClientDTO(newClient), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> updateClient(@PathVariable Long id, @RequestBody ClientDTO clientDTO) {
        try {
            Client updatedClient = clientService.updateClient(id, DTOConverter.convertToClient(clientDTO));
            return ResponseEntity.ok(DTOConverter.convertToClientDTO(updatedClient));
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Client non trouvé");
        }
    }

    @GetMapping("/cartes")
    public ResponseEntity<List<CarteDTO>> getAllCartes() {
        List<Carte> cartes = clientService.getAllCartes();
        List<CarteDTO> carteDTOs = DTOConverter.convertToCarteDTOs(cartes);
        return ResponseEntity.ok(carteDTOs);
    }
}
