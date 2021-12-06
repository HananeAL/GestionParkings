package com.rensilver.parkingmanager.services;

import com.rensilver.parkingmanager.dto.ClientDTO;
import com.rensilver.parkingmanager.entities.Client;
import com.rensilver.parkingmanager.entities.Vehicle;
import com.rensilver.parkingmanager.repositories.ClientRepository;
import com.rensilver.parkingmanager.repositories.VehicleRepository;
import com.rensilver.parkingmanager.services.exceptions.DatabaseException;
import com.rensilver.parkingmanager.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Transactional(readOnly = true)
    public List<ClientDTO> findAll() {
        List<Client> clientList = clientRepository.findAll();
        return clientList
                .stream()
                .map(ClientDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id) {
        Optional<Client> obj = clientRepository.findById(id);
        Client entity = obj.orElseThrow(() -> new ResourceNotFoundException("Client not found"));
        return new ClientDTO(entity);
    }

    @Transactional(readOnly = true)
    public List<ClientDTO> getAllClientsForVehicle(Long vehicleId) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId).orElseThrow(() -> new ResourceNotFoundException("Vehicle not found"));
        List<Client> clientList = clientRepository.findByVehicles_licensePlate(vehicle.getLicensePlate());
        return clientList
                .stream()
                .map(ClientDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public ClientDTO insert(ClientDTO clientDTO) {
        Client entity = new Client();
        copyDtoToEntity(clientDTO, entity);
        entity = clientRepository.save(entity);
        return new ClientDTO(entity);
    }

    @Transactional
    public ClientDTO update(Long id, ClientDTO clientDTO) {
        try {
            Client entity = clientRepository.getOne(id);
            copyDtoToEntity(clientDTO, entity);
            entity = clientRepository.save(entity);
            return new ClientDTO(entity);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
    }

    public void delete(Long id) {
        try {
            clientRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }
    }

    private void copyDtoToEntity(ClientDTO dto, Client entity) {

        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setCpf(dto.getCpf());
        entity.setAddress(dto.getAddress());
        entity.setCellPhone(dto.getCellPhone());
    }
}
