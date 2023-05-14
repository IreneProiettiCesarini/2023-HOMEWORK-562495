package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBuiaTest {

	private static final String DESCRIZIONE_STANZA_BUIA = "Qui c'è un buio pesto!";
	private static final Attrezzo ATTREZZO_LUMINOSO = new Attrezzo("attrezzoLuminoso", 1);
	private StanzaBuia stanzaBuia;
	
	@Before
	public void setUp() {
		this.stanzaBuia=new StanzaBuia("nomeStanzaBuia", ATTREZZO_LUMINOSO.getNome());
	}
	
	@Test
	public void testGetDescrizioneStanzaSenzaAttrezzoLuminoso() {
		assertEquals(DESCRIZIONE_STANZA_BUIA, this.stanzaBuia.getDescrizione());
	}
	
	@Test
	public void testGetDescrizioneStanzaConAttrezzoLuminoso() {
		this.stanzaBuia.addAttrezzo(ATTREZZO_LUMINOSO);
		assertNotEquals(DESCRIZIONE_STANZA_BUIA, this.stanzaBuia.getDescrizione());
	}
}
