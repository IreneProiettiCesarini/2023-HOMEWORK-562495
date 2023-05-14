package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.*;

import org.junit.Test;

public class GiocatoreTest {

	@Test
	public void testSetCfuDefault() {
		Giocatore playerTest = new Giocatore();
		assertEquals("Cfu di default non settati correttamente", 20, playerTest.getCfu());
	}

	@Test
	public void testSetCfuInput() {
		Giocatore playerTest = new Giocatore();
		playerTest.setCfu(4);
		assertEquals("Cfu di input non settati correttamente", 4, playerTest.getCfu());
	}
	
	@Test
	public void testSetBorsaDefault() {
		Giocatore playerTest = new Giocatore();
		assertNotNull(playerTest.getBorsa());
	}
	
	@Test
	public void testSetBorsaNuova() {
		Giocatore playerTest = new Giocatore();
		Borsa borsaTest = new Borsa();
		playerTest.setBorsa(borsaTest);
		assertEquals("Borsa non settata correttamente", borsaTest, playerTest.getBorsa());
	}

	@Test
	public void testSetBorsaNull() {
		Giocatore playerTest = new Giocatore();
		playerTest.setBorsa(null);
		assertEquals("Borsa non null", null, playerTest.getBorsa());
	}
}























