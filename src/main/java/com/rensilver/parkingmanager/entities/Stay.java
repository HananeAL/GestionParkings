package com.rensilver.parkingmanager.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Table(name = "tb_stay")
public class Stay implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="entryDate", nullable = false)
    private LocalDate entryDate;

    @Column(name="departureDate", nullable = false)
    private LocalDate departureDate;

    @Column(name="entryTime", nullable = false)
    private LocalTime entryTime;

    @Column(name="exitTime", nullable = false)
    private LocalTime exitTime;

    @Column(name="hourlyValor", nullable = false)
    private Double hourlyValor;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    public Stay() {
    }

    public Stay(Long id, LocalDate entryDate, LocalDate departureDate, LocalTime entryTime, LocalTime exitTime, Vehicle vehicle) {
        this.id = id;
        this.entryDate = entryDate;
        this.departureDate = departureDate;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
        this.vehicle = vehicle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public LocalTime getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalTime entryTime) {
        this.entryTime = entryTime;
    }

    public LocalTime getExitTime() {
        return exitTime;
    }

    public void setExitTime(LocalTime exitTime) {
        this.exitTime = exitTime;
    }

    public Double getHourlyValor() {
        return hourlyValor;
    }

    public void setHourlyValor(Double hourlyValor) {
        this.hourlyValor = hourlyValor;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stay stay = (Stay) o;
        return Objects.equals(id, stay.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
