package com.bus.trans.service.impl;

import com.bus.trans.model.VehiculeInterurbain;
import com.bus.trans.repository.VehiculeInterurbainRepository;
import com.bus.trans.service.VehiculeInterurbainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehiculeInterurbainServiceImpl implements VehiculeInterurbainService {

    @Autowired
    private VehiculeInterurbainRepository vehiculeRepository;

    @Override
    public VehiculeInterurbain createVehicule(VehiculeInterurbain vehicule) {
        return vehiculeRepository.save(vehicule);
    }

    @Override
    public VehiculeInterurbain getVehiculeById(Long id) {
        return vehiculeRepository.findById(id).orElse(null);
    }

    @Override
    public List<VehiculeInterurbain> getAllVehicules() {
        return vehiculeRepository.findAll();
    }

    @Override
    public VehiculeInterurbain updateVehicule(Long id, VehiculeInterurbain vehiculeDetails) {
        VehiculeInterurbain vehicule = vehiculeRepository.findById(id).orElse(null);
        if (vehicule != null) {
            vehicule.setImmatriculation(vehiculeDetails.getImmatriculation());
            vehicule.setCapacitePassagers(vehiculeDetails.getCapacitePassagers());
            vehicule.setMarque(vehiculeDetails.getMarque());
            vehicule.setModele(vehiculeDetails.getModele());
            return vehiculeRepository.save(vehicule);
        }
        return null;
    }

    @Override
    public void deleteVehicule(Long id) {
        vehiculeRepository.deleteById(id);
    }
}
