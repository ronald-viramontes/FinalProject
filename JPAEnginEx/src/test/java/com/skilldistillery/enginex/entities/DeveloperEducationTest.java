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

class DeveloperEducationTest {
	public static EntityManagerFactory emf;
	private EntityManager em;
	private DeveloperEducation edu;
	
	
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
		edu = em.find(DeveloperEducation.class, 1);
		
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		edu = null;
		
	}

	@Test
	void test() {
		assertNotNull(edu);
		assertEquals("Trade Skill", edu.getEducationType());
	}
	
	@Test
	@DisplayName("test relationship mappings on education")
	void test2() {
		assertNotNull(edu);
		assertEquals("Ron", edu.getUser().getFirstName());
	}

}
