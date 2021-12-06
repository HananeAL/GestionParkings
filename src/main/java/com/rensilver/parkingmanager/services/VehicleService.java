package com.rensilver.parkingmanager.services;

import com.rensilver.parkingmanager.dto.ClientDTO;
import com.rensilver.parkingmanager.dto.VehicleDTO;
import com.rensilver.parkingmanager.entities.Client;
import com.rensilver.parkingmanager.entities.Vehicle;
import com.rensilver.parkingmanager.repositories.ClientRepository;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Transactional(readOnly = true)
    public List<VehicleDTO> findAll() {
        List<Vehicle> vehicleList = vehicleRepository.findAll();
        return vehicleList
                .stream()
                .map(x -> new VehicleDTO(x, x.getClients(), x.getStays()))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public VehicleDTO findById(Long id) {
        Optional<Vehicle> obj = vehicleRepository.findById(id);
        Vehicle entity = obj.orElseThrow(() -> new ResourceNotFoundException("Vehicle not found"));
        return new VehicleDTO(entity, entity.getClients(), entity.getStays());
    }

    @Transactional
    public VehicleDTO insert(VehicleDTO vehicleDTO) {
        Vehicle entity = new Vehicle();
        copyDtoToEntity(vehicleDTO, entity);
        entity = vehicleRepository.save(entity);
        return new VehicleDTO(entity, entity.getClients(), entity.getStays());
    }

    @Transactional
    public VehicleDTO update(Long id, VehicleDTO vehicleDTO) {
        try {
            Vehicle entity = vehicleRepository.getOne(id);
            copyDtoToEntity(vehicleDTO, entity);
            entity = vehicleRepository.save(entity);
            return new VehicleDTO(entity, entity.getClients(), entity.getStays());
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
    }

    public void delete(Long id) {
        try {
            vehicleRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }
    }

    private void copyDtoToEntity(VehicleDTO dto, Vehicle entity) {

        entity.setModel(dto.getModel());
        entity.setColor(dto.getColor());
        entity.setLicensePlate(dto.getLicensePlate());

        if (dto.getClientId() != null) {
            Client clientId = clientRepository.getOne(dto.getClientId());
            entity.getClients().add(clientId);
        }
        else {
            entity.getClients().clear();
            for (ClientDTO clientDTO : dto.getClients()) {
                Client client = clientRepository.getOne(clientDTO.getId());
                entity.getClients().add(client);
            }
        }
    }
}
