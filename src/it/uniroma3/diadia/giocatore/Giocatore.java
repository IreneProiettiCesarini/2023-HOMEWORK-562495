package it.uniroma3.diadia.giocatore;

/**
 * La classe giocatore è associata a ciascuna partita ed ha il compito di
 * gestire i CFU e la borsa del player
 */
public class Giocatore {

	static final private int CFU_INIZIALI = 20;

	private String nomePlayer;
	private Borsa borsa;
	private int cfu;

	public Giocatore(String nome) {
		this.nomePlayer = nome;
		this.cfu = CFU_INIZIALI;
		this.borsa = new Borsa();
	}

	public String getNomePlayer() {
		return this.nomePlayer;
	}

	public Borsa getBorsa() {
		return this.borsa;
	}

	public int getCfu() {
		return this.cfu;
	}
	
	public String toStringCfu() {
		return ""+this.cfu+"";
	}

	public void setNomePlayer(String nomePlayer) {
		this.nomePlayer = nomePlayer;
	}

	public void setBorsa(Borsa borsa) {
		this.borsa = borsa;
	}

	public void setCfu(int cfu) {
		this.cfu = cfu;
	}
}
