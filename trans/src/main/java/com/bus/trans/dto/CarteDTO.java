package com.bus.trans.dto;

import java.util.Date;

public class CarteDTO {

    private Long id;
    private String rfid;
    private String dateCreation;
    private String dateExpiration;
    private String nomAgent;
    private boolean active;

    // Ajout des nouvelles propriétés
    private boolean forfaitActif;
    private String forfaitExpiration;  // Représentation de la date d'expiration du forfait

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRfid() {
        return rfid;
    }

    public void setRfid(String rfid) {
        this.rfid = rfid;
    }

    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(String dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public String getNomAgent() {
        return nomAgent;
    }

    public void setNomAgent(String nomAgent) {
        this.nomAgent = nomAgent;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    // Getters et Setters pour les nouvelles propriétés
    public boolean isForfaitActif() {
        return forfaitActif;
    }

    public void setForfaitActif(boolean forfaitActif) {
        this.forfaitActif = forfaitActif;
    }

    public String getForfaitExpiration() {
        return forfaitExpiration;
    }

    public void setForfaitExpiration(String forfaitExpiration) {
        this.forfaitExpiration = forfaitExpiration;
    }

    // toString pour aider au débogage
    @Override
    public String toString() {
        return "CarteDTO{" +
                "id=" + id +
                ", rfid='" + rfid + '\'' +
                ", dateCreation='" + dateCreation + '\'' +
                ", dateExpiration='" + dateExpiration + '\'' +
                ", nomAgent='" + nomAgent + '\'' +
                ", active=" + active +
                ", forfaitActif=" + forfaitActif +
                ", forfaitExpiration='" + forfaitExpiration + '\'' +
                '}';
    }
}
