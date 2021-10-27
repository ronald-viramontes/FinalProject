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

class JobApplicationTest {
	public static EntityManagerFactory emf;
	private EntityManager em;
	private JobApplication app;
	
	
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
		app = em.find(JobApplication.class, 1);
		
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		app = null;
		
	}

	@Test
	void test() {
		assertNotNull(app);
		assertEquals(9, app.getDecisionDate().getDayOfMonth());
	}

	@Test
	@DisplayName("test relationship mappings on JobApplication")
	void test2() {
		assertNotNull(app);
		assertEquals("Ron", app.getDeveloper().getFirstName());
		assertEquals("Excellent work!", app.getDetails().get(0).getComment());
	}

}
