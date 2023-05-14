package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class LabirintoBuilderTest {

	private static final String STANZA = "base";
	private static final String VINCENTE = "vincente";
	private static final String STANZA_NORD = "baseNord";
	private static final String STANZA_SUD = "baseSud";
	private static final String STANZA_EST = "baseEst";
	private static final String STANZA_OVEST = "baseOvest";

	private static final List<String> elencoDirezioni = new ArrayList<String>();

	private LabirintoBuilder labBuilder;

	@Before
	public void setUp() {
		this.labBuilder = new LabirintoBuilder();
		elencoDirezioni.add("nord");
		elencoDirezioni.add("sud");
		elencoDirezioni.add("ovest");
		elencoDirezioni.add("est");
	}

	@Test
	public void testAddStanzaInizialeMonolocale() {
		Labirinto monolocale = labBuilder.addStanzaIniziale(STANZA).getLabirinto();
		assertEquals(STANZA, monolocale.getStanzaIniziale().getNome());
		assertEquals(STANZA, this.labBuilder.getUltimoAccesso());
	}

	@Test
	public void testAddStanzaInizialeMonolocaleGiaImpostata() {
		Labirinto monolocale = labBuilder.addStanzaIniziale(STANZA).addStanzaIniziale("nuova").getLabirinto();
		assertEquals(STANZA, monolocale.getStanzaIniziale().getNome());
		assertEquals(STANZA, this.labBuilder.getUltimoAccesso());
	}

	@Test
	public void testAddStanzaVincenteConStanzaInizialeNonImpostata() {
		Labirinto monolocale = labBuilder.addStanzaVincente(VINCENTE).getLabirinto();
		assertEquals(VINCENTE, monolocale.getStanzaVincente().getNome());
		assertEquals(VINCENTE, this.labBuilder.getUltimoAccesso());
	}

	@Test
	public void testAddStanzaVincenteMonolocaleGiaImpostata() {
		Labirinto monolocale = labBuilder.addStanzaIniziale(VINCENTE).addStanzaVincente(VINCENTE)
				.addStanzaVincente("nuova").getLabirinto();
		assertEquals(VINCENTE, monolocale.getStanzaVincente().getNome());
		assertEquals(VINCENTE, this.labBuilder.getUltimoAccesso());
	}

	@Test
	public void testAddStanzaMonolocaleConStanzaInizialeNonImpostata() {
		Labirinto monolocale = labBuilder.addStanza(STANZA).getLabirinto();
		assertTrue(monolocale.getStanze().containsKey(STANZA));
		assertEquals(STANZA, this.labBuilder.getUltimoAccesso());
	}

	@Test
	public void testAddStanzaImplicitoTramiteAggiuntaDiUnaStanzaIniziale() {
		Labirinto monolocale = labBuilder.addStanzaIniziale(STANZA).getLabirinto();
		assertTrue(monolocale.getStanze().containsKey(STANZA));
	}

	@Test
	public void testAddStanzaBilocale() {
		Labirinto bilocale = labBuilder.addStanzaIniziale(STANZA).addStanza(STANZA_NORD).getLabirinto();
		assertTrue(bilocale.getStanze().containsKey(STANZA_NORD));
		assertEquals(STANZA_NORD, this.labBuilder.getUltimoAccesso());
	}

	@Test
	public void testAddStanzaGiaPresente() {
		Labirinto locale = labBuilder.addStanzaIniziale(STANZA).addStanza("base").getLabirinto();
		assertSame(locale.getStanze().get("base"), locale.getStanze().get(STANZA));
		assertEquals(STANZA, this.labBuilder.getUltimoAccesso());
	}

	@Test
	public void testSetAdiacenzaBilocale() {
		Labirinto bilocale = labBuilder.addStanzaIniziale(STANZA).addStanza(STANZA_NORD)
				.addAdiacenza(STANZA, STANZA_NORD, elencoDirezioni.get(0)).getLabirinto();
		assertTrue(bilocale.getStanze().containsKey(STANZA));
		assertTrue(bilocale.getStanze().containsKey(STANZA_NORD));
		assertEquals(STANZA_NORD,
				bilocale.getStanze().get(STANZA).getStanzaAdiacente(elencoDirezioni.get(0)).getNome());
		assertEquals(STANZA_NORD, this.labBuilder.getUltimoAccesso());
	}

	@Test
	public void testSetAdiacenzaStanzaBaseNonContenuta() {
		Labirinto bilocale = labBuilder.addStanzaIniziale("diversaBase").addStanza(STANZA_NORD)
				.addAdiacenza(STANZA, STANZA_NORD, elencoDirezioni.get(0)).getLabirinto();
		assertNull(bilocale.getStanze().get(STANZA));
	}

	@Test
	public void testSetAdiacenzaStanzaAdiacenteNonContenuta() {
		Labirinto bilocale = labBuilder.addStanzaIniziale(STANZA).addStanza("nuovaAdiacente")
				.addAdiacenza(STANZA, STANZA_NORD, elencoDirezioni.get(0)).getLabirinto();
		assertNull(bilocale.getStanze().get(STANZA_NORD));
	}

	@Test
	public void testSetAdiacenzaLocaleCompleto() {
		Labirinto locale = labBuilder.addStanzaIniziale(STANZA).addStanza(STANZA_NORD).addStanza(STANZA_SUD)
				.addStanza(STANZA_EST).addStanza(STANZA_OVEST).addAdiacenza(STANZA, STANZA_NORD, elencoDirezioni.get(0))
				.addAdiacenza(STANZA, STANZA_SUD, elencoDirezioni.get(1))
				.addAdiacenza(STANZA, STANZA_EST, elencoDirezioni.get(2))
				.addAdiacenza(STANZA, STANZA_OVEST, elencoDirezioni.get(3)).getLabirinto();
		assertEquals(STANZA_NORD, locale.getStanze().get(STANZA).getStanzaAdiacente(elencoDirezioni.get(0)).getNome());
		assertEquals(STANZA_SUD, locale.getStanze().get(STANZA).getStanzaAdiacente(elencoDirezioni.get(1)).getNome());
		assertEquals(STANZA_EST, locale.getStanze().get(STANZA).getStanzaAdiacente(elencoDirezioni.get(2)).getNome());
		assertEquals(STANZA_OVEST, locale.getStanze().get(STANZA).getStanzaAdiacente(elencoDirezioni.get(3)).getNome());
		assertEquals(STANZA_OVEST, this.labBuilder.getUltimoAccesso());
	}

	@Test
	public void testSetAdiacenzaBilocaleNuovaAdiacenza() {
		Labirinto bilocale = labBuilder.addStanzaIniziale(STANZA).addStanza(STANZA_NORD)
				.addAdiacenza(STANZA, STANZA_NORD, elencoDirezioni.get(0)).addStanza("nuovaBaseNord")
				.addAdiacenza(STANZA, "nuovaBaseNord", elencoDirezioni.get(0)).getLabirinto();
		assertTrue(bilocale.getStanze().containsKey(STANZA_NORD));
		assertTrue(bilocale.getStanze().containsKey("nuovaBaseNord"));
		assertEquals("nuovaBaseNord",
				bilocale.getStanze().get(STANZA).getStanzaAdiacente(elencoDirezioni.get(0)).getNome());
		assertEquals("nuovaBaseNord", this.labBuilder.getUltimoAccesso());
	}

	@Test
	public void testAddAttrezzoMonolocale() {
		Labirinto monolocale = labBuilder.addStanzaIniziale(STANZA).addAttrezzo("ascia", 5).getLabirinto();
		assertTrue(monolocale.getStanze().get(STANZA).hasAttrezzo("ascia"));
	}

	@Test
	public void testAddAttrezzoMonolocaleGiaContenuto() {
		Labirinto monolocale = labBuilder.addStanzaIniziale(STANZA).addAttrezzo("ascia", 5).addAttrezzo("scarpa", 1)
				.addAttrezzo("ascia", 7).getLabirinto();
		assertTrue(monolocale.getStanze().get(STANZA).hasAttrezzo("ascia"));
		assertEquals(5, monolocale.getStanze().get(STANZA).getAttrezzo("ascia").getPeso());
	}

	@Test
	public void testAddAttrezzoBilocaleGiaContenuto() {
		Labirinto bilocale = labBuilder.addStanzaIniziale(STANZA).addAttrezzo("ascia", 5).addAttrezzo("scarpa", 1)
				.addStanza(STANZA_NORD).addAttrezzo("ascia", 7).getLabirinto();
		assertTrue(bilocale.getStanze().get(STANZA).hasAttrezzo("ascia"));
		assertFalse(bilocale.getStanze().get(STANZA_NORD).hasAttrezzo("ascia"));
		assertEquals(5, bilocale.getStanze().get(STANZA).getAttrezzo("ascia").getPeso());
	}

	@Test
	public void testAddStanzaMagicaMonolocale() {
		int sogliaMagica = 1;
		Labirinto monolocale = labBuilder.addStanzaMagica(STANZA, sogliaMagica).getLabirinto();
		assertTrue(monolocale.getStanze().containsKey(STANZA));
		assertEquals(STANZA, this.labBuilder.getUltimoAccesso());
	}

	@Test
	public void testAddStanzaBuiaMonolocale() {
		String nomeAttrezzoLuminoso = "torcia";
		Labirinto monolocale = labBuilder.addStanzaBuia(STANZA, nomeAttrezzoLuminoso).getLabirinto();
		assertTrue(monolocale.getStanze().containsKey(STANZA));
		assertEquals(STANZA, this.labBuilder.getUltimoAccesso());
	}

	@Test
	public void testAddStanzaBloccataMonolocale() {
		String nomeAttrezzoChiave = "passpartou";
		Labirinto monolocale = labBuilder.addStanzaBloccata(STANZA, nomeAttrezzoChiave, elencoDirezioni.get(0))
				.getLabirinto();
		assertTrue(monolocale.getStanze().containsKey(STANZA));
		assertEquals(STANZA, this.labBuilder.getUltimoAccesso());
	}

}
