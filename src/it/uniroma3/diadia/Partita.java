package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

public class Partita {

	private Labirinto labirinto;
	private Stanza stanzaCorrente;
	private boolean finita;
	private Giocatore player;
	private IO io;

	public Partita(IO io, Labirinto labirinto) {
		this.io = io;
		this.labirinto=labirinto;
		this.stanzaCorrente = this.labirinto.getStanzaIniziale();
		this.player = new Giocatore();
		this.finita = false;
	}

	/* METODI GETTER */

	public Stanza getStanzaVincente() {
		if(this.labirinto==null) return null;
		return this.labirinto.getStanzaVincente();
	}

	public Stanza getStanzaCorrente() {
		if (this.labirinto==null) return null;
		return this.stanzaCorrente;
	}

	public Labirinto getLabirinto() {
		return this.labirinto;
	}

	public boolean getStatoPartita() {
		if(this.labirinto==null) return false;
		return this.finita;
	}

	public Giocatore getPlayer() {
		return this.player;
	}

	public IO getIO() {
		return this.io;
	}

	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * 
	 * @return vero se partita vinta
	 */
	public boolean vinta() {
		if(this.labirinto==null) return false;
		if (this.labirinto.getStanzaVincente() == null)
			return false;
		return this.getStanzaCorrente() == this.labirinto.getStanzaVincente();
	}

	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * 
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		if(this.labirinto==null) return true;
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

	public void setLabirinto(Labirinto labirinto) {
		this.labirinto = labirinto;
		this.stanzaCorrente = this.labirinto.getStanzaIniziale();
	}

	public String toString() {
		return "CFU: " + this.player.getCfu() + "";
	}
}
