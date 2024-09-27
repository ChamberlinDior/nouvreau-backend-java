package com.bus.trans.controller;

import com.bus.trans.dto.TerminalDTO;
import com.bus.trans.model.GestionTerminals;
import com.bus.trans.service.GestionTerminalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/gestion-terminaux")
public class GestionTerminalsController {

    @Autowired
    private GestionTerminalsService gestionTerminalsService;

    // Créer un terminal et enregistrer les infos
    @PostMapping
    public ResponseEntity<GestionTerminals> saveTerminalInfo(@RequestBody TerminalDTO terminalDTO) {
        GestionTerminals gestionTerminals = gestionTerminalsService.saveTerminalInfo(terminalDTO);
        return ResponseEntity.ok(gestionTerminals);
    }

    // Récupérer un terminal par ID
    @GetMapping("/{id}")
    public ResponseEntity<GestionTerminals> getTerminalById(@PathVariable Long id) {
        Optional<GestionTerminals> gestionTerminals = gestionTerminalsService.getTerminalById(id);
        if (gestionTerminals.isPresent()) {
            return ResponseEntity.ok(gestionTerminals.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Récupérer tous les terminaux
    @GetMapping
    public ResponseEntity<List<GestionTerminals>> getAllTerminals() {
        List<GestionTerminals> gestionTerminalsList = gestionTerminalsService.getAllTerminals();
        return ResponseEntity.ok(gestionTerminalsList);
    }

    // Mettre à jour un terminal
    @PutMapping("/{id}")
    public ResponseEntity<GestionTerminals> updateTerminal(@PathVariable Long id, @RequestBody TerminalDTO terminalDTO) {
        GestionTerminals updatedTerminal = gestionTerminalsService.updateTerminal(id, terminalDTO);
        if (updatedTerminal != null) {
            return ResponseEntity.ok(updatedTerminal);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Supprimer un terminal
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTerminal(@PathVariable Long id) {
        gestionTerminalsService.deleteTerminal(id);
        return ResponseEntity.noContent().build();
    }
}
