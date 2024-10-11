package com.bus.trans.dto;

import java.util.Date;

public class ForfaitDTO {

    private Long id;  // Identifiant unique du forfait
    private String typeForfait;  // Type de forfait (jour, semaine, mois)
    private Date dateActivation;  // Date d'activation du forfait
    private Date dateExpiration;  // Date d'expiration du forfait
    private Long carteId;  // Identifiant de la carte associée
    private String rfid;  // RFID de la carte associée

    // Constructeur vide
    public ForfaitDTO() {}

    // Constructeur complet avec tous les champs
    public ForfaitDTO(Long id, String typeForfait, Date dateActivation, Date dateExpiration, Long carteId, String rfid) {
        this.id = id;
        this.typeForfait = typeForfait;
        this.dateActivation = dateActivation;
        this.dateExpiration = dateExpiration;
        this.carteId = carteId;
        this.rfid = rfid;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeForfait() {
        return typeForfait;
    }

    public void setTypeForfait(String typeForfait) {
        this.typeForfait = typeForfait;
    }

    public Date getDateActivation() {
        return dateActivation;
    }

    public void setDateActivation(Date dateActivation) {
        this.dateActivation = dateActivation;
    }

    public Date getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(Date dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public Long getCarteId() {
        return carteId;
    }

    public void setCarteId(Long carteId) {
        this.carteId = carteId;
    }

    public String getRfid() {
        return rfid;
    }

    public void setRfid(String rfid) {
        this.rfid = rfid;
    }

    @Override
    public String toString() {
        return "ForfaitDTO{" +
                "id=" + id +
                ", typeForfait='" + typeForfait + '\'' +
                ", dateActivation=" + dateActivation +
                ", dateExpiration=" + dateExpiration +
                ", carteId=" + carteId +
                ", rfid='" + rfid + '\'' +
                '}';
    }
}
