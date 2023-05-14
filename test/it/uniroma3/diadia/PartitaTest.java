package it.uniroma3.diadia;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;

//import it.uniroma3.diadia.Partita

public class PartitaTest {

	private static final IO IO = new IOConsole();

	private Partita partita;

	private Labirinto bilocaleBase;

	@Before
	public void setUp() {
		this.bilocaleBase = new LabirintoBuilder()
				.addStanzaIniziale("base")
				.addStanzaVincente("vincente")
				.getLabirinto();
	}

	@Test
	public void testGetLabirintoValido() {
		this.partita = new Partita(IO, bilocaleBase);
		assertNotNull(this.partita.getLabirinto());
	}

	@Test
	public void testGetStanzaVincenteDiPartitaValida() {
		this.partita = new Partita(IO, bilocaleBase);
		assertNotNull(this.partita.getStanzaVincente());
	}

	@Test
	public void testGetStanzaCorrenteDiPartitaValida() {
		this.partita = new Partita(IO, bilocaleBase);
		assertEquals("base", this.partita.getStanzaCorrente().getNome());
	}

	@Test
	public void testVintaDiPartitaDefault() {
		this.partita = new Partita(IO, bilocaleBase);
		assertFalse(this.partita.vinta());
	}

	@Test
	public void testVintaDiPartitaVinta() {
		this.partita = new Partita(IO, bilocaleBase);
		this.partita.setStanzaCorrente(this.partita.getLabirinto().getStanzaVincente());
		assertTrue(this.partita.vinta());
	}

	@Test
	public void testVintaDiPartitaSenzaCfu() {
		this.partita = new Partita(IO, bilocaleBase);
		this.partita.getPlayer().setCfu(0);
		assertFalse(this.partita.vinta());
	}

	@Test
	public void testIsFinitaDiPartitaDefault() {
		this.partita = new Partita(IO, bilocaleBase);
		assertFalse(this.partita.isFinita());
	}

	@Test
	public void testIsFinitaDiPartitaFinita() {
		this.partita = new Partita(IO, bilocaleBase);
		this.partita.setFinita();
		assertTrue(this.partita.isFinita());
	}

	@Test
	public void testIsFinitaDiPartitaSenzaCfu() {
		this.partita = new Partita(IO, bilocaleBase);
		this.partita.getPlayer().setCfu(0);
		assertTrue(this.partita.isFinita());
	}

	@Test
	public void testIsFinitaDiPartitaVinta() {
		this.partita = new Partita(IO, bilocaleBase);
		this.partita.setStanzaCorrente(this.partita.getLabirinto().getStanzaVincente());
		assertTrue(this.partita.isFinita());
	}

}
