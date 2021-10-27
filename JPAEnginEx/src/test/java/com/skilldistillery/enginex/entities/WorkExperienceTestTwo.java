package com.skilldistillery.enginex.entities;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WorkExperienceTestTwo {
	public static EntityManagerFactory emf;
	private EntityManager em;
	private WorkExperience work;
	

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
		work = em.find(WorkExperience.class, 1);
		
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		work = null;
		
	}

	@Test
	void test() {
		assertNotNull(work);
		assertEquals("Full stack java developer", work.getJobTitle());
	}

}
