package it.uniroma3.diadia.ambienti;

import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Labirinto {

	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;
	private Map<String, Stanza> stanze;
	private Map<String, Attrezzo> attrezzi;

	public Stanza getStanzaIniziale() {
		return stanzaIniziale;
	}

	public void setStanzaIniziale(Stanza stanzaIniziale) {
		this.stanzaIniziale = stanzaIniziale;
	}

	public Stanza getStanzaVincente() {
		return stanzaVincente;
	}

	public void setStanzaVincente(Stanza stanza) {
		this.stanzaVincente = stanza;
	}

	public Map<String, Stanza> getStanze() {
		return stanze;
	}

	public void setStanze(Map<String, Stanza> stanze) {
		this.stanze = stanze;
	}

	public Map<String, Attrezzo> getAttrezzi() {
		return attrezzi;
	}

	public void setAttrezzi(Map<String, Attrezzo> attrezzi) {
		this.attrezzi = attrezzi;
	}

}
