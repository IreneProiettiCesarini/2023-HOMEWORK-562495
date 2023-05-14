package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoVaiTest {

	private static final Labirinto BILOCALE = new LabirintoBuilder().addStanzaIniziale("base").addStanza("baseNord")
			.addAdiacenza("base", "baseNord", "nord").addAdiacenza("baseNord", "base", "sud").getLabirinto();
	private static final Labirinto TRILOCALE = new LabirintoBuilder().addStanzaIniziale("base").addStanza("baseNord")
			.addAdiacenza("base", "baseNord", "nord").addAdiacenza("baseNord", "base", "sud").addStanza("baseNordEst")
			.addAdiacenza("baseNord", "baseNordEst", "est").addAdiacenza("baseNordEst", "baseNord", "ovest")
			.getLabirinto();
	private Labirinto trilocale_bloccato = new LabirintoBuilder().addStanzaIniziale("base")
			.addStanzaBloccata("baseNord", "piedediporco", "est").addAdiacenza("base", "baseNord", "nord")
			.addAdiacenza("baseNord", "base", "sud").addStanza("baseNordEst")
			.addAdiacenza("baseNord", "baseNordEst", "est").addAdiacenza("baseNordEst", "baseNord", "ovest")
			.getLabirinto();

	private Partita partita;

	private Comando vai;

	@Before
	public void setUp() {
		this.vai = new ComandoVai();
	}

	@Test
	public void testComandoVaiDirezioneNonDisponibile() {
		this.partita = new Partita(new IOConsole(), BILOCALE);
		this.vai.setParametro("nord-est");
		this.vai.esegui(partita);
		assertEquals("base", this.partita.getStanzaCorrente().getNome());
	}

	@Test
	public void testComandoVaiBilocaleUnidirezionaleValido() {
		this.partita = new Partita(new IOConsole(), BILOCALE);
		this.vai.setParametro("nord");
		this.vai.esegui(partita);
		assertEquals("baseNord", this.partita.getStanzaCorrente().getNome());
	}

	@Test
	public void testComandoVaiBilocaleBidirezionaleVerificaCorrispondenzaAdiacenze() {
		this.partita = new Partita(new IOConsole(), BILOCALE);
		this.vai.setParametro("nord");
		this.vai.esegui(partita);
		assertEquals("baseNord", this.partita.getStanzaCorrente().getNome());
		this.vai.setParametro("sud");
		this.vai.esegui(partita);
		assertEquals("base", this.partita.getStanzaCorrente().getNome());
	}

	@Test
	public void testComandoVaiTrilocaleMultidirezionale() {
		this.partita = new Partita(new IOConsole(), TRILOCALE);
		this.vai.setParametro("nord");
		this.vai.esegui(partita);
		assertEquals("baseNord", this.partita.getStanzaCorrente().getNome());
		this.vai.setParametro("est");
		this.vai.esegui(partita);
		assertEquals("baseNordEst", this.partita.getStanzaCorrente().getNome());
		this.vai.setParametro("ovest");
		this.vai.esegui(partita);
		assertEquals("baseNord", this.partita.getStanzaCorrente().getNome());
		this.vai.setParametro("sud");
		this.vai.esegui(partita);
		assertEquals("base", this.partita.getStanzaCorrente().getNome());
	}

	@Test
	public void testComandoVaiBilocaleBloccato() {
		this.partita = new Partita(new IOConsole(), trilocale_bloccato);
		this.vai.setParametro("nord");
		this.vai.esegui(partita);
		assertEquals("baseNord", this.partita.getStanzaCorrente().getNome());
		this.vai.setParametro("est");
		this.vai.esegui(partita);
		assertEquals("baseNord", this.partita.getStanzaCorrente().getNome());
	}

	@Test
	public void testComandoVaiBilocaleBloccatoSbloccato() {
		this.partita = new Partita(new IOConsole(), trilocale_bloccato);
		this.vai.setParametro("nord");
		this.vai.esegui(partita);
		assertEquals("baseNord", this.partita.getStanzaCorrente().getNome());
		assertTrue(this.partita.getStanzaCorrente().addAttrezzo(new Attrezzo("piedediporco", 3)));
		this.vai.setParametro("est");
		this.vai.esegui(partita);
		assertEquals("baseNordEst", this.partita.getStanzaCorrente().getNome());
	}

}
