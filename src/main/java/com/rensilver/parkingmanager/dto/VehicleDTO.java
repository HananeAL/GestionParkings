package com.rensilver.parkingmanager.dto;

import com.rensilver.parkingmanager.entities.Client;
import com.rensilver.parkingmanager.entities.Stay;
import com.rensilver.parkingmanager.entities.Vehicle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class VehicleDTO implements Serializable {

    private Long id;
    private String model;
    private String color;
    private String licensePlate;
    private Long clientId;

    private List<ClientDTO> clients = new ArrayList<>();

    private List<StayDTO> stays = new ArrayList<>();

    public VehicleDTO() {
    }

    public VehicleDTO(Long id, String model, String color, String licensePlate, Long clientId) {
        this.id = id;
        this.model = model;
        this.color = color;
        this.licensePlate = licensePlate;
        this.clientId = clientId;
    }

    public VehicleDTO(Vehicle entity) {
        id = entity.getId();
        model = entity.getModel();
        color = entity.getColor();
        licensePlate = entity.getLicensePlate();
    }

    public VehicleDTO(Vehicle entity, Set<Client> clients, List<Stay> stays) {
        this(entity);
        clients.forEach(client -> this.clients.add(new ClientDTO(client)));
        stays.forEach(st -> this.stays.add(new StayDTO(st)));
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

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public List<ClientDTO> getClients() {
        return clients;
    }

    public void setClients(List<ClientDTO> clients) {
        this.clients = clients;
    }

    public List<StayDTO> getStays() {
        return stays;
    }
}
