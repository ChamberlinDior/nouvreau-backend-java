package com.bus.trans.service.impl;

import com.bus.trans.dto.ColisDTO;
import com.bus.trans.model.Colis;
import com.bus.trans.repository.ColisRepository;
import com.bus.trans.service.ColisService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ColisServiceImpl implements ColisService {
    private final ColisRepository colisRepository;

    @Autowired
    public ColisServiceImpl(ColisRepository colisRepository) {
        this.colisRepository = colisRepository;
    }

    // Convert Colis to ColisDTO
    private ColisDTO convertToDto(Colis colis) {
        ColisDTO colisDTO = new ColisDTO();
        colisDTO.setId(colis.getId());
        colisDTO.setLongueur(colis.getLongueur());
        colisDTO.setLargeur(colis.getLargeur());
        colisDTO.setHauteur(colis.getHauteur());
        colisDTO.setPoids(colis.getPoids());
        colisDTO.setCodeSuivi(colis.getCodeSuivi());
        colisDTO.setQrCodeData(colis.getQrCodeData());
        colisDTO.setStatut(colis.getStatut());
        colisDTO.setDateEnregistrement(colis.getDateEnregistrement());
        colisDTO.setVoyageId(colis.getVoyage().getId());
        return colisDTO;
    }

    // Convert ColisDTO to Colis
    private Colis convertToEntity(ColisDTO colisDTO) {
        Colis colis = new Colis();
        colis.setLongueur(colisDTO.getLongueur());
        colis.setLargeur(colisDTO.getLargeur());
        colis.setHauteur(colisDTO.getHauteur());
        colis.setPoids(colisDTO.getPoids());
        colis.setCodeSuivi(colisDTO.getCodeSuivi());
        colis.setQrCodeData(colisDTO.getQrCodeData());
        colis.setStatut(colisDTO.getStatut());
        colis.setDateEnregistrement(colisDTO.getDateEnregistrement());
        return colis;
    }

    @Override
    public ColisDTO createColis(ColisDTO colisDTO) {
        Colis colis = convertToEntity(colisDTO);
        colis = colisRepository.save(colis);
        return convertToDto(colis);
    }

    @Override
    public ColisDTO updateColis(Long id, ColisDTO colisDTO) {
        Optional<Colis> existingColisOpt = colisRepository.findById(id);
        if (existingColisOpt.isPresent()) {
            Colis existingColis = existingColisOpt.get();
            existingColis.setLongueur(colisDTO.getLongueur());
            existingColis.setLargeur(colisDTO.getLargeur());
            existingColis.setHauteur(colisDTO.getHauteur());
            existingColis.setPoids(colisDTO.getPoids());
            existingColis.setCodeSuivi(colisDTO.getCodeSuivi());
            existingColis.setQrCodeData(colisDTO.getQrCodeData());
            existingColis.setStatut(colisDTO.getStatut());
            existingColis.setDateEnregistrement(colisDTO.getDateEnregistrement());
            colisRepository.save(existingColis);
            return convertToDto(existingColis);
        } else {
            throw new RuntimeException("Colis not found with id " + id);
        }
    }

    @Override
    public void deleteColis(Long id) {
        if (colisRepository.existsById(id)) {
            colisRepository.deleteById(id);
        } else {
            throw new RuntimeException("Colis not found with id " + id);
        }
    }

    @Override
    public ColisDTO getColisById(Long id) {
        Optional<Colis> colis = colisRepository.findById(id);
        return colis.map(this::convertToDto)
                .orElseThrow(() -> new RuntimeException("Colis not found with id " + id));
    }

    @Override
    public List<ColisDTO> getAllColis() {
        List<Colis> colisList = colisRepository.findAll();
        return colisList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}
