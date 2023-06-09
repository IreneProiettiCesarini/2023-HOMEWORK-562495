package it.uniroma3.diadia.giocatore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import it.uniroma3.diadia.Configuratore;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.attrezzi.ComparatorePerPeso;

/**
 * La classe Borsa è associata ad un player, contiene degli attrezzi il cui peso
 * totale non può superare uno specifico peso di default
 */
public class Borsa {

	public final static int DEFAULT_PESO_MAX_BORSA = Configuratore.getPesoMax();
	private Map<String, Attrezzo> attrezzi; // arrai di riferimenti ad Attrezzi
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
	 * @param int peso massimo che può contenere la borsa
	 */
	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new HashMap<String, Attrezzo>();
	}

	/**
	 * metodo che aggiunge un Attrezzo
	 * 
	 * @param Attrezzo che si vuole aggiungere,
	 * @return Boolean true se l'aggiunta è andata a buon fine, false altrimenti
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (attrezzo == null)
			return false;
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax()) {
			return false;
		}
//		if (this.numeroAttrezzi == 10) {
//			return false;
//		}
//		if (this.attrezzi.containsKey(attrezzo.getNome())) {
//			return false;
//		}
		this.attrezzi.put(attrezzo.getNome(), attrezzo);
		return true;
	}

	/**
	 * metodo che ritorna il peso massimo che possono avere gli Attrezzi contenuti
	 * nella Borsa
	 */
	public int getPesoMax() {
		return pesoMax;
	}

	/**
	 * metodo getter
	 * 
	 * @param String nome dell'attrezzo da cercare
	 * @return Attrezzo contenuto, null nel caso in non sia contenuto
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		for(String k: this.attrezzi.keySet()) {
			if(k.equals(nomeAttrezzo)) {
				return this.attrezzi.get(k);
			}
		}
		return null;
	}

	/**
	 * metodo getter
	 * 
	 * @return int peso corrente della Borsa (al momento dell'ivocazione del
	 *         metodo), 0 altrimenti
	 */
	public int getPeso() {
		int peso = 0;
		for (String k : this.attrezzi.keySet())
			peso += this.attrezzi.get(k).getPeso();
		return peso;
	}

	public Map<String, Attrezzo> getAttrezzi() {
		return this.attrezzi;
	}

	/**
	 * metodo che permette di verificare se la borsa è vuota
	 * 
	 * @return Boolean true se la borsa contiene almeno 1 attrezzo, false altrimenti
	 */
	public boolean isEmpty() {
		return this.attrezzi.isEmpty();
	}

	/**
	 * metodo che permette di verificare se un certo attrezzo è contenuto nella
	 * borsa o meno
	 * 
	 * @param String nome dell'attrezzo cercato
	 * @return Boolean true se la Borsa contiene l'attrezzo, false altrimenti
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo) != null;
	}

	/**
	 * metodo che permette di modificare gli attrezzi contenuti nella borsa
	 * 
	 * @param String nome dell'attrezzo che si intende rimuovere
	 * @return Attrezzo rimosso
	 */
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		if (this.attrezzi.isEmpty()) {
			return null;
		}
		for(String k: this.attrezzi.keySet()) {
			if(k.equals(nomeAttrezzo)) {
				return this.attrezzi.remove(k);
			}
		}
		return null;
	}

	/**
	 * metodo toString che specifica il formato di stampa della classe Borsa
	 */
	public String toString() {
		String s = new String();
		if (!this.isEmpty()) {
			this.getContenutoOrdinatoPerPeso();
			s += "Contenuto borsa (" + this.getPeso() + "kg/" + this.getPesoMax() + "kg): ";
			for (String k : this.attrezzi.keySet())
				s += this.attrezzi.get(k).toString() + " ";
		} else
			s += "Borsa vuota";
		return s;
	}

	/**
	 * Metodo che ordina e ritorna il riferimento alla lista degli attrezi ordinati
	 * per peso
	 * 
	 * @return List<Att>
	 */
	public List<Attrezzo> getContenutoOrdinatoPerPeso() {
		List<Attrezzo> list=new ArrayList<Attrezzo>();
		for(String k: this.attrezzi.keySet()) {
			list.add(this.attrezzi.get(k));
		}
		ComparatorePerPeso cmpPerPeso = new ComparatorePerPeso();
		Collections.sort(list, cmpPerPeso);
		return list;
	}

	/**
	 * Metodo che ritorna l'insieme degli attrezzi nella borsa ordinati per nome
	 * 
	 * @return Set<Att>, in particolare HashSet<Att>
	 */
	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome() {
		SortedSet<Attrezzo> insieme = new TreeSet<Attrezzo>();
		for (String k : this.attrezzi.keySet()) {
			insieme.add(this.attrezzi.get(k));
		}
		return insieme;
	}

	/**
	 * Metodo che ritorna una mappa le cui chiavi sono interi rappresentanti il peso
	 * degli Attrezzi e i cui valori sono Insiemi di Attrezzi il cui peso è
	 * equivalente
	 * 
	 * @return Map<Int, Set<Att>>
	 */
	public Map<Integer, Set<Attrezzo>> getContenutoRaggruppatoPerPeso() {
		Map<Integer, Set<Attrezzo>> mappa = new HashMap<Integer, Set<Attrezzo>>();
		Set<Attrezzo> tmp;
		for (String k : this.attrezzi.keySet()) { // scorro lista di attrezzi
			tmp = mappa.get(this.attrezzi.get(k).getPeso());
			if (tmp == null) // non esiste nessun insieme associato alla chiave
				tmp = new HashSet<Attrezzo>(); // creo l'insieme ospitante
			tmp.add(this.attrezzi.get(k));
			mappa.put(this.attrezzi.get(k).getPeso(), tmp);
		}
		return mappa;
	}

	/**
	 * Metodo che restituisce gli attrezzi contenuti nella borsa ordinati per peso
	 * 
	 * @return Set<Att>
	 */
	public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso() {
		ComparatorePerPeso cmpPerPeso = new ComparatorePerPeso();
		SortedSet<Attrezzo> set=new TreeSet<Attrezzo>(cmpPerPeso);
		for(String k: this.attrezzi.keySet()){
			set.add(this.attrezzi.get(k));
		}
		return set;
	}

}
