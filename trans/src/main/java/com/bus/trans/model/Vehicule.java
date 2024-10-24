package com.bus.trans.model;

import jakarta.persistence.*;

@Entity
public class Vehicule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String immatriculation;  // Champ obligatoire

    @Column(nullable = true)
    private String trajet;  // Champ optionnel

    @Column(nullable = true)
    private String chauffeur;  // Champ optionnel

    @Column(nullable = true, unique = true)
    private String macAddress;  // Champ optionnel pour l'adresse MAC

    @Column(nullable = true)
    private String marque;  // Nouvelle colonne pour la marque

    @Column(nullable = true)
    private String modele;  // Nouvelle colonne pour le modèle

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public String getTrajet() {
        return trajet;
    }

    public void setTrajet(String trajet) {
        this.trajet = trajet;
    }

    public String getChauffeur() {
        return chauffeur;
    }

    public void setChauffeur(String chauffeur) {
        this.chauffeur = chauffeur;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }
}
