package com.rensilver.parkingmanager.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
class ClientRepositoryTest {

	@MockBean
	private ClientRepository clientRepository;

	@Autowired
	private VehicleRepository vehicleRepository;

	Client client;
	Set<Vehicle> vehicles;

	@BeforeEach
	void setUp() throws Exception {
		client = new Client(1L, "Maria", "Silva", "12485775505", "Rua Antonio Prado, n° 97", "(11) 97845-2301");
		vehicles = new HashSet<>();
		vehicles.add(new Vehicle(4L, "Toyota Corolla", "Preto", "FZY3R17"));
		vehicles.add(new Vehicle(5L, "Renaut Duster", "Prata", "TJP0Z25"));
		client.setVehicles(vehicles);
	}

	@AfterEach
	void tearDown() throws Exception {
		client = null;
	}

	@Test
	@Order(1)
	void testFindAll() {
		List<Client> clients = clientRepository.findAll();
		assertNotNull(clients);
	}

	@Test
	@Order(2)
	void testFindById() {
		when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
		Client client1 = clientRepository.findById(1L).get();
		assertEquals(client1.getId(), client.getId());
	}

	@Test
	@Order(3)
	void testGetAllClientsForVehicle() {
		Vehicle vehicle = new Vehicle(4L, "Toyota Corolla", "Preto", "FZY3R17");
		List<Client> clientList = clientRepository.findByVehicles_licensePlate(vehicle.getLicensePlate());
		assertNotNull(clientList);
	}

	@Test
	@Order(4)
	void testInsert() {
		Client client1 = new Client(2L, "Mar", "Sil", "12485", "Rua Antonio", "(11) 97845-2222");
		clientRepository.save(client1);
		assertThat(client.getId()).isGreaterThan(0);
	}

	@Test
	@Order(5)
	void testUpdate() {
		client.setFirstName("Mery");
		clientRepository.save(client);
		assertEquals(client.getFirstName(), "Mery");
	}

	@Test
	@Order(6)
	void testDelete() {
		when(clientRepository.findById(client.getId())).thenReturn(Optional.of(client));
		clientRepository.deleteById(client.getId());
		verify(clientRepository).deleteById(client.getId());
	}

}
