package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagicaTest {

	private static final Stanza STANZA = new Stanza("stanzaBase");
	protected static final Attrezzo ATTREZZO = new Attrezzo("attrezzoBase", 1);
	protected StanzaMagica stanzaMagica;

	@Before
	public void setUp() {
		this.stanzaMagica = new StanzaMagica(STANZA.getNome());
	}

	@Test
	public void testAddAttrezzoSingolo() {
		this.stanzaMagica.addAttrezzo(ATTREZZO);
		assertTrue(this.stanzaMagica.hasAttrezzo(ATTREZZO.getNome()));
	}

	@SuppressWarnings("static-access")
	@Test
	public void testAggiungiMassimoSogliaDiDefaultAttrezzi() {
		for (int i = 0; i < this.stanzaMagica.NUMERO_MASSIMO_ATTREZZI; i++) {
			Attrezzo attrezzoI = new Attrezzo("attrezzo" + i, 1);
			assertTrue(this.stanzaMagica.addAttrezzo(attrezzoI));
		}
		assertFalse(this.stanzaMagica.addAttrezzo(ATTREZZO));
	}

	@Test
	public void testIsNotMagic() {
		assertTrue(this.stanzaMagica.addAttrezzo(ATTREZZO));
		assertSame(this.stanzaMagica.getAttrezzo("attrezzoBase"), ATTREZZO);
	}

	@SuppressWarnings("static-access")
	@Test
	public void testIsMagic() {
		for (int i = 0; i < this.stanzaMagica.SOGLIA_MAGICA_DEFAULT; i++) {
			Attrezzo attrezzoI = new Attrezzo("attrezzo" + i, 1);
			assertTrue(this.stanzaMagica.addAttrezzo(attrezzoI));
		}
		Attrezzo magico = new Attrezzo("magico", 1);
		this.stanzaMagica.addAttrezzo(magico);
		assertNull(this.stanzaMagica.getAttrezzo(magico.getNome()));
		Attrezzo magicoInverso = new Attrezzo("ocigam", 1);
		assertNotNull(this.stanzaMagica.getAttrezzo(magicoInverso.getNome()));
	}

	@Test
	public void testModificaAttrezzoComportamentoMagico() {
		Attrezzo chiave = new Attrezzo("chiave", 3);
		Attrezzo modificato = this.stanzaMagica.modificaAttrezzo(chiave);
		assertEquals("evaihc", modificato.getNome());
		assertSame(6, modificato.getPeso());
	}
}
