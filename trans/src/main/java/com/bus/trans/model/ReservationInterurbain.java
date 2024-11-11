package com.bus.trans.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class ReservationInterurbain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String numeroPlace;

    @Column(nullable = false)
    private boolean estReservee; // Pour le statut de la place (réservée ou non)

    @Column(nullable = true)
    private String nomPassager;

    @Column(nullable = true)
    private String prenomPassager;

    @Temporal(TemporalType.DATE)
    private Date dateNaissancePassager;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateReservation;

    @ManyToOne
    @JoinColumn(name = "trajet_id", nullable = false)
    private TrajetInterurbain trajet;

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNumeroPlace() { return numeroPlace; }
    public void setNumeroPlace(String numeroPlace) { this.numeroPlace = numeroPlace; }

    public boolean isEstReservee() { return estReservee; }
    public void setEstReservee(boolean estReservee) { this.estReservee = estReservee; }

    public String getNomPassager() { return nomPassager; }
    public void setNomPassager(String nomPassager) { this.nomPassager = nomPassager; }

    public String getPrenomPassager() { return prenomPassager; }
    public void setPrenomPassager(String prenomPassager) { this.prenomPassager = prenomPassager; }

    public Date getDateNaissancePassager() { return dateNaissancePassager; }
    public void setDateNaissancePassager(Date dateNaissancePassager) { this.dateNaissancePassager = dateNaissancePassager; }

    public Date getDateReservation() { return dateReservation; }
    public void setDateReservation(Date dateReservation) { this.dateReservation = dateReservation; }

    public TrajetInterurbain getTrajet() { return trajet; }
    public void setTrajet(TrajetInterurbain trajet) { this.trajet = trajet; }
}
