package com.bus.trans.service;

import com.bus.trans.dto.ReservationDTO;
import com.bus.trans.model.ReservationInterurbain;
import com.bus.trans.model.TrajetInterurbain;
import java.util.Date;
import java.util.List;

public interface ReservationInterurbainService {
    List<TrajetInterurbain> getVoyagesByDateAndDestination(Date date, String destination);
    List<ReservationInterurbain> getReservationsByTrajet(Long trajetId);
    ReservationInterurbain reserverPlace(Long trajetId, String numeroPlace, ReservationDTO reservationDTO);
    String genererTitreDeTransport(Long reservationId);
}
