package com.printer.models;

import java.sql.Date;

public class Rental {
    private int id;
    private int clientId;
    private int machineId;
    private Date startDate;
    private Date endDate;
    private String status;

    public Rental(int id, int clientId, int machineId, Date startDate, Date endDate, String status) {
        this.id = id;
        this.clientId = clientId;
        this.machineId = machineId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public Rental(int clientId, int machineId, Date startDate, Date endDate, String status) {
        this.clientId = clientId;
        this.machineId = machineId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getMachineId() {
        return machineId;
    }

    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Rental{id=" + id + ", clientId=" + clientId + ", machineId=" + machineId + 
               ", startDate=" + startDate + ", endDate=" + endDate + ", status='" + status + "'}";
    }
}
