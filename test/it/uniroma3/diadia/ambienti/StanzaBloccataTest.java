package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccataTest {

	private static final String DIREZIONE_BLOCCATA = "nord";
	private static final String DIREZIONE_LIBERA = "sud";
	private static final Stanza STANZA_RAGGIUNGIBILE = new Stanza("nomeStanzaRaggiungibile");
	private static final Stanza STANZA_IRRAGGIUNGIBILE = new Stanza("nomeStanzaIrraggiungibile");
	private static final Attrezzo ATTREZZO_CHIAVE = new Attrezzo("piedediporco", 1);

	private StanzaBloccata stanzaBloccata;

	@Before
	public void setUp() {
		this.stanzaBloccata = new StanzaBloccata("nomeStanzaBloccata", ATTREZZO_CHIAVE.getNome(), DIREZIONE_BLOCCATA);
	}

	public void setDirezioneBloccataSuUnaStanza(Stanza stanza) {
		stanza.impostaStanzaAdiacente(DIREZIONE_LIBERA, STANZA_RAGGIUNGIBILE);
		stanza.impostaStanzaAdiacente(DIREZIONE_BLOCCATA, STANZA_IRRAGGIUNGIBILE);
	}

	@Test
	public void testGetStanzaAdiacenteConDirezioneLibera() {
		setDirezioneBloccataSuUnaStanza(this.stanzaBloccata);
		assertEquals(STANZA_RAGGIUNGIBILE, this.stanzaBloccata.getStanzaAdiacente(DIREZIONE_LIBERA));
	}

	@Test
	public void testGetStanzaAdiacenteDirezioneBloccata() {
		setDirezioneBloccataSuUnaStanza(this.stanzaBloccata);
		assertEquals(this.stanzaBloccata, this.stanzaBloccata.getStanzaAdiacente(DIREZIONE_BLOCCATA));
	}

	@Test
	public void testGetStanzaAdiacenteDirezioneSbloccata() {
		setDirezioneBloccataSuUnaStanza(this.stanzaBloccata);
		this.stanzaBloccata.addAttrezzo(ATTREZZO_CHIAVE);
		assertEquals(STANZA_IRRAGGIUNGIBILE, this.stanzaBloccata.getStanzaAdiacente(DIREZIONE_BLOCCATA));
	}
}
