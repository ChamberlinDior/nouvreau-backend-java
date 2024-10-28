package com.bus.trans.service;

import com.bus.trans.model.Carte;
import com.bus.trans.model.Client;
import com.bus.trans.repository.CarteRepository;
import com.bus.trans.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private static final Logger logger = LoggerFactory.getLogger(ClientService.class);

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CarteRepository carteRepository;

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client createClient(Client client) {
        if (client.getCartes() != null) {
            for (Carte carte : client.getCartes()) {
                carte.setClient(client);
            }
        }
        return clientRepository.save(client);
    }

    public Client updateClient(Long id, Client clientDetails) {
        Optional<Client> clientOptional = clientRepository.findById(id);
        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();
            client.setNom(clientDetails.getNom());
            client.setPrenom(clientDetails.getPrenom());
            client.setQuartier(clientDetails.getQuartier());
            client.setVille(clientDetails.getVille());
            client.setNomAgent(clientDetails.getNomAgent());

            if (clientDetails.getCartes() != null) {
                for (Carte carte : clientDetails.getCartes()) {
                    carte.setClient(client);
                }
            }
            return clientRepository.save(client);
        } else {
            throw new IllegalArgumentException("Client non trouvé avec l'ID fourni");
        }
    }

    public Client getClientById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    public Client getClientByRFID(String rfid) {
        logger.info("Recherche de client par RFID: {}", rfid);
        Optional<Client> clientOpt = clientRepository.findClientByRFID(rfid.toUpperCase().trim());
        if (clientOpt.isPresent()) {
            logger.info("Client trouvé pour RFID: {}", rfid);
            return clientOpt.get();
        } else {
            logger.warn("Aucun client trouvé pour RFID: {}", rfid);
            return null;
        }
    }

    public Carte addCarteToClient(Long clientId, Carte carte) {
        Client client = getClientById(clientId);
        if (client != null) {
            carte.setClient(client);
            return carteRepository.save(carte);
        } else {
            throw new IllegalArgumentException("Client non trouvé avec l'ID fourni.");
        }
    }

    public Carte updateCarte(Long carteId, Carte carteDetails) {
        Optional<Carte> carteOptional = carteRepository.findById(carteId);
        if (carteOptional.isPresent()) {
            Carte carte = carteOptional.get();
            carte.setActive(carteDetails.isActive());
            carte.setDateExpiration(carteDetails.getDateExpiration());
            carte.setClient(carteDetails.getClient());
            return carteRepository.save(carte);
        }
        return null;
    }

    public List<Carte> getAllCartes() {
        return carteRepository.findAll();
    }

    public Carte getCarteById(Long carteId) {
        return carteRepository.findById(carteId).orElse(null);
    }
}
