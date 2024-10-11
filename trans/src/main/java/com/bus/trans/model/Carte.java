package com.bus.trans.model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
public class Carte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String rfid;  // Chaque carte aura un RFID unique

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date dateCreation;  // Date de création de la carte

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateExpiration;  // Date d'expiration de la carte

    @Column(nullable = false)
    private String nomAgent;  // Nom de l'agent ayant délivré la carte

    @Column(nullable = false)
    private boolean active;  // Statut de la carte (active ou non)

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;  // Association avec le client

    public Carte() {
        this.rfid = "RFID-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.nomAgent = "Agent-" + UUID.randomUUID().toString().substring(0, 4).toUpperCase();
        this.dateCreation = new Date();
        this.active = true;  // Par défaut, la carte est active
    }

    // Getters and Setters
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

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(Date dateExpiration) {
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
