package com.bus.trans.repository;

import com.bus.trans.model.TrajetInterurbain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TrajetInterurbainRepository extends JpaRepository<TrajetInterurbain, Long> {

    // Rechercher tous les trajets pour une date et une destination spécifiques
    List<TrajetInterurbain> findByHeureDepartAndLieuArrivee(Date heureDepart, String lieuArrivee);
}
