package com.bus.trans.util;
import com.bus.trans.dto.CarteDTO;
import com.bus.trans.dto.ClientDTO;
import com.bus.trans.model.Carte;
import com.bus.trans.model.Client;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

public class DTOConverter {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public static ClientDTO convertToClientDTO(Client client) {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(client.getId());
        clientDTO.setNumClient(client.getNumClient());
        clientDTO.setNom(client.getNom());
        clientDTO.setPrenom(client.getPrenom());
        clientDTO.setQuartier(client.getQuartier());
        clientDTO.setVille(client.getVille());
        clientDTO.setDateCreation(sdf.format(client.getDateCreation()));
        clientDTO.setNomAgent(client.getNomAgent());
        clientDTO.setCartes(convertToCarteDTOs(client.getCartes()));
        return clientDTO;
    }

    public static List<CarteDTO> convertToCarteDTOs(List<Carte> cartes) {
        return cartes.stream().map(DTOConverter::convertToCarteDTO).collect(Collectors.toList());
    }

    public static CarteDTO convertToCarteDTO(Carte carte) {
        CarteDTO carteDTO = new CarteDTO();
        carteDTO.setId(carte.getId());
        carteDTO.setRfid(carte.getRfid());
        carteDTO.setDateCreation(sdf.format(carte.getDateCreation()));
        carteDTO.setDateExpiration(carte.getDateExpiration() != null ? sdf.format(carte.getDateExpiration()) : null);
        carteDTO.setNomAgent(carte.getNomAgent());
        carteDTO.setActive(carte.isActive());
        return carteDTO;
    }
}
