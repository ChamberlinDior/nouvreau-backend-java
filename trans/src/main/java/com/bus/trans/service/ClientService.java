package com.bus.trans.service;

import com.bus.trans.model.Carte;
import com.bus.trans.model.Client;
import com.bus.trans.repository.CarteRepository;
import com.bus.trans.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CarteRepository carteRepository;

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client createClient(Client client) {
        // Associe chaque carte au client avant de sauvegarder
        if (client.getCartes() != null) {
            for (Carte carte : client.getCartes()) {
                carte.setClient(client);  // Définit l'association
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
            // Mettre à jour les cartes associées
            if (clientDetails.getCartes() != null) {
                for (Carte carte : clientDetails.getCartes()) {
                    carte.setClient(client);  // Définit l'association
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
        Optional<Carte> carte = carteRepository.findByRfid(rfid);
        return carte.map(Carte::getClient).orElse(null);
    }

    public Carte addCarteToClient(Long clientId, Carte carte) {
        Client client = getClientById(clientId);
        if (client != null) {
            carte.setClient(client);  // Associe le client à la carte
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
            carte.setClient(carteDetails.getClient());  // Associe le client si nécessaire
            return carteRepository.save(carte);
        }
        return null;
    }

    public List<Carte> getAllCartes() {
        return carteRepository.findAll();
    }
}
