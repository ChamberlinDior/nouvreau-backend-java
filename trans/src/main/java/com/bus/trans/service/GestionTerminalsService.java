package com.bus.trans.service;

import com.bus.trans.dto.TerminalDTO;
import com.bus.trans.model.GestionTerminals;
import com.bus.trans.repository.GestionTerminalsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class GestionTerminalsService {

    @Autowired
    private GestionTerminalsRepository gestionTerminalsRepository;

    // Sauvegarder les informations d'un terminal
    public GestionTerminals saveTerminalInfo(TerminalDTO terminalDTO) {
        GestionTerminals gestionTerminals = new GestionTerminals();
        gestionTerminals.setAndroidId(terminalDTO.getAndroidId());
        gestionTerminals.setBatteryLevel(terminalDTO.getBatteryLevel());
        gestionTerminals.setTerminalType(terminalDTO.getTerminalType());
        gestionTerminals.setUserName(terminalDTO.getUserName());
        gestionTerminals.setUserUniqueId(terminalDTO.getUserUniqueId());
        gestionTerminals.setConnectionTime(LocalDateTime.now());

        // Trajet info
        gestionTerminals.setTrajetChauffeurName(terminalDTO.getTrajetChauffeurName());
        gestionTerminals.setTrajetChauffeurUniqueId(terminalDTO.getTrajetChauffeurUniqueId());
        gestionTerminals.setTrajetDestination(terminalDTO.getTrajetDestination());
        gestionTerminals.setTrajetStartTime(terminalDTO.getTrajetStartTime());

        // Forfait info
        gestionTerminals.setClientName(terminalDTO.getClientName());
        gestionTerminals.setRfid(terminalDTO.getRfid());
        gestionTerminals.setForfaitType(terminalDTO.getForfaitType());
        gestionTerminals.setForfaitStatus(terminalDTO.getForfaitStatus());
        gestionTerminals.setVerificationTime(terminalDTO.getVerificationTime());

        return gestionTerminalsRepository.save(gestionTerminals);
    }

    // Récupérer un terminal par ID
    public Optional<GestionTerminals> getTerminalById(Long id) {
        return gestionTerminalsRepository.findById(id);
    }

    // Récupérer tous les terminaux
    public List<GestionTerminals> getAllTerminals() {
        return gestionTerminalsRepository.findAll();
    }

    // Mettre à jour un terminal existant
    public GestionTerminals updateTerminal(Long id, TerminalDTO terminalDTO) {
        Optional<GestionTerminals> terminalOptional = gestionTerminalsRepository.findById(id);
        if (terminalOptional.isPresent()) {
            GestionTerminals terminal = terminalOptional.get();
            terminal.setAndroidId(terminalDTO.getAndroidId());
            terminal.setBatteryLevel(terminalDTO.getBatteryLevel());
            terminal.setTerminalType(terminalDTO.getTerminalType());
            terminal.setUserName(terminalDTO.getUserName());
            terminal.setUserUniqueId(terminalDTO.getUserUniqueId());

            terminal.setClientName(terminalDTO.getClientName());
            terminal.setRfid(terminalDTO.getRfid());
            terminal.setForfaitType(terminalDTO.getForfaitType());
            terminal.setForfaitStatus(terminalDTO.getForfaitStatus());
            terminal.setVerificationTime(terminalDTO.getVerificationTime());

            terminal.setTrajetChauffeurName(terminalDTO.getTrajetChauffeurName());
            terminal.setTrajetChauffeurUniqueId(terminalDTO.getTrajetChauffeurUniqueId());
            terminal.setTrajetDestination(terminalDTO.getTrajetDestination());
            terminal.setTrajetStartTime(terminalDTO.getTrajetStartTime());

            return gestionTerminalsRepository.save(terminal);
        } else {
            return null;
        }
    }

    // Supprimer un terminal
    public void deleteTerminal(Long id) {
        gestionTerminalsRepository.deleteById(id);
    }
}
