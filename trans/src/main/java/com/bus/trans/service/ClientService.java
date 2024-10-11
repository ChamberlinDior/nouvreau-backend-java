package com.bus.trans.service;

import com.bus.trans.model.Carte;
import com.bus.trans.model.Client;
import com.bus.trans.repository.CarteRepository;
import com.bus.trans.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CarteRepository carteRepository;

    // Obtenir tous les clients
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    // Sauvegarder un nouveau client
    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    // Obtenir un client par ID
    public Client getClientById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    // Obtenir un client par RFID
    public Client getClientByRFID(String rfid) {
        Optional<Carte> carte = carteRepository.findByRfid(rfid);
        return carte.map(Carte::getClient).orElse(null);
    }

    // Ajouter une nouvelle carte à un client existant
    public Carte addCarteToClient(Long clientId, Carte carte) {
        Client client = getClientById(clientId);
        if (client != null) {
            carte.setClient(client);
            return carteRepository.save(carte);
        }
        return null;
    }

    // Mise à jour d'une carte (désactivation ou changement de date de validité)
    public Carte updateCarte(Long carteId, Carte carteDetails) {
        Optional<Carte> carteOptional = carteRepository.findById(carteId);
        if (carteOptional.isPresent()) {
            Carte carte = carteOptional.get();
            carte.setActive(carteDetails.isActive());
            carte.setDateExpiration(carteDetails.getDateExpiration());
            return carteRepository.save(carte);
        }
        return null;
    }
}
