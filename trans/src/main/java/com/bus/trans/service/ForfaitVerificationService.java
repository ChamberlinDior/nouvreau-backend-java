package com.bus.trans.service;
import com.bus.trans.dto.ForfaitVerificationDTO;
import com.bus.trans.model.ForfaitVerification;
import com.bus.trans.repository.ForfaitVerificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForfaitVerificationService {

    @Autowired
    private ForfaitVerificationRepository forfaitVerificationRepository;

    // Enregistrer une nouvelle vérification de forfait
    public ForfaitVerification saveForfaitVerification(ForfaitVerificationDTO forfaitVerificationDTO) {
        ForfaitVerification verification = new ForfaitVerification(
                forfaitVerificationDTO.getNomClient(),
                forfaitVerificationDTO.getRfid(),
                forfaitVerificationDTO.getStatutForfait(),
                forfaitVerificationDTO.getAndroidId(),
                forfaitVerificationDTO.getRoleUtilisateur(),
                forfaitVerificationDTO.getNomUtilisateur()  // Capture le nom de l'utilisateur
        );

        return forfaitVerificationRepository.save(verification);
    }

    // Récupérer toutes les vérifications de forfaits
    public List<ForfaitVerification> getAllForfaitVerifications() {
        return forfaitVerificationRepository.findAll();
    }
}
