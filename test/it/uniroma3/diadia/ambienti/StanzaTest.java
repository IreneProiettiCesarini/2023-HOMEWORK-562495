package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaTest {

	static final private String[] elencoDirezioniAccettabili = { "nord", "sud", "est", "ovest" };
	private static final int MAX_ATTREZZI = 10;
	private static final Stanza STANZA_NORD = new Stanza("stanzaAdjNord");
	private static final Attrezzo ATTREZZO = new Attrezzo("attrezzoBase", 1);
	private static final String NORD = "nord";

	protected Stanza stanza;

	@Before
	public void setUp() {
		this.stanza = new Stanza("stanzaBase");
		this.stanza.impostaStanzaAdiacente(NORD, STANZA_NORD);
		this.stanza.addAttrezzo(ATTREZZO);
	}

	@Test
	public void testGetStanzaAdiacenteNull() {
		assertNull(this.stanza.getStanzaAdiacente(null));
	}

	@Test
	public void testGetStanzaAdiacente() {
		assertSame(STANZA_NORD, this.stanza.getStanzaAdiacente(NORD));
	}

	@Test
	public void testGetStanzaAdiacenteDirezioneNonValida() {
		String nordEst = "nord-est";
		assertNull(this.stanza.getStanzaAdiacente(nordEst));
	}

	@Test
	public void testImpostaStanzaAdiacenteDirezioneValida() {
		String sud = "sud";
		Stanza stanzaSud = new Stanza("stanzaAdjSud");
		this.stanza.impostaStanzaAdiacente(sud, stanzaSud);
		assertSame(stanzaSud, this.stanza.getStanzaAdiacente(sud));
	}

	@Test
	public void testImpostaStanzaAdiacenteDirezionePreEsistente() {
		String nordNuovo = "nord";
		Stanza stanzaNordNuova = new Stanza("stanzaAdjNordNuova");
		this.stanza.impostaStanzaAdiacente(nordNuovo, stanzaNordNuova);
		assertSame(stanzaNordNuova, this.stanza.getStanzaAdiacente(nordNuovo));
	}

	@Test
	public void testImpostaMassimo4DirezioniRaggiungibili() {
		for (String i : elencoDirezioniAccettabili) {
			this.stanza.impostaStanzaAdiacente(i, new Stanza("stanzaAdj" + i));
		}
		String inPiu = "sud-est";
		this.stanza.impostaStanzaAdiacente(inPiu, new Stanza("stanzaInPiu"));
		assertFalse(this.stanza.getStanzeAdiacenti().containsKey(inPiu));
	}

	@Test
	public void testImpostaStanzaAdiacenteDirezioneNonValida() {
		String nonValida = "nonValida";
		Stanza stanzaNonValida = new Stanza("stanzaAdjNonValida");
		this.stanza.impostaStanzaAdiacente(nonValida, stanzaNonValida);
		assertFalse(this.stanza.getStanzeAdiacenti().containsKey(nonValida));
	}

	@Test
	public void testGetDirezioniStanzaVuota() {
		Stanza stanzaVuota = new Stanza("stanzaVuota");
		assertEquals(0, stanzaVuota.getDirezioni().size());
	}

	@Test
	public void testGetDirezioniSingola() {
		assertEquals(1, this.stanza.getDirezioni().size());
	}

	@Test
	public void testAddAttrezzoSingolo() {
		Attrezzo attrezzoNuovo = new Attrezzo("attrezzoNuovo", 1);
		this.stanza.addAttrezzo(attrezzoNuovo);
		assertTrue(this.stanza.hasAttrezzo(attrezzoNuovo.getNome()));
	}

	@Test
	public void testAddAttrezzoOltreMax() {
		for (int i = 0; i < MAX_ATTREZZI-1; i++) {
			assertTrue(this.stanza.addAttrezzo(new Attrezzo("attrezzo" + i, 1)));
		}
		Attrezzo attrezzoInPiu = new Attrezzo("attrezzoInPiu", 1);
		assertFalse(this.stanza.addAttrezzo(attrezzoInPiu));
		assertFalse(this.stanza.hasAttrezzo(attrezzoInPiu.getNome()));
	}

	@Test
	public void testGetAttrezziStanzaVuota() {
		Stanza stanzaVuota = new Stanza("stanzaVuota");
		assertEquals(0, stanzaVuota.getAttrezzi().size());
	}

	@Test
	public void testGetAttrezziSingleton() {
		assertEquals(1, this.stanza.getAttrezzi().size());
	}

	@Test
	public void testGetAttrezzoNonContenuto() {
		Attrezzo attrezzoNonContenuto = new Attrezzo("attrezzoNonContenuto", 1);
		assertNull(this.stanza.getAttrezzo(attrezzoNonContenuto.getNome()));
	}

	@Test
	public void testGetAttrezzoContenuto() {
		assertEquals(ATTREZZO, this.stanza.getAttrezzo(ATTREZZO.getNome()));
	}

	@Test
	public void testGetAttrezzoNull() {
		assertNull(this.stanza.getAttrezzo(null));
	}

	@Test
	public void testHasAttrezzoSingolo() {
		assertTrue(this.stanza.hasAttrezzo(ATTREZZO.getNome()));
	}

	@Test
	public void testHasAttrezzoStanzaVuota() {
		Stanza stanzaVuota = new Stanza("stanzaVuota");
		assertFalse(stanzaVuota.hasAttrezzo(ATTREZZO.getNome()));
	}

	@Test
	public void testHasAttrezzoNonContenuto() {
		Attrezzo attrezzoNonContenuto = new Attrezzo("attrezzoNonContenuto", 1);
		assertFalse(this.stanza.hasAttrezzo(attrezzoNonContenuto.getNome()));
	}

	@Test
	public void testHasAttrezzoNull() {
		assertFalse(this.stanza.hasAttrezzo(null));
	}

	@Test
	public void testRemoveAttrezzoNull() {
		assertFalse(this.stanza.removeAttrezzo(null));
	}

	@Test
	public void testRemoveAttrezzoNonContenuto() {
		Attrezzo attrezzoNonContenuto = new Attrezzo("attrezzoNonContenuto", 1);
		assertFalse(this.stanza.getAttrezzi().contains(attrezzoNonContenuto));
		assertFalse(this.stanza.removeAttrezzo(attrezzoNonContenuto));
		assertFalse(this.stanza.getAttrezzi().contains(attrezzoNonContenuto));
	}

	@Test
	public void testRemoveAttrezzoContenuto() {
		assertTrue(this.stanza.getAttrezzi().contains(ATTREZZO));
		assertTrue(this.stanza.removeAttrezzo(ATTREZZO));
		assertFalse(this.stanza.getAttrezzi().contains(ATTREZZO));
	}
}
