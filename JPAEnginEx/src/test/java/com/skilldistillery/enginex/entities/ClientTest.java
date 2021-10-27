package com.skilldistillery.enginex.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ClientTest {
	public static EntityManagerFactory emf;
	private EntityManager em;
	private Client client;
	
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("JPAEnginEx");
		
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
		
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		client = em.find(Client.class, 1);
		
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		client = null;
		
	}

	@Test
	@DisplayName("test entity mappings for Developer")
	void test() {
		assertNotNull(client);
		assertEquals("Don", client.getFirstName());
	}
	
	@Test
	@DisplayName("test relationship mappings on Developer")
	void test2() {
		assertEquals("Don", client.getFirstName());
		assertEquals("Johnson", client.getLastName());
		assertEquals("Hollywood", client.getCompany().getCompanyName());
		assertEquals("donjohn", client.getUser().getUsername());
	
	}

	
}
