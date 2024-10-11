package com.bus.trans.service;

import com.bus.trans.dto.ForfaitDTO;
import com.bus.trans.model.Carte;
import com.bus.trans.model.Forfait;
import com.bus.trans.repository.CarteRepository;
import com.bus.trans.repository.ForfaitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ForfaitService {

    @Autowired
    private ForfaitRepository forfaitRepository;

    @Autowired
    private CarteRepository carteRepository;

    // Récupérer l'historique des forfaits d'une carte par ID
    public List<ForfaitDTO> getForfaitHistory(Long carteId) {
        List<Forfait> forfaits = forfaitRepository.findByCarteId(carteId);
        return forfaits.stream()
                .map(forfait -> new ForfaitDTO(
                        forfait.getId(),
                        forfait.getTypeForfait(),
                        forfait.getDateActivation(),
                        forfait.getDateExpiration(),
                        forfait.getCarte().getId(),
                        forfait.getCarte().getRfid()))
                .collect(Collectors.toList());
    }

    // Créer un forfait pour une carte et mettre à jour le statut de la carte
    public Forfait createForfait(ForfaitDTO forfaitDTO) {
        Optional<Carte> carteOpt = carteRepository.findById(forfaitDTO.getCarteId());
        if (carteOpt.isEmpty()) {
            throw new IllegalArgumentException("Carte introuvable avec l'ID fourni");
        }

        Carte carte = carteOpt.get();

        // Calculer les dates d'activation et d'expiration du forfait
        Date dateActivation = new Date();
        Date dateExpiration = calculateExpirationDate(forfaitDTO.getTypeForfait(), dateActivation);

        // Créer le forfait
        Forfait forfait = new Forfait(forfaitDTO.getTypeForfait(), dateActivation, dateExpiration, carte);
        forfaitRepository.save(forfait);

        // Mettre à jour la carte avec le statut du forfait et la date d'expiration
        carte.setForfaitActif(true);
        carte.setForfaitExpiration(dateExpiration);
        carteRepository.save(carte);

        return forfait;
    }

    // Méthode pour calculer la date d'expiration en fonction du type de forfait
    private Date calculateExpirationDate(String typeForfait, Date dateActivation) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateActivation);
        switch (typeForfait) {
            case "jour":
                cal.add(Calendar.DAY_OF_MONTH, 1);
                break;
            case "semaine":
                cal.add(Calendar.DAY_OF_MONTH, 7);
                break;
            case "mois":
                cal.add(Calendar.MONTH, 1);
                break;
        }
        return cal.getTime();
    }

    // Supprimer un forfait
    public void deleteForfait(Long id) {
        forfaitRepository.deleteById(id);
    }

    // Récupérer le statut du forfait via RFID de la carte
    public ForfaitDTO getForfaitStatusByRFID(String rfid) {
        Optional<Carte> carteOpt = carteRepository.findByRfid(rfid);
        if (carteOpt.isEmpty()) {
            return null; // Carte introuvable
        }

        Carte carte = carteOpt.get();

        // Vérifier le statut du forfait (actif ou inactif)
        if (carte.isForfaitActif()) {
            Date expirationDate = carte.getForfaitExpiration();
            if (expirationDate != null && expirationDate.after(new Date())) {
                return new ForfaitDTO(
                        carte.getId(),
                        "Forfait Actif",
                        carte.getForfaitExpiration(),
                        carte.getForfaitExpiration(),
                        carte.getId(),
                        carte.getRfid());
            } else {
                return new ForfaitDTO(
                        carte.getId(),
                        "Forfait Expiré",
                        carte.getForfaitExpiration(),
                        carte.getForfaitExpiration(),
                        carte.getId(),
                        carte.getRfid());
            }
        } else {
            return new ForfaitDTO(
                    carte.getId(),
                    "Aucun forfait actif",
                    null,
                    null,
                    carte.getId(),
                    carte.getRfid());
        }
    }
}
