package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * La classe Borsa è associata ad un player, contiene degli attrezzi il cui peso
 * totale non può superare uno specifico peso di default
 */
public class Borsa {

	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private Attrezzo[] attrezzi; // arrai di riferimenti ad Attrezzi
	private int numeroAttrezzi; // numero di attrezzi totali contenuti nella borsa
	private int pesoMax; // peso massimo che possono raggiungere gli Attrezzi contenuti (sommati)

	/**
	 * Costruttore di default no-args che invoca, attraverso la parola chiave this,
	 * il costruttore secondario
	 */
	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}

	/**
	 * Costruttore secondario che setta tutte le variabili di stato della classe
	 * Borsa
	 * 
	 * @param peso massimo che può contenere la borsa
	 */
	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new Attrezzo[10]; // speriamo che bastino...
		this.numeroAttrezzi = 0;
	}

	/**
	 * metodo che aggiunge un Attrezzo
	 * 
	 * @param riferimento all'attrezzo che si vuole aggiungere,
	 * @return true se l'aggiunta è andata a buon fine, false altrimenti
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if(attrezzo==null) return false;
		// aggiunge il peso alla borsa e verifica che rientra nei limiti imposti
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax()) {
			return false;
		}
		// verifica che la Borsa può ancora contenere Attrezzi
		if (this.numeroAttrezzi == 10) {
			return false;
		}
		// il nuovo Attrezzo può essere aggiunto
		this.attrezzi[this.numeroAttrezzi] = attrezzo;
		this.numeroAttrezzi++;
		return true;
	}

	/**
	 * metodo che ritorna il peso massimo che possono avere gli Attrezzi contenuti
	 * nella Borsa
	 */
	public int getPesoMax() {
		return pesoMax;
	}

	public int getNumeroAttrezzi() {
		return this.numeroAttrezzi;
	}

	/**
	 * metodo getter
	 * 
	 * @param il nome dell'attrezzo da cercare
	 * @return il riferimento all'Attrezzo contenuto, null nel caso in non sia
	 *         contenuto
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null; // riferimento che verrà fornito come output
		for (int i = 0; i < this.numeroAttrezzi; i++)
			if (this.attrezzi[i].getNome().equals(nomeAttrezzo))
				a = attrezzi[i];
		return a;
	}

	/**
	 * metodo getter
	 * 
	 * @return peso corrente di tutti gli Attrezzi contenuti nella Borsa (al momento
	 *         dell'ivocazione del metodo), 0 altrimenti
	 */
	public int getPeso() {
		int peso = 0;
		for (int i = 0; i < this.numeroAttrezzi; i++)
			peso += this.attrezzi[i].getPeso();
		return peso;
	}

	/**
	 * metodo che permette di verificare se la borsa è vuota
	 * 
	 * @return true se la borsa contiene almeno 1 attrezzo, false altrimenti
	 */
	public boolean isEmpty() {
		return this.numeroAttrezzi == 0;
	}

	/**
	 * metodo che permette di verificare se un certo attrezzo è contenuto nella
	 * borsa o meno
	 * 
	 * @param il nome dell'attrezzo cercato
	 * @return true se la Borsa contiene l'attrezzo, false altrimenti
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo) != null;
	}

	/**
	 * metodo che permette di modificare gli attrezzi contenuti nella borsa
	 * 
	 * @param il nome dell'attrezzo che si intende rimuovere
	 * @return il riferimento all'attrezzo rimosso
	 */
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		if(this.numeroAttrezzi==0) {
			return null;
		}
		Attrezzo a = null;
		for (int i = 0; i < numeroAttrezzi; i++) {
			if (this.attrezzi[i].getNome().equals(nomeAttrezzo)) { // l'Attrezzo è quello cercato
				a = this.attrezzi[i]; // salvo il riferimento all'attrezzo da eliminare in a
				// la cella i dell'array ora referenzia l'ultimo Attrezzo dell'array,
				// quello da eliminare viene sovrascritto
				this.attrezzi[i] = this.attrezzi[numeroAttrezzi - 1];
				this.attrezzi[numeroAttrezzi - 1] = null; // libero cella dell'array attrezzi
				this.numeroAttrezzi = this.numeroAttrezzi - 1;
			}
		}
		return a;
	}

	/**
	 * metodo toString che specifica il formato di stampa della classe Borsa
	 */
	public String toString() {
		String s = new String();
		if (!this.isEmpty()) {
			s += "Contenuto borsa (" + this.getPeso() + "kg/" + this.getPesoMax() + "kg): ";
			for (int i = 0; i < this.numeroAttrezzi; i++)
				s += attrezzi[i].toString() + " ";
		} else
			s += "Borsa vuota";
		return s;
	}

}
