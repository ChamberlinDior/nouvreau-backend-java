package com.bus.trans.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class TrajetInterurbain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String lieuDepart;

    @Column(nullable = false)
    private String lieuArrivee;

    @Column(nullable = false)
    private double montant;

    @Temporal(TemporalType.TIMESTAMP)
    private Date heureDepart;

    @Temporal(TemporalType.TIMESTAMP)
    private Date heureArrivee;

    @ManyToOne
    @JoinColumn(name = "vehicule_id", nullable = false)
    private VehiculeInterurbain vehicule;

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLieuDepart() {
        return lieuDepart;
    }

    public void setLieuDepart(String lieuDepart) {
        this.lieuDepart = lieuDepart;
    }

    public String getLieuArrivee() {
        return lieuArrivee;
    }

    public void setLieuArrivee(String lieuArrivee) {
        this.lieuArrivee = lieuArrivee;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public Date getHeureDepart() {
        return heureDepart;
    }

    public void setHeureDepart(Date heureDepart) {
        this.heureDepart = heureDepart;
    }

    public Date getHeureArrivee() {
        return heureArrivee;
    }

    public void setHeureArrivee(Date heureArrivee) {
        this.heureArrivee = heureArrivee;
    }

    public VehiculeInterurbain getVehicule() {
        return vehicule;
    }

    public void setVehicule(VehiculeInterurbain vehicule) {
        this.vehicule = vehicule;
    }
}
