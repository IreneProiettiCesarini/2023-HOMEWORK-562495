package it.uniroma3.diadia.ambienti;

import java.util.HashMap;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class LabirintoBuilder extends Labirinto {

	private Labirinto labirinto;
	private String ultimoAccesso;

	public LabirintoBuilder() {
		this.labirinto = new Labirinto();
		labirinto.setStanze(new HashMap<String, Stanza>());
		labirinto.setAttrezzi(new HashMap<String, Attrezzo>());
	}

//	/* costruttore che inizializza immediatamente il labirinto */
//	public LabirintoBuilder(int init) {
//		super();
//		init();
//	}

	public String getUltimoAccesso() {
		return this.ultimoAccesso;
	}

	public Labirinto getLabirinto() {
		return this.labirinto;
	}

	/**
	 * Metodo per l'impostazione della stanza Iniziale, questo implica anche
	 * l'aggiunta della stanza all'insieme delle stanze del labrinito
	 * 
	 * @param String, nome della stanza che si vuole creare ed aggiungere
	 * @return this , LubirintoBuilder
	 */
	public LabirintoBuilder addStanzaIniziale(String nome) {
		if (nome == null)
			return null;
		if (labirinto.getStanzaIniziale() != null) // stanza iniziale già impostata
			return this;
		if (labirinto.getStanze().containsKey(nome)) { // impostazione come iniziale
			labirinto.setStanzaIniziale(labirinto.getStanze().get(nome));
			this.ultimoAccesso = nome;
			return this;
		} // creazione nuova stanza e impostazione come inziale
		Stanza s = new Stanza(nome);
		labirinto.setStanzaIniziale(s);
		labirinto.getStanze().put(nome, s);
		this.ultimoAccesso = nome;
		return this;
	}

	/**
	 * Metodo per l'impostazione della stanza Vincente, richiede che la stanza sia
	 * già stata aggiunta alla mappa delle stanze del labirinto
	 * 
	 * @param String, nome della stanza che si vuole aggiungere
	 * @return this , LubirintoBuilder
	 */
	public LabirintoBuilder addStanzaVincente(String nome) {
		if (nome == null)
			return null;
		if (labirinto.getStanzaVincente() != null) // stanza vincente già impostata
			return this;
		if (labirinto.getStanze().containsKey(nome)) { // impostazione come vincente
			labirinto.setStanzaVincente(labirinto.getStanze().get(nome));
			this.ultimoAccesso = nome;
			return this;
		} // creazione stanza e impostazione come vincente
		Stanza s = new Stanza(nome);
		labirinto.setStanzaVincente(s);
		labirinto.getStanze().put(nome, s);
		this.ultimoAccesso = nome;
		return this;
	}

	/**
	 * Metodo per l'aggiunta di una stanza al labirinto
	 * 
	 * @param String, nome della stanza che si vuole aggiungere
	 * @return this , LubirintoBuilder
	 */
	public LabirintoBuilder addStanza(String nome) {
		if (nome == null)
			return null;
		if (labirinto.getStanze().containsKey(nome)) { // stanza già presente
			return this;
		} // creazione e aggiunta di una nuova stanza
		Stanza s = new Stanza(nome);
		labirinto.getStanze().put(nome, s);
		this.ultimoAccesso = nome;
		return this;
	}

	/**
	 * Metodo che aggiunge l'attrezzo, il cui peso viene fornito, nell'ultima stanza
	 * che è stata manipolata
	 * 
	 * @param String, nome dell'attrezzo int, peso dell'attrezzo
	 * @return this , LubirintoBuilder
	 */
	public LabirintoBuilder addAttrezzo(String nome, int peso) {
		if (nome == null || peso == 0)
			return null;
		if (this.ultimoAccesso == null) // riferimento alla stanza da aggiungere non presente
			return null;
		if (labirinto.getAttrezzi().containsKey(nome)) // attrezzo già presente nel labirinto
			return this;
		// creazione e aggiunta a stanza e labirinto del nuovo attrezzo
		Attrezzo a = new Attrezzo(nome, peso);
		labirinto.getAttrezzi().put(nome, a);
		labirinto.getStanze().get(ultimoAccesso).addAttrezzo(a);
		return this;
	}

	/**
	 * Metodo che aggiunge alla prima stanza referenziata l'adiacenza in direzione
	 * fornita con la seconda stanza referenziata
	 * 
	 * @param String, nome stanza di partenza, String, nome stanza adiacente,
	 *                String, direzione
	 * @return this , LubirintoBuilder
	 */
	public LabirintoBuilder addAdiacenza(String nome, String adiacente, String direzione) {
		if (nome == null || adiacente == null || direzione == null)
			return this;
		if (!labirinto.getStanze().containsKey(nome) || !labirinto.getStanze().containsKey(adiacente))
			return this;
		Stanza base = labirinto.getStanze().get(nome);
		Stanza adj = labirinto.getStanze().get(adiacente);
		base.impostaStanzaAdiacente(direzione, adj);
		return this;
	}

	/**
	 * Metodo per l'aggiunta di una stanza con proprietà magiche al labirinto
	 * 
	 * @param String, nome della stanza che si vuole aggiungere
	 * @return this , LubirintoBuilder
	 */
	public LabirintoBuilder addStanzaMagica(String nome, int sogliaMagica) {
		if (nome == null || sogliaMagica < 0)
			return null;
		if (labirinto.getStanze().containsKey(nome)) { // stanza già presente
			return this;
		} // creazione e aggiunta di una nuova stanza
		StanzaMagica s = new StanzaMagica(nome, sogliaMagica);
		labirinto.getStanze().put(nome, s);
		this.ultimoAccesso = nome;
		return this;
	}

	/**
	 * Metodo per l'aggiunta di una stanza buia al labirinto
	 * 
	 * @param String, nome della stanza che si vuole aggiungere
	 * @return this , LubirintoBuilder
	 */
	public LabirintoBuilder addStanzaBuia(String nome, String nomeAttrezzoLuminoso) {
		if (nome == null || nomeAttrezzoLuminoso == null)
			return null;
		if (labirinto.getStanze().containsKey(nome)) { // stanza già presente
			return this;
		} // creazione e aggiunta di una nuova stanza
		StanzaBuia s = new StanzaBuia(nome, nomeAttrezzoLuminoso);
		labirinto.getStanze().put(nome, s);
		this.ultimoAccesso = nome;
		return this;
	}

	/**
	 * Metodo per l'aggiunta di una stanza con direzione bloccata al labirinto
	 * 
	 * @param String, nome della stanza che si vuole aggiungere
	 * @return this , LubirintoBuilder
	 */
	public LabirintoBuilder addStanzaBloccata(String nome, String nomeAttrezzoChiave, String direzioneBloccata) {
		if (nome == null || nomeAttrezzoChiave == null || direzioneBloccata == null)
			return null;
		if (labirinto.getStanze().containsKey(nome)) { // stanza già presente
			return this;
		} // creazione e aggiunta di una nuova stanza
		StanzaBloccata s = new StanzaBloccata(nome, nomeAttrezzoChiave, direzioneBloccata);
		labirinto.getStanze().put(nome, s);
		this.ultimoAccesso = nome;
		return this;
	}
}
