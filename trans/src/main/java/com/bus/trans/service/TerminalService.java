package com.bus.trans.service;


import com.bus.trans.model.Terminal;
import com.bus.trans.repository.TerminalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TerminalService {

    @Autowired
    private TerminalRepository terminalRepository;

    // Récupérer tous les terminaux
    public List<Terminal> getAllTerminals() {
        return terminalRepository.findAll();
    }

    // Enregistrer un terminal
    public Terminal saveTerminal(Terminal terminal) {
        return terminalRepository.save(terminal);
    }

    // Récupérer un terminal par son adresse MAC
    public Terminal getTerminalByMacAddress(String macAddress) {
        return terminalRepository.findByMacAddress(macAddress);
    }

    // Mettre à jour un terminal par son adresse MAC
    public Terminal updateTerminal(String macAddress, Terminal terminalDetails) {
        Terminal terminal = terminalRepository.findByMacAddress(macAddress);
        if (terminal != null) {
            terminal.setTerminalId(terminalDetails.getTerminalId());
            terminal.setTypeTerminal(terminalDetails.getTypeTerminal());
            terminal.setBusMatricule(terminalDetails.getBusMatricule());
            return terminalRepository.save(terminal);
        }
        return null;
    }

    // Supprimer un terminal par ID
    public void deleteTerminal(Long id) {
        terminalRepository.deleteById(id);
    }
}