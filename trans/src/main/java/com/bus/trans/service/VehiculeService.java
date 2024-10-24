package com.bus.trans.service;

import com.bus.trans.model.Vehicule;
import com.bus.trans.repository.VehiculeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehiculeService {

    @Autowired
    private VehiculeRepository vehiculeRepository;

    public List<Vehicule> getAllVehicules() {
        return vehiculeRepository.findAll();
    }

    public Vehicule saveVehicule(Vehicule vehicule) {
        return vehiculeRepository.save(vehicule);
    }

    public Vehicule getVehiculeByImmatriculation(String immatriculation) {
        return vehiculeRepository.findByImmatriculation(immatriculation);
    }

    public Vehicule getVehiculeByMacAddress(String macAddress) {
        return vehiculeRepository.findByMacAddress(macAddress);
    }

    public Vehicule getVehiculeById(Long id) {
        return vehiculeRepository.findById(id).orElse(null);
    }

    public Vehicule updateVehicule(String immatriculation, Vehicule vehiculeDetails) {
        Vehicule vehicule = vehiculeRepository.findByImmatriculation(immatriculation);
        if (vehicule != null) {
            vehicule.setTrajet(vehiculeDetails.getTrajet());
            vehicule.setChauffeur(vehiculeDetails.getChauffeur());
            vehicule.setMacAddress(vehiculeDetails.getMacAddress());
            vehicule.setMarque(vehiculeDetails.getMarque());
            vehicule.setModele(vehiculeDetails.getModele());
            return vehiculeRepository.save(vehicule);
        }
        return null;
    }

    public void deleteVehicule(Long id) {
        vehiculeRepository.deleteById(id);
    }
}
