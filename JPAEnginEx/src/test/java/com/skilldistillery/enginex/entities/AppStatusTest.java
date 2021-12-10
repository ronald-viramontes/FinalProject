package com.skilldistillery.enginex.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AppStatusTest {
	public static EntityManagerFactory emf;
	private EntityManager em;
	private AppStatus appStatus;
	
	
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
		appStatus = em.find(AppStatus.class, 1);
	
		
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		appStatus = null;
	
		
	}

	@Test
	void test() {
		assertNotNull(appStatus);
		assertEquals("Pending Review", appStatus.getName());
	}

	@Test
	@DisplayName("test relationship mappings on AppStatus")
	void test2() {
		
		assertNotNull(appStatus);
		assertTrue(appStatus.getApplications().size() > 0);
	}

}
