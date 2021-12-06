package com.rensilver.parkingmanager.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.rensilver.parkingmanager.entities.Client;
import com.rensilver.parkingmanager.entities.Vehicle;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VehicleRepositoryTest {

	@MockBean
	private VehicleRepository vehicleRepository;

	@Autowired
	private ClientRepository clientRepository;

	Client client;
	Vehicle vehicle;

	@BeforeEach
	public void setUp() throws Exception {
		vehicle = new Vehicle(4L, "Toyota Corolla", "Preto", "FZY3R17");
	}

	@AfterEach
	void tearDown() throws Exception {
		vehicle = null;
	}

	@Test
	@Order(1)
	void testFindAll() {
		List<Vehicle> vehicles = vehicleRepository.findAll();
		assertNotNull(vehicles);
	}

	@Test
	@Order(2)
	void testFindById() {
		when(vehicleRepository.findById(1L)).thenReturn(Optional.of(vehicle));
		Vehicle vehicle1 = vehicleRepository.findById(1L).get();
		assertEquals(vehicle1.getId(), vehicle.getId());
	}

	@Test
	@Order(3)
	void testInsert() {
		Vehicle vehicle1 = new Vehicle(2L, "Toyota Corolla", "Preto", "FZY3R17");
		vehicleRepository.save(vehicle1);
		assertThat(vehicle.getId()).isGreaterThan(0);
	}

	@Test
	@Order(4)
	void testUpdate() {
		vehicle.setModel("Renaut Duster");
		vehicleRepository.save(vehicle);
		assertEquals(vehicle.getModel(), "Renaut Duster");
	}

	@Test
	@Order(5)
	void testDelete() {
		when(vehicleRepository.findById(vehicle.getId())).thenReturn(Optional.of(vehicle));
		vehicleRepository.deleteById(vehicle.getId());
		verify(vehicleRepository).deleteById(vehicle.getId());
	}

}
