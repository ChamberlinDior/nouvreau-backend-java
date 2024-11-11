package com.bus.trans.controller;

import com.bus.trans.dto.ReservationDTO;
import com.bus.trans.model.ReservationInterurbain;
import com.bus.trans.model.TrajetInterurbain;
import com.bus.trans.service.ManifesteService;
import com.bus.trans.service.ReservationInterurbainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/reservations-interurbains")
public class ReservationInterurbainController {

    @Autowired
    private ReservationInterurbainService reservationService;

    @Autowired
    private ManifesteService manifesteService;

    // Afficher les voyages par date et destination
    @GetMapping("/voyages")
    public ResponseEntity<List<TrajetInterurbain>> getVoyagesByDateAndDestination(
            @RequestParam Date date, @RequestParam String destination) {
        List<TrajetInterurbain> voyages = reservationService.getVoyagesByDateAndDestination(date, destination);
        return ResponseEntity.ok(voyages);
    }

    // Afficher les réservations pour un trajet
    @GetMapping("/trajet/{trajetId}")
    public ResponseEntity<List<ReservationInterurbain>> getReservationsByTrajet(@PathVariable Long trajetId) {
        List<ReservationInterurbain> reservations = reservationService.getReservationsByTrajet(trajetId);
        return ResponseEntity.ok(reservations);
    }

    // Réserver une place
    @PostMapping("/trajet/{trajetId}/reserver")
    public ResponseEntity<ReservationInterurbain> reserverPlace(
            @PathVariable Long trajetId, @RequestParam String numeroPlace, @RequestBody ReservationDTO reservationDTO) {
        ReservationInterurbain reservation = reservationService.reserverPlace(trajetId, numeroPlace, reservationDTO);
        return ResponseEntity.ok(reservation);
    }

    // Générer le titre de transport
    @GetMapping("/{reservationId}/titre")
    public ResponseEntity<String> genererTitreDeTransport(@PathVariable Long reservationId) {
        String titreDeTransport = reservationService.genererTitreDeTransport(reservationId);
        return ResponseEntity.ok(titreDeTransport);
    }

    // Générer le manifeste de voyage en PDF
    @GetMapping("/trajet/{trajetId}/manifeste")
    public ResponseEntity<InputStreamResource> genererManifeste(@PathVariable Long trajetId) {
        ByteArrayInputStream manifestePDF = manifesteService.genererManifeste(trajetId);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=manifeste.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(manifestePDF));
    }
}
