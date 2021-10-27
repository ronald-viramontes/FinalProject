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

class JobStatusTest {
	public static EntityManagerFactory emf;
	private EntityManager em;
	private JobStatus status;
	
	
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
		status = em.find(JobStatus.class, 1);
		
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		status = null;
		
	}

	@Test
	void test() {
		assertNotNull(status);
		assertEquals("Complete", status.getName());
	}

	@Test
	@DisplayName("test relationship mappings on JobStatus")
	void test2() {
		assertNotNull(status);
		assertEquals("Full stack application to manage orders restaurant orders for my small business", status.getJobPosts().get(0).getJobRequirements());
	}

}
