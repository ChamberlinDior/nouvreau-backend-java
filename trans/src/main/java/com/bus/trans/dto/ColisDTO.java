// ColisDTO.java
package com.bus.trans.dto;

import java.util.Date;

public class ColisDTO {
    private Long id;
    private double longueur;
    private double largeur;
    private double hauteur;
    private double poids;
    private String codeSuivi;
    private String qrCodeData;
    private String statut;
    private Date dateEnregistrement;
    private Long voyageId;

    // Getters and Setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public double getLongueur() { return longueur; }
    public void setLongueur(double longueur) { this.longueur = longueur; }

    public double getLargeur() { return largeur; }
    public void setLargeur(double largeur) { this.largeur = largeur; }

    public double getHauteur() { return hauteur; }
    public void setHauteur(double hauteur) { this.hauteur = hauteur; }

    public double getPoids() { return poids; }
    public void setPoids(double poids) { this.poids = poids; }

    public String getCodeSuivi() { return codeSuivi; }
    public void setCodeSuivi(String codeSuivi) { this.codeSuivi = codeSuivi; }

    public String getQrCodeData() { return qrCodeData; }
    public void setQrCodeData(String qrCodeData) { this.qrCodeData = qrCodeData; }

    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }

    public Date getDateEnregistrement() { return dateEnregistrement; }
    public void setDateEnregistrement(Date dateEnregistrement) { this.dateEnregistrement = dateEnregistrement; }

    public Long getVoyageId() { return voyageId; }
    public void setVoyageId(Long voyageId) { this.voyageId = voyageId; }
}
