package it.uniroma3.diadia.ambienti;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import it.uniroma3.diadia.Configuratore;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class Stanza {

	static final private int NUMERO_MASSIMO_DIREZIONI = Configuratore.getDirezioniMax();
	static final int NUMERO_MASSIMO_ATTREZZI = Configuratore.getAttrezziMax();

	private String nome;
	private List<Attrezzo> attrezzi;
	private Map<Direzione, Stanza> direzione2stanzaAdiacente;
	private List<Direzione> direzioni;
	private AbstractPersonaggio character;

	/**
	 * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
	 * 
	 * @param nome il nome della stanza
	 */
	public Stanza(String nome) {
		if (nome != null) {
			this.nome = nome;
			this.attrezzi = new ArrayList<Attrezzo>();
			this.direzione2stanzaAdiacente = new HashMap<Direzione, Stanza>();
			this.direzioni = new ArrayList<Direzione>();
		}
	}

	/**
	 * Imposta una stanza adiacente, assume che la direzione sia valida.
	 *
	 * @param String direzione in cui sara' posta la stanza adiacente.
	 * @param Stanza stanza adiacente nella direzione indicata dal primo parametro.
	 * @return void
	 */
	public void impostaStanzaAdiacente(Direzione direzione, Stanza stanza) {
		if (stanza == null || direzione == null) // verifico parametri
			return;
		if (this.direzione2stanzaAdiacente.containsKey(direzione)) // aggiorna adiacenza
			this.direzione2stanzaAdiacente.put(direzione, stanza);
		else if (this.direzione2stanzaAdiacente.size() < NUMERO_MASSIMO_DIREZIONI) // imposta nuova adiacenza
			this.direzione2stanzaAdiacente.put(direzione, stanza);
		else
			return;
	}

	public void setPersonaggio(AbstractPersonaggio character) {
		this.character = character;
	}

	/**
	 * Restituisce la stanza adiacente nella direzione specificata
	 * 
	 * @param String direzione richiesta
	 * @return Stanza stanza adiacente in direzione richiesta
	 */
	public Stanza getStanzaAdiacente(Direzione direzione) {
		if (!this.direzione2stanzaAdiacente.containsKey(direzione))
			return null;
		return this.direzione2stanzaAdiacente.get(direzione);
	}

	public Map<Direzione, Stanza> getStanzeAdiacenti() {
		return this.direzione2stanzaAdiacente;
	}

	/**
	 * Restituisce la nome della stanza.
	 * 
	 * @return String nome della stanza
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Restituisce la descrizione della stanza.
	 * 
	 * @return String descrizione della stanza
	 */
	public String getDescrizione() {
		return this.toString();
	}

	public AbstractPersonaggio getPersonaggio() {
		return this.character;
	}

	/**
	 * Restituisce una rappresentazione stringa di questa stanza, stampadone la
	 * descrizione, le uscite e gli eventuali attrezzi contenuti
	 * 
	 * @return String rappresentazione della stanza
	 */
	public String toString() {
		StringBuilder risultato = new StringBuilder();
		risultato.append(this.nome);
		risultato.append("\nPersonaggio: ");
		if (this.getPersonaggio() != null)
			risultato.append(this.getPersonaggio().getNome());
		risultato.append("\nUscite: ");
		for (Direzione i : this.direzione2stanzaAdiacente.keySet()) {
			risultato.append(i.toString() + "; ");
		}
		risultato.append("\nAttrezzi nella stanza: ");
		for (Attrezzo attrezzo : this.attrezzi) {
			risultato.append(attrezzo.toString() + " ");
		}
		return risultato.toString();
	}

	/**
	 * Restituisce la collezione di attrezzi presenti nella stanza.
	 * 
	 * @return List collezione di attrezzi nella stanza
	 */
	public List<Attrezzo> getAttrezzi() {
		return this.attrezzi;
	}

	/**
	 * Mette un attrezzo nella stanza.
	 * 
	 * @param Attrezzo da mettere nella stanza.
	 * @return Boolean true se riesce ad aggiungere l'attrezzo, false atrimenti.
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.attrezzi.size() < NUMERO_MASSIMO_ATTREZZI) {
			for (Attrezzo i : this.attrezzi) {
				if (i.getNome().equals(attrezzo.getNome()))
					return false;
			}
			return this.attrezzi.add(attrezzo);
		}
		return false;
	}

	/**
	 * Rimuove un attrezzo dalla stanza.
	 * 
	 * @param Attrezzo da rimuovere
	 * @return Boolean true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	public boolean removeAttrezzo(Attrezzo attrezzo) {
		if (attrezzo == null || !this.attrezzi.contains(attrezzo))
			return false;
		Iterator<Attrezzo> it = this.attrezzi.iterator();
		while (it.hasNext()) {
			if (it.next().equals(attrezzo)) {
				it.remove();
				return true;
			}
		}
		return false;
	}

	/**
	 * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * 
	 * @param String nome dell'attrezzo da prendere
	 * @return Attrezzo se presente nella stanza, null se l'attrezzo non e'
	 *         presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		if (nomeAttrezzo == null)
			return null;
		for (Attrezzo i : this.attrezzi) {
			if (i.getNome().equals(nomeAttrezzo))
				return i;
		}
		return null;
	}

	/**
	 * Restituisce il numero di attrezzi presente nella stanza
	 * 
	 * @return int numero attrezzi nella stanza
	 */
	public int getNumeroAttrezzi() {
		return this.attrezzi.size();
	}

	public List<Direzione> getDirezioni() {
		for (Direzione key : this.direzione2stanzaAdiacente.keySet()) {
			this.direzioni.add(key);
		}
		return direzioni;
	}

	/**
	 * Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	 * 
	 * @param String nome dell'attrezzo da cercare
	 * @return Boolean true se l'attrezzo esiste nella stanza, false altrimenti.
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		if (nomeAttrezzo == null)
			return false;
		for (Attrezzo i : this.attrezzi) {
			if (i.getNome().equals(nomeAttrezzo))
				return true;
		}
		return false;
	}

	public String printDirezioni() {
		String out = null;
		for (Direzione i : this.direzioni) {
			out = i + "; ";
		}
		return out;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		Stanza that = (Stanza) obj;
		return this.getNome().equals(that.getNome());
	}

	public Stanza getStanzaAdiacenteMinimoAttrezzi() {
		if (this.getStanzeAdiacenti().isEmpty())
			return null;
		Stanza min = this.getStanzaAdiacente(this.getDirezioni().get(0));
		for (Direzione k : this.direzione2stanzaAdiacente.keySet()) {
			if (this.direzione2stanzaAdiacente.get(k).getNumeroAttrezzi() < min.getNumeroAttrezzi())
				min = this.direzione2stanzaAdiacente.get(k);
		}
		return min;
	}

	public Stanza getStanzaAdiacenteMassimoAttrezzi() {
		if (this.getStanzeAdiacenti().isEmpty())
			return null;
		Stanza max = this.getStanzaAdiacente(this.getDirezioni().get(0));
		for (Direzione k : this.direzione2stanzaAdiacente.keySet()) {
			if (this.direzione2stanzaAdiacente.get(k).getNumeroAttrezzi() > max.getNumeroAttrezzi())
				max = this.direzione2stanzaAdiacente.get(k);
		}
		return max;
	}

}
