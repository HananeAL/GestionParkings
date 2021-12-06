package com.rensilver.parkingmanager.repositories;

import com.rensilver.parkingmanager.entities.Client;
import com.rensilver.parkingmanager.entities.Stay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StayRepository extends JpaRepository<Stay, Long> {

    List<Stay> findByVehicle_id(Long id);
}
