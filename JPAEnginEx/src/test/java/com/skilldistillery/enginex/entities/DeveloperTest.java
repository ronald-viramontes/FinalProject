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

class DeveloperTest {
	public static EntityManagerFactory emf;
	private EntityManager em;
	private Developer devAcct;
	
	
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
		devAcct = em.find(Developer.class, 1);
		
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		devAcct = null;
		
	}

	@Test
	@DisplayName("test entity mappings for Developer")
	void test() {
		assertNotNull(devAcct);
		assertEquals("Ron", devAcct.getFirstName());
	}
	
	@Test
	@DisplayName("test relationship mappings on Developer")
	void test2() {
		assertEquals("rodfed", devAcct.getUser().getUsername());
		assertEquals("Java", devAcct.getSkills().get(0).getSkillTitle());
		assertEquals("Full stack java developer", devAcct.getExperiences().get(0).getJobTitle());
		assertEquals("Trade Skill", devAcct.getEducations().get(0).getEducationType());
	}

	
}
