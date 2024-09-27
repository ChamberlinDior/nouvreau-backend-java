package com.bus.trans.dto;

import java.time.LocalDateTime;

public class TerminalDTO {

    private String androidId;
    private int batteryLevel;
    private String terminalType;
    private String userName;
    private String userUniqueId;
    private LocalDateTime connectionTime;
    private String clientName;
    private String rfid;
    private String forfaitType;
    private String forfaitStatus;
    private LocalDateTime verificationTime;
    private String trajetChauffeurName;
    private String trajetChauffeurUniqueId;
    private String trajetDestination;
    private LocalDateTime trajetStartTime;

    // Getters and Setters

    public String getAndroidId() {
        return androidId;
    }

    public void setAndroidId(String androidId) {
        this.androidId = androidId;
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(int batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public String getTerminalType() {
        return terminalType;
    }

    public void setTerminalType(String terminalType) {
        this.terminalType = terminalType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserUniqueId() {
        return userUniqueId;
    }

    public void setUserUniqueId(String userUniqueId) {
        this.userUniqueId = userUniqueId;
    }

    public LocalDateTime getConnectionTime() {
        return connectionTime;
    }

    public void setConnectionTime(LocalDateTime connectionTime) {
        this.connectionTime = connectionTime;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getRfid() {
        return rfid;
    }

    public void setRfid(String rfid) {
        this.rfid = rfid;
    }

    public String getForfaitType() {
        return forfaitType;
    }

    public void setForfaitType(String forfaitType) {
        this.forfaitType = forfaitType;
    }

    public String getForfaitStatus() {
        return forfaitStatus;
    }

    public void setForfaitStatus(String forfaitStatus) {
        this.forfaitStatus = forfaitStatus;
    }

    public LocalDateTime getVerificationTime() {
        return verificationTime;
    }

    public void setVerificationTime(LocalDateTime verificationTime) {
        this.verificationTime = verificationTime;
    }

    public String getTrajetChauffeurName() {
        return trajetChauffeurName;
    }

    public void setTrajetChauffeurName(String trajetChauffeurName) {
        this.trajetChauffeurName = trajetChauffeurName;
    }

    public String getTrajetChauffeurUniqueId() {
        return trajetChauffeurUniqueId;
    }

    public void setTrajetChauffeurUniqueId(String trajetChauffeurUniqueId) {
        this.trajetChauffeurUniqueId = trajetChauffeurUniqueId;
    }

    public String getTrajetDestination() {
        return trajetDestination;
    }

    public void setTrajetDestination(String trajetDestination) {
        this.trajetDestination = trajetDestination;
    }

    public LocalDateTime getTrajetStartTime() {
        return trajetStartTime;
    }

    public void setTrajetStartTime(LocalDateTime trajetStartTime) {
        this.trajetStartTime = trajetStartTime;
    }
}
