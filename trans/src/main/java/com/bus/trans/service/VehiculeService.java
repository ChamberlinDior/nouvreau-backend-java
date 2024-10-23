package com.bus.trans.service;


import com.bus.trans.model.Vehicule;
import com.bus.trans.repository.VehiculeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehiculeService {

    @Autowired
    private VehiculeRepository vehiculeRepository;

    // Récupérer tous les véhicules
    public List<Vehicule> getAllVehicules() {
        return vehiculeRepository.findAll();
    }

    // Créer un nouveau véhicule
    public Vehicule saveVehicule(Vehicule vehicule) {
        return vehiculeRepository.save(vehicule);
    }

    // Récupérer un véhicule par immatriculation
    public Vehicule getVehiculeByImmatriculation(String immatriculation) {
        return vehiculeRepository.findByImmatriculation(immatriculation);
    }

    // Récupérer un véhicule par adresse MAC
    public Vehicule getVehiculeByMacAddress(String macAddress) {
        return vehiculeRepository.findByMacAddress(macAddress);
    }

    // Mettre à jour un véhicule
    public Vehicule updateVehicule(String immatriculation, Vehicule vehiculeDetails) {
        Vehicule vehicule = vehiculeRepository.findByImmatriculation(immatriculation);
        if (vehicule != null) {
            vehicule.setTrajet(vehiculeDetails.getTrajet());
            vehicule.setChauffeur(vehiculeDetails.getChauffeur());
            vehicule.setMacAddress(vehiculeDetails.getMacAddress());
            return vehiculeRepository.save(vehicule);
        }
        return null;
    }

    // Supprimer un véhicule par ID
    public void deleteVehicule(Long id) {
        vehiculeRepository.deleteById(id);
    }
}