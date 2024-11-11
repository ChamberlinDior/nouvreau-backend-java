package com.bus.trans.repository;

import com.bus.trans.model.ReservationInterurbain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationInterurbainRepository extends JpaRepository<ReservationInterurbain, Long> {

    // Récupérer toutes les réservations pour un trajet spécifique
    List<ReservationInterurbain> findByTrajetId(Long trajetId);
}
