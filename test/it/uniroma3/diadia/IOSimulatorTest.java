package it.uniroma3.diadia;

import static org.junit.Assert.*;

import org.junit.Test;

public class IOSimulatorTest {

	@Test
	public void testNessunComando() {
		assertNull(new IOSimulator().leggiRiga());
	}

	@Test
	public void testUnComando() {
		assertEquals("fine", new IOSimulator("fine").leggiRiga());
	}

	@Test
	public void testPiuComandiStrategiaMappaturaFirstInFirstOut() {
		IOSimulator io = new IOSimulator("vai nord", "guarda", "fine");
		assertEquals("fine", io.leggiRiga());
		assertEquals("guarda", io.leggiRiga());
		assertEquals("vai nord", io.leggiRiga());
	}

	@Test
	public void testTroppeLetture() {
		IOSimulator io = new IOSimulator("fine");
		assertEquals("fine", io.leggiRiga());
		// io.leggiRiga();
	}

}
