package com.rensilver.parkingmanager.services;

import com.rensilver.parkingmanager.dto.StayDTO;
import com.rensilver.parkingmanager.entities.Stay;
import com.rensilver.parkingmanager.entities.Vehicle;
import com.rensilver.parkingmanager.repositories.StayRepository;
import com.rensilver.parkingmanager.repositories.VehicleRepository;
import com.rensilver.parkingmanager.services.exceptions.DatabaseException;
import com.rensilver.parkingmanager.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StayService {

    @Autowired
    private StayRepository stayRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Transactional(readOnly = true)
    public List<StayDTO> findAll() {
        List<Stay> stayList = stayRepository.findAll();
        return stayList
                .stream()
                .map(x -> new StayDTO(x, x.getVehicle()))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public StayDTO findById(Long id) {
        Optional<Stay> obj = stayRepository.findById(id);
        Stay entity = obj.orElseThrow(() -> new ResourceNotFoundException("Stay not found"));
        return new StayDTO(entity, entity.getVehicle());
    }

    @Transactional(readOnly = true)
    public List<StayDTO> getAllStaysForVehicle(Long vehicleId) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId).orElseThrow(() -> new ResourceNotFoundException("Vehicle not found"));
        List<Stay> stayList = stayRepository.findByVehicle_id(vehicle.getId());
        return stayList
                .stream()
                .map(StayDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public StayDTO insert(StayDTO stayDTO) {
        Stay entity = new Stay();
        copyDtoToEntity(stayDTO, entity);
        entity = stayRepository.save(entity);
        return new StayDTO(entity, entity.getVehicle());
    }

    @Transactional
    public StayDTO update(Long id, StayDTO stayDTO) {
        try {
            Stay entity = stayRepository.getOne(id);
            copyDtoToEntity(stayDTO, entity);
            entity = stayRepository.save(entity);
            return new StayDTO(entity, entity.getVehicle());
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
    }

    public void delete(Long id) {
        try {
            stayRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }
    }

    private void copyDtoToEntity(StayDTO stayDTO, Stay entity) {

        entity.setEntryDate(stayDTO.getEntryDate());
        entity.setDepartureDate(stayDTO.getDepartureDate());
        entity.setEntryTime(stayDTO.getEntryTime());
        entity.setExitTime(stayDTO.getExitTime());
        entity.setHourlyValor(stayDTO.getHourlyValor());
        if (stayDTO.getVehicleId() != null) {
            Vehicle vehicle = vehicleRepository.getOne(stayDTO.getVehicleId());
            entity.setVehicle(vehicle);
        }
        else {
            Vehicle vehicle = vehicleRepository.getOne(stayDTO.getVehicle().getId());
            entity.setVehicle(vehicle);
        }
    }
}
