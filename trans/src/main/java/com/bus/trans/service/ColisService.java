
package com.bus.trans.service;

import com.bus.trans.dto.ColisDTO;

import java.util.List;

public interface ColisService {
    ColisDTO createColis(ColisDTO colisDTO);
    ColisDTO updateColis(Long id, ColisDTO colisDTO);
    void deleteColis(Long id);
    ColisDTO getColisById(Long id);
    List<ColisDTO> getAllColis();
}
