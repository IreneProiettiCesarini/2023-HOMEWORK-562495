package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

public class Partita {

	private Labirinto labirintoCorrente;
	private Stanza stanzaCorrente;
	private boolean finita;
	private Giocatore player;

	public Partita() {
		this.finita = false;
		this.labirintoCorrente = new Labirinto();
		this.stanzaCorrente = this.labirintoCorrente.getStanzaDiEntrata();
		this.player=new Giocatore("player-uno");
	}

	/* METODI GETTER */

	public Stanza getStanzaVincente() {
		return this.labirintoCorrente.getStanzaVincente();
	}

	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}

	public Labirinto getLabirintoCorrente() {
		return this.labirintoCorrente;
	}
	
	public boolean getStatoPartita() {
		return this.finita;
	}
	
	public Giocatore getPlayer() {
		return this.player;
	}

	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * 
	 * @return vero se partita vinta
	 */
	public boolean vinta() {
		return this.getStanzaCorrente() == this.labirintoCorrente.getStanzaVincente();
	}

	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * 
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		return finita || vinta() || (this.player.getCfu() == 0);
	}

	/* METODI SETTER */

	/**
	 * Imposta la partita come finita
	 *
	 */
	public void setFinita() {
		this.finita = true;
	}
	
	public void setStanzaCorrente(Stanza stanza) {
		this.stanzaCorrente = stanza;
	}

	public void setLabirintoCorrente(Labirinto labirinto) {
		this.labirintoCorrente = labirinto;
	}
}
