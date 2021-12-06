package com.rensilver.parkingmanager.controllers;

import com.rensilver.parkingmanager.dto.ClientDTO;
import com.rensilver.parkingmanager.dto.StayDTO;
import com.rensilver.parkingmanager.services.StayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/stays")
public class StayController {

    @Autowired
    private StayService stayService;

    @GetMapping
    public ResponseEntity<List<StayDTO>> findAll() {
        List<StayDTO> stayList = stayService.findAll();
        return ResponseEntity.ok().body(stayList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<StayDTO> findById(@PathVariable Long id) {
        StayDTO stayDTO = stayService.findById(id);
        return ResponseEntity.ok().body(stayDTO);
    }

    @GetMapping(value = "/by-vehicle/{vehicleId}")
    public ResponseEntity<List<StayDTO>> getAllStaysForVehicle(@PathVariable Long vehicleId) {
        List<StayDTO> stayDTOList = stayService.getAllStaysForVehicle(vehicleId);
        return ResponseEntity.ok().body(stayDTOList);
    }

    @PostMapping
    public ResponseEntity<StayDTO> insert(@Valid @RequestBody StayDTO stayDTO) {
        stayDTO = stayService.insert(stayDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(stayDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(stayDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<StayDTO> update(@PathVariable Long id, @RequestBody StayDTO stayDTO) {
        stayDTO = stayService.update(id, stayDTO);
        return ResponseEntity.ok().body(stayDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<StayDTO> delete(@PathVariable Long id) {
        stayService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
