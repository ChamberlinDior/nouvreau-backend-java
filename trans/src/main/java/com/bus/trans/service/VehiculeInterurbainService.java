package com.bus.trans.service;

import com.bus.trans.model.VehiculeInterurbain;
import java.util.List;

public interface VehiculeInterurbainService {
    VehiculeInterurbain createVehicule(VehiculeInterurbain vehicule);
    VehiculeInterurbain getVehiculeById(Long id);
    List<VehiculeInterurbain> getAllVehicules();
    VehiculeInterurbain updateVehicule(Long id, VehiculeInterurbain vehicule);
    void deleteVehicule(Long id);
}
