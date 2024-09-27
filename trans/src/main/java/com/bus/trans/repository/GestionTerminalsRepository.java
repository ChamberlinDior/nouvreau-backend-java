package com.bus.trans.repository;

import com.bus.trans.model.GestionTerminals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GestionTerminalsRepository extends JpaRepository<GestionTerminals, Long> {
}
