package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.StanzaBuia;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoGuardaTest {

	private static final Attrezzo ATTREZZO_LUMINOSO = new Attrezzo("torcia", 2);
	private static final Labirinto MONOLOCALE = new LabirintoBuilder()
			.addStanzaIniziale("base")
			.getLabirinto();
	private Labirinto monolocale_buio = new LabirintoBuilder()
			.addStanzaBuia("baseBuia", "torcia")
			.addStanzaIniziale("baseBuia")
			.getLabirinto();

	private Partita partita;

	private Comando guarda;

	@Before
	public void setUp() {
		this.guarda = new ComandoGuarda();
	}

	@Test
	public void testComandoGuarda() {
		this.partita = new Partita(new IOConsole(), MONOLOCALE);
		this.guarda.esegui(partita);
		assertEquals("base", this.partita.getStanzaCorrente().getNome());
	}

	@Test
	public void testComandoGuardaStanzaBuia() {
		this.partita = new Partita(new IOConsole(), monolocale_buio);
		this.guarda.esegui(partita);
		assertEquals(StanzaBuia.DESCRIZIONE_STANZA_BUIA, this.partita.getStanzaCorrente().getDescrizione());
	}

	@Test
	public void testComandoGuardaStanzaBuiaSbloccata() {
		this.partita = new Partita(new IOConsole(), monolocale_buio);
		assertTrue(this.partita.getStanzaCorrente().addAttrezzo(ATTREZZO_LUMINOSO));
		this.guarda.esegui(partita);
		assertNotEquals(StanzaBuia.DESCRIZIONE_STANZA_BUIA, this.partita.getStanzaCorrente().getDescrizione());
	}

}
