package com.bus.trans.controller;

import com.bus.trans.dto.VehiculeDTO;
import com.bus.trans.model.Vehicule;
import com.bus.trans.service.VehiculeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicules")
public class VehiculeController {

    @Autowired
    private VehiculeService vehiculeService;

    @GetMapping
    public ResponseEntity<List<Vehicule>> getAllVehicules() {
        List<Vehicule> vehicules = vehiculeService.getAllVehicules();
        return ResponseEntity.ok(vehicules);
    }

    @PostMapping("/create")
    public ResponseEntity<Vehicule> createVehicule(@RequestBody VehiculeDTO vehiculeDTO) {
        try {
            Vehicule vehicule = new Vehicule();
            vehicule.setImmatriculation(vehiculeDTO.getImmatriculation());
            vehicule.setTrajet(vehiculeDTO.getTrajet());
            vehicule.setChauffeur(vehiculeDTO.getChauffeur());
            vehicule.setMacAddress(vehiculeDTO.getMacAddress());
            vehicule.setMarque(vehiculeDTO.getMarque());
            vehicule.setModele(vehiculeDTO.getModele());

            Vehicule newVehicule = vehiculeService.saveVehicule(vehicule);
            return ResponseEntity.ok(newVehicule);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicule> getVehiculeById(@PathVariable Long id) {
        Vehicule vehicule = vehiculeService.getVehiculeById(id);
        if (vehicule != null) {
            return ResponseEntity.ok(vehicule);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/immatriculation/{immatriculation}")
    public ResponseEntity<Vehicule> updateVehicule(@PathVariable String immatriculation, @RequestBody VehiculeDTO vehiculeDTO) {
        try {
            Vehicule vehiculeDetails = new Vehicule();
            vehiculeDetails.setTrajet(vehiculeDTO.getTrajet());
            vehiculeDetails.setChauffeur(vehiculeDTO.getChauffeur());
            vehiculeDetails.setMacAddress(vehiculeDTO.getMacAddress());
            vehiculeDetails.setMarque(vehiculeDTO.getMarque());
            vehiculeDetails.setModele(vehiculeDTO.getModele());

            Vehicule updatedVehicule = vehiculeService.updateVehicule(immatriculation, vehiculeDetails);
            return ResponseEntity.ok(updatedVehicule);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteVehicule(@PathVariable Long id) {
        vehiculeService.deleteVehicule(id);
        return ResponseEntity.ok().build();
    }
}
