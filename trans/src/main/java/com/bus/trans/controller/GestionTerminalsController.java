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
@RequestMapping("/api/terminaux")
public class GestionTerminalsController {

    @Autowired
    private GestionTerminalsService gestionTerminalsService;

    @PostMapping
    public ResponseEntity<GestionTerminals> saveTerminalInfo(@RequestBody TerminalDTO terminalDTO) {
        GestionTerminals savedTerminal = gestionTerminalsService.saveTerminalInfo(terminalDTO);
        return ResponseEntity.ok(savedTerminal);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GestionTerminals> getTerminalById(@PathVariable Long id) {
        Optional<GestionTerminals> gestionTerminals = gestionTerminalsService.getTerminalById(id);
        return gestionTerminals.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<GestionTerminals>> getAllTerminals() {
        List<GestionTerminals> gestionTerminalsList = gestionTerminalsService.getAllTerminals();
        return ResponseEntity.ok(gestionTerminalsList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GestionTerminals> updateTerminal(@PathVariable Long id, @RequestBody TerminalDTO terminalDTO) {
        GestionTerminals updatedTerminal = gestionTerminalsService.updateTerminal(id, terminalDTO);
        return updatedTerminal != null ? ResponseEntity.ok(updatedTerminal) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTerminal(@PathVariable Long id) {
        gestionTerminalsService.deleteTerminal(id);
        return ResponseEntity.noContent().build();
    }
}
