package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Labirinto {

	private Stanza stanzaDiEntrata;
	private Stanza stanzaVincente;

	public Labirinto() {
		init();
	}

	/**
	 * metodo di inizializzazione del labirinto
	 */
	private void init() {

		/* crea stanze del labirinto */
		Stanza atrio = new Stanza("Atrio");
		Stanza aulaN11 = new Stanza("Aula N11");
		Stanza aulaN10 = new Stanza("Aula N10");
		Stanza laboratorio = new Stanza("Laboratorio Campus");
		Stanza biblioteca = new Stanza("Biblioteca");

		/* collega le stanze */
		atrio.impostaStanzaAdiacente("nord", biblioteca);
		atrio.impostaStanzaAdiacente("est", aulaN11);
		atrio.impostaStanzaAdiacente("sud", aulaN10);
		atrio.impostaStanzaAdiacente("ovest", laboratorio);
		aulaN11.impostaStanzaAdiacente("est", laboratorio);
		aulaN11.impostaStanzaAdiacente("ovest", atrio);
		aulaN10.impostaStanzaAdiacente("nord", atrio);
		aulaN10.impostaStanzaAdiacente("est", aulaN11);
		aulaN10.impostaStanzaAdiacente("ovest", laboratorio);
		laboratorio.impostaStanzaAdiacente("est", atrio);
		laboratorio.impostaStanzaAdiacente("ovest", aulaN11);
		biblioteca.impostaStanzaAdiacente("sud", atrio);

		/* crea gli attrezzi */
		Attrezzo osso = new Attrezzo("osso", 1);
		Attrezzo stecchino = new Attrezzo("stecchino", 1);
		Attrezzo spada = new Attrezzo("spada", 5);
		Attrezzo dolcetto = new Attrezzo("dolcetto", 1);

		/* pone gli attrezzi nelle stanze */
		aulaN10.addAttrezzo(dolcetto);
		atrio.addAttrezzo(osso);
		laboratorio.addAttrezzo(stecchino);
		aulaN11.addAttrezzo(spada);

		/* inizio del gioco */
		this.stanzaDiEntrata = atrio;
		this.stanzaVincente = biblioteca;
	}

	public Stanza getStanzaDiEntrata() {
		return this.stanzaDiEntrata;
	}

	public Stanza getStanzaVincente() {
		return this.stanzaVincente;
	}
}
