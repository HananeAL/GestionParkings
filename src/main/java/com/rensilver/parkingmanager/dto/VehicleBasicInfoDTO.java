package com.rensilver.parkingmanager.dto;

import com.rensilver.parkingmanager.entities.Vehicle;

import java.io.Serializable;

public class VehicleBasicInfoDTO implements Serializable {

    private Long id;
    private String model;
    private String color;
    private String licensePlate;

    public VehicleBasicInfoDTO() {
    }

    public VehicleBasicInfoDTO(Vehicle entity) {
        id = entity.getId();
        model = entity.getModel();
        color = entity.getColor();
        licensePlate = entity.getLicensePlate();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }
}
