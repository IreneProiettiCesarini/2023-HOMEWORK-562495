package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosaTest {

	public final static Attrezzo ATTREZZO = new Attrezzo("ascia", 5);
	
	private Labirinto monolocale = new LabirintoBuilder()
			.addStanzaIniziale("base")
			.getLabirinto();
	private Labirinto monolocale_magico=new LabirintoBuilder()
			.addStanzaMagica("base", 1)
			.addAttrezzo("fiore", 1)
			.addStanzaIniziale("base")
			.getLabirinto();
	
	private Partita partita;
	
	private Comando posa;

	@Before
	public void setUp() {
		this.posa = new ComandoPosa();
		this.posa.setParametro("ascia");
	}

	@Test
	public void testEseguiComandoPosa() {
		this.partita = new Partita(new IOConsole(), monolocale);
		assertTrue(this.partita.getPlayer().getBorsa().addAttrezzo(ATTREZZO));
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("ascia"));
		this.posa.esegui(this.partita);
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("ascia"));
	}

	@Test
	public void testEseguiComandoPosaAttrezzoNonContenutoNellaBorsa() {
		this.partita = new Partita(new IOConsole(), monolocale);
		this.posa.esegui(this.partita);
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("ascia"));
	}
	
	@Test
	public void testEseguiComandoPosaInUnaStanzaMagicaAttiva() {
		this.partita = new Partita(new IOConsole(), monolocale_magico);
		assertTrue(this.partita.getPlayer().getBorsa().addAttrezzo(ATTREZZO));
		this.posa.esegui(this.partita);
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("ascia"));
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("aicsa"));
	}
}
