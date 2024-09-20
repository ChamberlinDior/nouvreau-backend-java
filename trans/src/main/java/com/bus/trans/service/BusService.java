package com.bus.trans.service;
import com.bus.trans.model.Bus;
import com.bus.trans.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusService {

    @Autowired
    private BusRepository busRepository;

    // Récupérer tous les bus
    public List<Bus> getAllBuses() {
        return busRepository.findAll();
    }

    // Enregistrer un bus
    public Bus saveBus(Bus bus) {
        return busRepository.save(bus);
    }

    // Récupérer un bus par son adresse MAC
    public Bus getBusByMacAddress(String macAddress) {
        return busRepository.findByMacAddress(macAddress);
    }

    // Mettre à jour le chauffeur et la destination
    public Bus updateChauffeurAndDestinationByMacAddress(String macAddress, String lastDestination, String chauffeurNom, String chauffeurUniqueNumber) {
        Bus bus = busRepository.findByMacAddress(macAddress);
        if (bus != null) {
            bus.setLastDestination(lastDestination);
            bus.setChauffeurNom(chauffeurNom);
            bus.setChauffeurUniqueNumber(chauffeurUniqueNumber);
            return busRepository.save(bus);
        }
        return null;
    }

    // Démarrer un trajet
    public Bus startTrip(String macAddress, String lastDestination) {
        Bus bus = busRepository.findByMacAddress(macAddress);
        if (bus != null) {
            bus.setDebutTrajet(new java.util.Date());
            bus.setLastDestination(lastDestination);
            return busRepository.save(bus);
        }
        return null;
    }

    // Terminer un trajet
    public Bus endTrip(String macAddress) {
        Bus bus = busRepository.findByMacAddress(macAddress);
        if (bus != null) {
            bus.setFinTrajet(new java.util.Date());
            return busRepository.save(bus);
        }
        return null;
    }

    // Mettre à jour le niveau de batterie et l'état de charge
    public Bus updateBatteryLevel(String macAddress, Integer niveauBatterie, boolean isCharging) {
        Bus bus = busRepository.findByMacAddress(macAddress);
        if (bus != null) {
            bus.setNiveauBatterie(niveauBatterie);
            bus.setCharging(isCharging);  // Mise à jour de l'état de charge
            return busRepository.save(bus);
        }
        return null;
    }
}
