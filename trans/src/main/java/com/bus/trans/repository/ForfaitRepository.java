package com.bus.trans.repository;
import com.bus.trans.model.Forfait;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ForfaitRepository extends JpaRepository<Forfait, Long> {

    List<Forfait> findByClientId(Long clientId); // Récupérer les forfaits par ID client
}