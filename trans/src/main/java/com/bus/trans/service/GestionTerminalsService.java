package com.bus.trans.service;

import com.bus.trans.dto.TerminalDTO;
import com.bus.trans.model.GestionTerminals;
import com.bus.trans.repository.GestionTerminalsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class GestionTerminalsService {

    @Autowired
    private GestionTerminalsRepository gestionTerminalsRepository;

    @Transactional  // Ajoute cette annotation pour s'assurer que les transactions sont bien gérées
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
}
