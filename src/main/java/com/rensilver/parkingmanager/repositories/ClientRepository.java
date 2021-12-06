package com.rensilver.parkingmanager.repositories;

import com.rensilver.parkingmanager.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    List<Client> findByVehicles_licensePlate(String licensePlate);
}
