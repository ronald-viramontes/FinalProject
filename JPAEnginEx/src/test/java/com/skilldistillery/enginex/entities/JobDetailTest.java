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

class JobDetailTest {
	public static EntityManagerFactory emf;
	private EntityManager em;
	private JobDetail detail;
	
	
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
		detail = em.find(JobDetail.class, 1);
		
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		detail = null;
		
	}

	@Test
	void test() {
		assertNotNull(detail);
		assertEquals("Excellent work!", detail.getComment());
	}
	
	@Test
	@DisplayName("test relationship mappings on JobDetail")
	void test2() {
		assertNotNull(detail);
		assertEquals("Approved", detail.getApplication().getStatus());
	}

}
