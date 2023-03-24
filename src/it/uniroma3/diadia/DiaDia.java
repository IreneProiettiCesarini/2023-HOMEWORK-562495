package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""
			+ "Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n"
			+ "Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"
			+ "I locali sono popolati da strani personaggi, " + "alcuni amici, altri... chissa!\n"
			+ "Ci sono attrezzi che potrebbero servirti nell'impresa:\n"
			+ "puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n"
			+ "o regalarli se pensi che possano ingraziarti qualcuno.\n\n"
			+ "Per conoscere le istruzioni usa il comando 'aiuto'.";

	static final private String[] elencoComandi = { "vai", "aiuto", "fine", "prendi", "posa" };

	private Partita partita;
	private IOConsole console;

	public DiaDia(IOConsole console) {
		this.partita = new Partita();
		this.console = console;
	}

	public void gioca() {
		String istruzione;

		this.console.mostraMessaggio(MESSAGGIO_BENVENUTO);
		do
			istruzione = this.console.leggiRiga();
		while (!processaIstruzione(istruzione));
	}

	/**
	 * Processa una istruzione
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false
	 *         altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire = new Comando(istruzione);

		if (comandoDaEseguire.getNome() == null) {
			this.console.mostraMessaggio("Digita un comando valido");
			return false;
		}
		if (comandoDaEseguire.getNome().equals("fine")) {
			this.fine();
			return true;
		} else if (comandoDaEseguire.getNome().equals("vai")) {
			this.vai(comandoDaEseguire.getParametro());
		} else if (comandoDaEseguire.getNome().equals("aiuto")) {
			this.aiuto();
		} else if (comandoDaEseguire.getNome().equals("prendi")) {
			this.prendi(comandoDaEseguire.getParametro());
		} else if (comandoDaEseguire.getNome().equals("posa")) {
			this.posa(comandoDaEseguire.getParametro());
		} else
			this.console.mostraMessaggio("Comando sconosciuto");
		if (this.partita.vinta()) {
			this.console.mostraMessaggio("Hai vinto!");
			return true;
		} else
			return false;
	}

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		for (int i = 0; i < elencoComandi.length; i++) {
			this.console.mostraMessaggio(elencoComandi[i] + " ");
		}
		this.console.mostraMessaggio("");
		this.console.mostraMessaggio("STANZA D'INGRESSO:");
		this.console.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		this.console.mostraMessaggio(partita.getPlayer().toStringCfu());
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra e ne stampa il
	 * nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if (direzione == null)
			this.console.mostraMessaggio("Dove vuoi andare?");
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			this.console.mostraMessaggio("Direzione inesistente");
		else {
			this.partita.setStanzaCorrente(prossimaStanza);
			int cfu = this.partita.getPlayer().getCfu();
			this.partita.getPlayer().setCfu(cfu - 1);
		}
		this.console.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		this.console.mostraMessaggio(partita.getPlayer().toStringCfu());
	}

	/**
	 * Comando "Fine".
	 */
	private void fine() {
		this.console.mostraMessaggio("Grazie di aver giocato!"); // si desidera smettere
	}

	/**
	 * Comando "prendi": permette di aggiungere un nuovo Attrezzo alla Borsa del
	 * player, l'attrezzo viene rimosso dalla Stanza
	 * 
	 * @param il nome dell'attrezzo
	 */
	private void prendi(String nomeAttrezzo) {
		if (nomeAttrezzo == null) {
			this.console.mostraMessaggio("Specifica quale attrezzo vuoi raccogliere dalla stanza");
			return;
		}else if(this.partita.getPlayer().getBorsa().getPeso()==10) {
			this.console.mostraMessaggio("La borsa ha raggiunto il suo peso massimo!");
		} else if (!this.partita.getStanzaCorrente().hasAttrezzo(nomeAttrezzo)) {
			this.console.mostraMessaggio("La stanza non contiene l'attrezzo cercato!");
			return;
		}else {
			Attrezzo attrezzoDaPrendere = null;
			attrezzoDaPrendere = this.partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo);
			if(this.partita.getPlayer().getBorsa().getPeso()+attrezzoDaPrendere.getPeso()>10) {
				this.console.mostraMessaggio("Questo oggetto è troppo pesante per la tua borsa!");
			}
			if (this.partita.getPlayer().getBorsa().addAttrezzo(attrezzoDaPrendere)) {
				// l'attrezzo è stato aggiunto correttamente
				this.console.mostraMessaggio("Hai raccolto un nuovo attrezzo!");
				this.console.mostraMessaggio(this.partita.getPlayer().getBorsa().toString());
				this.partita.getStanzaCorrente().removeAttrezzo(attrezzoDaPrendere);
			}
		}
		return;
	}

	/**
	 * Comando "posa": permette di rimuovere un Attrezzo dalla Borsa del player,
	 * l'attrezzo viene aggiunto alla Stanza
	 * 
	 * @param il nome dell'attrezzo
	 */
	private void posa(String nomeAttrezzo) {
		if (nomeAttrezzo == null) {
			this.console.mostraMessaggio("Specifica quale attrezzo vuoi posare dalla borsa");
			return;
		}
		if (!this.partita.getPlayer().getBorsa().hasAttrezzo(nomeAttrezzo)) {
			this.console.mostraMessaggio("La borsa non contiene l'attrezzo cercato!");
			return;
		}
		Attrezzo attrezzoDaPosare = this.partita.getPlayer().getBorsa().getAttrezzo(nomeAttrezzo);
		this.partita.getPlayer().getBorsa().removeAttrezzo(nomeAttrezzo);
		this.partita.getStanzaCorrente().addAttrezzo(attrezzoDaPosare);
		this.console.mostraMessaggio("Hai lasciato un l'attrezzo!");
		this.console.mostraMessaggio(this.partita.getPlayer().getBorsa().toString());
	}

	public static void main(String[] argc) {
		IOConsole console = new IOConsole();
		DiaDia gioco = new DiaDia(console);
		gioco.gioca();
	}

}
