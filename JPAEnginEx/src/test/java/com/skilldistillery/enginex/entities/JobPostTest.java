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

class JobPostTest {
	public static EntityManagerFactory emf;
	private EntityManager em;
	private JobPost post;
	
	
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
		post = em.find(JobPost.class, 1);
		
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		post = null;
		
	}

	@Test
	@DisplayName("test entity mappings on JobPost")
	void test() {
		assertNotNull(post);
		assertEquals("Simple profile HTML page", post.getJobRequirements());
		assertEquals(5, post.getStartDate().getDayOfMonth());
	}
	
	@Test
	@DisplayName("test relationship mappings on JobPost")
	void test2() {
		assertNotNull(post);
		assertEquals("Web Developer", post.getType().getName());
		assertEquals("Ronald", post.getUser().getFirstName());
		assertEquals("Complete", post.getStatus().getName());
		assertEquals("Approved", post.getApplications().get(0).getAppStatus().getName());
		assertEquals(10, post.getApplications().get(0).getDetail().getRating());
	}

}
