package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;

public class FabbricaDiComandiFisarmonicaTest {

	private FabbricaDiComandiFisarmonica fdcf;
	private Comando comando;
	private IO io;

	private String inputAiuto = "aiuto";
	private String inputFine = "fine";
	private String inputGuarda = "guarda";
	private String inputPrendi = "prendi attrezzo";
	private String inputPosa = "posa attrezzo";
	private String inputVai = "vai direzione";
	private String inputNonValido = "non valido";

	@Before
	public void setUp() {
		this.fdcf = new FabbricaDiComandiFisarmonica();
		this.comando = null;
		this.io = new IOConsole();
	}

	@Test
	public void testRiconoscimentoComandoAiuto() {
		this.comando = fdcf.costruisciComando(inputAiuto, this.io);
		assertEquals("aiuto", this.comando.getNome());
		assertNull(this.comando.getParametro());
	}

	@Test
	public void testRiconoscimentoComandoFine() {
		this.comando = fdcf.costruisciComando(inputFine, this.io);
		assertEquals("fine", this.comando.getNome());
		assertNull(this.comando.getParametro());
	}

	@Test
	public void testRiconoscimentoComandoGuarda() {
		this.comando = fdcf.costruisciComando(inputGuarda, this.io);
		assertEquals("guarda", this.comando.getNome());
		assertNull(this.comando.getParametro());
	}

	@Test
	public void testRiconoscimentoComandoPrendi() {
		this.comando = fdcf.costruisciComando(inputPrendi, this.io);
		assertEquals("prendi", this.comando.getNome());
		assertEquals("attrezzo", this.comando.getParametro());
	}

	@Test
	public void testRiconoscimentoComandoPosa() {
		this.comando = fdcf.costruisciComando(inputPosa, this.io);
		assertEquals("posa", this.comando.getNome());
		assertEquals("attrezzo", this.comando.getParametro());
	}

	@Test
	public void testRiconoscimentoComandoVai() {
		this.comando = fdcf.costruisciComando(inputVai, this.io);
		assertEquals("vai", this.comando.getNome());
		assertEquals("direzione", this.comando.getParametro());
	}

	@Test
	public void testRiconoscimentoComandoNonValido() {
		this.comando = fdcf.costruisciComando(inputNonValido, this.io);
		assertEquals("invalido", this.comando.getNome());
		assertNull(this.comando.getParametro());
	}
}
