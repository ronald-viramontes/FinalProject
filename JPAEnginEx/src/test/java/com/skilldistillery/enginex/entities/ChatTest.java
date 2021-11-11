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
import org.junit.jupiter.api.Test;

class ChatTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Chat chat;

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
		chat = em.find(Chat.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		chat = null;
	}

	@Test
	void test() {
		assertNotNull(chat);
		assertEquals("Hello, Do you have any upcoming jobs?", chat.getMessage());
		assertEquals("Future jobs", chat.getSubject());
		assertEquals(1, chat.getSender().getId());
		assertEquals(2, chat.getReceiver().getId());
		
	}

}
