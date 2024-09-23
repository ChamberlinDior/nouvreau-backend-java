package com.bus.trans.controller;

import com.bus.trans.model.Bus;
import com.bus.trans.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/buses")
public class BusController {

    @Autowired
    private BusService busService;

    // Récupérer tous les bus
    @GetMapping
    public ResponseEntity<List<Bus>> getAllBuses() {
        List<Bus> buses = busService.getAllBuses();
        return ResponseEntity.ok(buses);
    }

    // Récupérer un bus par son adresse MAC
    @GetMapping("/mac/{macAddress}")
    public ResponseEntity<Bus> getBusByMacAddress(@PathVariable String macAddress) {
        Bus bus = busService.getBusByMacAddress(macAddress);
        if (bus != null) {
            return ResponseEntity.ok(bus);
        }
        return ResponseEntity.notFound().build();
    }

    // Créer un nouveau bus
    @PostMapping("/create")
    public ResponseEntity<?> createBus(@RequestBody Bus bus) {
        try {
            Bus newBus = busService.saveBus(bus);
            return ResponseEntity.ok(newBus);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erreur lors de la création du bus : " + e.getMessage());
        }
    }

    // Mettre à jour le chauffeur et la destination par adresse MAC
    @PostMapping("/mac/{macAddress}/update-chauffeur-destination")
    public ResponseEntity<?> updateChauffeurAndDestinationByMac(
            @PathVariable String macAddress,
            @RequestParam String lastDestination,
            @RequestParam String chauffeurNom,
            @RequestParam String chauffeurUniqueNumber) {
        Bus bus = busService.updateChauffeurAndDestinationByMacAddress(macAddress, lastDestination, chauffeurNom, chauffeurUniqueNumber);
        if (bus != null) {
            return ResponseEntity.ok("Chauffeur et destination mis à jour avec succès.");
        }
        return ResponseEntity.notFound().build();
    }

    // Démarrer un trajet par adresse MAC
    @PostMapping("/mac/{macAddress}/start-trip")
    public ResponseEntity<?> startTrip(@PathVariable String macAddress,
                                       @RequestParam String lastDestination) {
        Bus bus = busService.startTrip(macAddress, lastDestination);
        if (bus != null) {
            return ResponseEntity.ok("Trajet démarré avec succès.");
        }
        return ResponseEntity.notFound().build();
    }

    // Terminer un trajet par adresse MAC
    @PostMapping("/mac/{macAddress}/end-trip")
    public ResponseEntity<?> endTrip(@PathVariable String macAddress) {
        Bus bus = busService.endTrip(macAddress);
        if (bus != null) {
            return ResponseEntity.ok("Trajet terminé avec succès.");
        }
        return ResponseEntity.notFound().build();
    }

    // Mettre à jour le niveau de batterie et l'état de charge
    @PostMapping("/mac/{macAddress}/update-battery")
    public ResponseEntity<?> updateBusBatteryLevel(
            @PathVariable String macAddress,
            @RequestParam Integer niveauBatterie,
            @RequestParam boolean isCharging) {

        Bus bus = busService.updateBatteryLevel(macAddress, niveauBatterie, isCharging);
        if (bus != null) {
            return ResponseEntity.ok("Niveau de batterie et état de charge mis à jour avec succès.");
        }
        return ResponseEntity.notFound().build();
    }
}
