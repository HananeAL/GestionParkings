package com.rensilver.parkingmanager.controllers;

import com.rensilver.parkingmanager.dto.VehicleDTO;
import com.rensilver.parkingmanager.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping
    public ResponseEntity<List<VehicleDTO>> findAll() {
        List<VehicleDTO> vehicleList = vehicleService.findAll();
        return ResponseEntity.ok().body(vehicleList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<VehicleDTO> findById(@PathVariable Long id) {
        VehicleDTO vehicleDTO = vehicleService.findById(id);
        return ResponseEntity.ok().body(vehicleDTO);
    }

    @PostMapping
    public ResponseEntity<VehicleDTO> insert(@Valid @RequestBody VehicleDTO vehicleDTO) {
        vehicleDTO = vehicleService.insert(vehicleDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(vehicleDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(vehicleDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<VehicleDTO> update(@PathVariable Long id, @RequestBody VehicleDTO vehicleDTO) {
        vehicleDTO = vehicleService.update(id, vehicleDTO);
        return ResponseEntity.ok().body(vehicleDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<VehicleDTO> delete(@PathVariable Long id) {
        vehicleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
