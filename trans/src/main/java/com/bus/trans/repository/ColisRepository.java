package com.bus.trans.repository;

import com.bus.trans.model.Colis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColisRepository extends JpaRepository<Colis, Long> {
}
