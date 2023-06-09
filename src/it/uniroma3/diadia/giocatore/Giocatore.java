package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.Configuratore;

/**
 * La classe giocatore è associata a ciascuna partita ed ha il compito di
 * gestire i CFU e la borsa del player
 */
public class Giocatore {

	static final private int CFU_INIZIALI = Configuratore.getCFU();

	private Borsa borsa;
	private int cfu;

	public Giocatore() {
		this.cfu = CFU_INIZIALI;
		this.borsa = new Borsa();
	}

	public Borsa getBorsa() {
		return this.borsa;
	}

	public int getCfu() {
		return this.cfu;
	}

	public String toStringCfu() {
		return "CFU: " + this.cfu;
	}

	public void setBorsa(Borsa borsa) {
		this.borsa = borsa;
	}

	public void setCfu(int cfu) {
		this.cfu = cfu;
	}
}
