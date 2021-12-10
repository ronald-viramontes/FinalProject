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
	private JobApplication app2;
	
	
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
		app2 = em.find(JobApplication.class, 2);
		
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		app = null;
		app2 = null;
		
	}

	@Test
	void test() {
		assertNotNull(app);
		assertEquals(5, app.getDate().getDayOfMonth());
	}

	@Test
	void test2() {
		assertNotNull(app2);
		assertEquals("Excellent work on the profile page! Exceeded my expectations", app2.getDetail().getComment());
		assertEquals("Ronald", app2.getJobPost().getUser().getFirstName());
	}

	@Test
	@DisplayName("test relationship mappings on JobApplication")
	void test3() {
		assertNotNull(app);
		assertEquals("Ronald", app.getUser().getFirstName());
		
	}

}
