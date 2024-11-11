package com.bus.trans.controller;

import com.bus.trans.model.VehiculeInterurbain;
import com.bus.trans.service.VehiculeInterurbainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicules-interurbains")
public class VehiculeInterurbainController {

    @Autowired
    private VehiculeInterurbainService vehiculeService;

    @PostMapping
    public ResponseEntity<VehiculeInterurbain> createVehicule(@RequestBody VehiculeInterurbain vehicule) {
        return ResponseEntity.ok(vehiculeService.createVehicule(vehicule));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehiculeInterurbain> getVehiculeById(@PathVariable Long id) {
        VehiculeInterurbain vehicule = vehiculeService.getVehiculeById(id);
        return vehicule != null ? ResponseEntity.ok(vehicule) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<VehiculeInterurbain>> getAllVehicules() {
        return ResponseEntity.ok(vehiculeService.getAllVehicules());
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehiculeInterurbain> updateVehicule(
            @PathVariable Long id, @RequestBody VehiculeInterurbain vehiculeDetails) {
        VehiculeInterurbain updatedVehicule = vehiculeService.updateVehicule(id, vehiculeDetails);
        return updatedVehicule != null ? ResponseEntity.ok(updatedVehicule) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicule(@PathVariable Long id) {
        vehiculeService.deleteVehicule(id);
        return ResponseEntity.noContent().build();
    }
}
