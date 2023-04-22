package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaProtected {

	static final protected int NUMERO_MASSIMO_DIREZIONI = 4;
	static final protected int NUMERO_MASSIMO_ATTREZZI = 10; // prec:10 - per semplicità nelle classi di test

	protected String nome;
	protected Attrezzo[] attrezzi;
	protected int numeroAttrezzi;
	protected Stanza[] stanzeAdiacenti;
	protected int numeroStanzeAdiacenti;
	protected String[] direzioni;

	/**
	 * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
	 * 
	 * @param nome il nome della stanza
	 */
	public StanzaProtected(String nome) {
		if (nome != null) {
			this.nome = nome;
			this.numeroStanzeAdiacenti = 0;
			this.numeroAttrezzi = 0;
			this.direzioni = new String[NUMERO_MASSIMO_DIREZIONI];
			this.stanzeAdiacenti = new Stanza[NUMERO_MASSIMO_DIREZIONI];
			this.attrezzi = new Attrezzo[NUMERO_MASSIMO_ATTREZZI];
		}
	}

	public void setNumeroAttrezzi(int numeroAttrezzi) {
		this.numeroAttrezzi = numeroAttrezzi;
	}

	/**
	 * Restituisce la stanza adiacente nella direzione specificata
	 * 
	 * @param direzione
	 */
	public Stanza getStanzaAdiacente(String direzione) {
		Stanza stanza = null;
		for (int i = 0; i < this.numeroStanzeAdiacenti; i++)
			if (this.direzioni[i].equals(direzione))
				stanza = this.stanzeAdiacenti[i];
		return stanza;
	}

	/**
	 * Restituisce la nome della stanza.
	 * 
	 * @return il nome della stanza
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Restituisce la descrizione della stanza.
	 * 
	 * @return la descrizione della stanza
	 */
	public String getDescrizione() {
		return this.toString();
	}

	/**
	 * Restituisce una rappresentazione stringa di questa stanza, stampadone la
	 * descrizione, le uscite e gli eventuali attrezzi contenuti
	 * 
	 * @return la rappresentazione stringa
	 */
	public String toString() {
		StringBuilder risultato = new StringBuilder();
		risultato.append(this.nome);
		risultato.append("\nUscite: ");
		for (String direzione : this.direzioni) {
			if (direzione != null)
				risultato.append(direzione + "; ");
		}
		risultato.append("\nAttrezzi nella stanza: ");
		for (Attrezzo attrezzo : this.attrezzi) {
			if (attrezzo != null)
				risultato.append(attrezzo.toString() + " ");
		}
		return risultato.toString();
	}

	/**
	 * Restituisce la collezione di attrezzi presenti nella stanza.
	 * 
	 * @return la collezione di attrezzi nella stanza.
	 */
	public Attrezzo[] getAttrezzi() {
		Attrezzo[] attrezzi = new Attrezzo[this.numeroAttrezzi];
		for (int i = 0; i < this.numeroAttrezzi; i++)
			attrezzi[i] = this.attrezzi[i];
		return attrezzi;
	}

	/**
	 * Mette un attrezzo nella stanza.
	 * 
	 * @param attrezzo l'attrezzo da mettere nella stanza.
	 * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.numeroAttrezzi < NUMERO_MASSIMO_ATTREZZI) {
			this.attrezzi[numeroAttrezzi] = attrezzo;
			this.numeroAttrezzi++;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Rimuove un attrezzo dalla stanza.
	 * 
	 * @param Attrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	public boolean removeAttrezzo(Attrezzo attrezzo) {
		if (attrezzo == null)
			return false;
		for (int i = 0; i < this.numeroAttrezzi; i++) {
			if (this.attrezzi[i] == attrezzo) { // il confronto viene eseguito sui riferimenti
				this.attrezzi[i] = this.attrezzi[numeroAttrezzi - 1]; // sovrascrivo il riferimento i dell'attrezzo da
																		// rimuovere
				this.attrezzi[numeroAttrezzi - 1] = null;
				this.numeroAttrezzi = this.numeroAttrezzi - 1;
				return true;
			}
		}
		return false;
	}

	/**
	 * Imposta una stanza adiacente, assume che la direzione sia valida.
	 *
	 * @param direzione direzione in cui sara' posta la stanza adiacente.
	 * @param stanza    stanza adiacente nella direzione indicata dal primo
	 *                  parametro.
	 */
	public void impostaStanzaAdiacente(String direzione, Stanza stanza) {
		if (stanza == null)
			return;
		boolean aggiornato = false;
		for (int i = 0; i < this.direzioni.length; i++)
			if (direzione.equals(this.direzioni[i])) { // verifica quale direzione è stata richiesta
				this.stanzeAdiacenti[i] = stanza; // sovrascrive il valore contenuto
				aggiornato = true;
			}
		if (!aggiornato) // se la direzione non risulta ancora inserita (e quindi occupata)
			if (this.numeroStanzeAdiacenti < NUMERO_MASSIMO_DIREZIONI) { // verifica che la stanza può essere inserita
				this.direzioni[numeroStanzeAdiacenti] = direzione;
				this.stanzeAdiacenti[numeroStanzeAdiacenti] = stanza;
				this.numeroStanzeAdiacenti++;
			}
	}

	/**
	 * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * 
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza. null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		if (nomeAttrezzo == null)
			return null;
		Attrezzo attrezzoCercato;
		attrezzoCercato = null;
		for (Attrezzo attrezzo : this.attrezzi) {
			if (attrezzo != null) {
				if (attrezzo.getNome().equals(nomeAttrezzo))
					attrezzoCercato = attrezzo;
			}
		}
		return attrezzoCercato;
	}

	/**
	 * Restituisce il numero di attrezzi presente nella stanza
	 */
	public int getNumeroAttrezzi() {
		return this.numeroAttrezzi;
	}

	public String[] getDirezioni() {
		String[] direzioni = new String[this.numeroStanzeAdiacenti];
		for (int i = 0; i < this.numeroStanzeAdiacenti; i++)
			direzioni[i] = this.direzioni[i];
		return direzioni;
	}

	/**
	 * Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	 * 
	 * @return true se l'attrezzo esiste nella stanza, false altrimenti.
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		boolean trovato;
		trovato = false;
		for (Attrezzo attrezzo : this.attrezzi) {
			if (attrezzo != null)
				if (attrezzo.getNome().equals(nomeAttrezzo))
					trovato = true;
		}
		return trovato;
	}

	public String printDirezioni() {
		String out = null;
		for (String i : this.getDirezioni()) {
			if (i != null) {
				out = '<' + i + '>';
				;
			}
		}
		return out;
	}

}
