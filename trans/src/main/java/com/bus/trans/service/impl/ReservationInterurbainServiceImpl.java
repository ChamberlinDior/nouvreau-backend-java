package com.bus.trans.service.impl;

import com.bus.trans.dto.ReservationDTO;
import com.bus.trans.model.ReservationInterurbain;
import com.bus.trans.model.TrajetInterurbain;
import com.bus.trans.repository.ReservationInterurbainRepository;
import com.bus.trans.repository.TrajetInterurbainRepository;
import com.bus.trans.service.ReservationInterurbainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReservationInterurbainServiceImpl implements ReservationInterurbainService {

    @Autowired
    private TrajetInterurbainRepository trajetRepository;

    @Autowired
    private ReservationInterurbainRepository reservationRepository;

    @Override
    public List<TrajetInterurbain> getVoyagesByDateAndDestination(Date date, String destination) {
        // Utiliser la nouvelle méthode du repository pour récupérer les trajets
        return trajetRepository.findByHeureDepartAndLieuArrivee(date, destination);
    }

    @Override
    public List<ReservationInterurbain> getReservationsByTrajet(Long trajetId) {
        return reservationRepository.findByTrajetId(trajetId);
    }

    @Override
    public ReservationInterurbain reserverPlace(Long trajetId, String numeroPlace, ReservationDTO reservationDTO) {
        TrajetInterurbain trajet = trajetRepository.findById(trajetId).orElse(null);
        if (trajet != null) {
            ReservationInterurbain reservation = new ReservationInterurbain();
            reservation.setTrajet(trajet);
            reservation.setNumeroPlace(numeroPlace);
            reservation.setEstReservee(true);
            reservation.setNomPassager(reservationDTO.getNomPassager());
            reservation.setPrenomPassager(reservationDTO.getPrenomPassager());
            reservation.setDateNaissancePassager(reservationDTO.getDateNaissancePassager());
            reservation.setDateReservation(new Date());
            return reservationRepository.save(reservation);
        }
        return null;
    }

    @Override
    public String genererTitreDeTransport(Long reservationId) {
        ReservationInterurbain reservation = reservationRepository.findById(reservationId).orElse(null);
        if (reservation != null) {
            return "Titre de Transport :\nPassager : " + reservation.getNomPassager() + " " + reservation.getPrenomPassager() +
                    "\nDépart : " + reservation.getTrajet().getLieuDepart() +
                    "\nArrivée : " + reservation.getTrajet().getLieuArrivee() +
                    "\nDate : " + reservation.getDateReservation();
        }
        return null;
    }
}
