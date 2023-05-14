package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendiTest {

	private Partita partita;
	
	private Labirinto monolocale = new LabirintoBuilder()
			.addStanzaIniziale("base")
			.addAttrezzo("ascia", 5)
			.getLabirinto();
	private Labirinto monolocale_magico=new LabirintoBuilder()
			.addStanzaMagica("base", 1)
			.addAttrezzo("fiore", 1)
			.addStanzaIniziale("base")
			.getLabirinto();

	private Comando prendi;

	@Before
	public void setUp() {
		this.prendi = new ComandoPrendi();
		this.prendi.setParametro("ascia");
	}

	@Test
	public void testEseguiComandoPrendi() {
		this.partita = new Partita(new IOConsole(), monolocale);
		this.prendi.esegui(this.partita);
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("ascia"));
		assertTrue(this.partita.getPlayer().getBorsa().hasAttrezzo("ascia"));
	}
	
	@Test
	public void testEseguiComandoPrendiAttrezzoNonContenutoNellaStanza() {
		this.partita = new Partita(new IOConsole(), monolocale);
		this.prendi.setParametro("spada");
		this.prendi.esegui(this.partita);
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("spada"));
		assertFalse(this.partita.getPlayer().getBorsa().hasAttrezzo("spada"));
	}
	
	@Test
	public void testEseguiComandoPrendiSuStanzaMagica() {
		this.partita = new Partita(new IOConsole(), monolocale_magico);
		assertTrue(this.partita.getStanzaCorrente().addAttrezzo(new Attrezzo("ascia", 2)));
		this.prendi.esegui(this.partita);
		assertFalse(this.partita.getPlayer().getBorsa().hasAttrezzo("ascia"));
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("ascia"));
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("aicsa"));
		this.prendi.setParametro("aicsa");
		this.prendi.esegui(this.partita);
		assertTrue(this.partita.getPlayer().getBorsa().hasAttrezzo("aicsa"));
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("aicsa"));
	}
}
