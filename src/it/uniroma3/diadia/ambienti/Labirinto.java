package it.uniroma3.diadia.ambienti;

import java.io.FileNotFoundException;
import java.io.StringReader;
import java.util.Map;

import it.uniroma3.diadia.CaricatoreLabirinto;
import it.uniroma3.diadia.InvalidFileFormatException;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;

public class Labirinto {

	// CLASSE STATICA NIDIFICATA LABIRINTO BUILDER
	public static class LabirintoBuilder {

		private Labirinto labirinto;
		private String ultimoAccesso;

		public LabirintoBuilder(String labirinto) throws FileNotFoundException, InvalidFileFormatException {
			this.labirinto = new Labirinto(labirinto);
		}

		public LabirintoBuilder(StringReader labirinto) throws FileNotFoundException, InvalidFileFormatException {
			this.labirinto = new Labirinto(labirinto);
		}

//		/* costruttore che inizializza immediatamente il labirinto */
//		public LabirintoBuilder(int init) {
//			super();
//			init();
//		}

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
			if (labirinto.getStanzaIniziale() != null)
				return this;
			Stanza iniziale = this.labirinto.getStanze().get(nome);
			if (iniziale == null) {
				iniziale = new Stanza(nome);
				this.labirinto.getStanze().put(nome, iniziale);
			}
			labirinto.setStanzaIniziale(iniziale);
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
			if (labirinto.getStanzaVincente() != null)
				return this;
			Stanza vincente = this.labirinto.getStanze().get(nome);
			if (vincente == null) {
				vincente = new Stanza(nome);
				this.labirinto.getStanze().put(nome, vincente);
			}
			labirinto.setStanzaVincente(vincente);
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
			Stanza s = this.labirinto.getStanze().get(nome);
			if (s != null) {
				s = new Stanza(nome);
				this.labirinto.getStanze().put(nome, s);
				this.ultimoAccesso = nome;
			}
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
			if (this.ultimoAccesso == null) // riferimento alla stanza da aggiungere non presente
				return null;
			Attrezzo att = this.labirinto.getAttrezzi().get(nome);
			if (att == null) {
				att = new Attrezzo(nome, peso);
				this.labirinto.getAttrezzi().put(nome, att);
				this.labirinto.getStanze().get(this.ultimoAccesso).addAttrezzo(att);
			}
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
			Stanza base = this.labirinto.getStanze().get(nome);
			Stanza adj = this.labirinto.getStanze().get(adiacente);
			if (base != null && adj != null) {
				base.impostaStanzaAdiacente(Direzione.valueOf(direzione), adj);
			}
			return this;
		}

		/**
		 * Metodo per l'aggiunta di una stanza con proprietà magiche al labirinto
		 * 
		 * @param String, nome della stanza che si vuole aggiungere
		 * @return this , LubirintoBuilder
		 */
		public LabirintoBuilder addStanzaMagica(String nome, int sogliaMagica) {
			Stanza magica = this.labirinto.getStanze().get(nome);
			if (magica == null) {
				magica = new StanzaMagica(nome, sogliaMagica);
				this.labirinto.getStanze().put(nome, magica);
				this.ultimoAccesso = nome;
			}
			return this;
		}

		/**
		 * Metodo per l'aggiunta di una stanza buia al labirinto
		 * 
		 * @param String, nome della stanza che si vuole aggiungere
		 * @return this , LubirintoBuilder
		 */
		public LabirintoBuilder addStanzaBuia(String nome, String nomeAttrezzoLuminoso) {
			Stanza buia = this.labirinto.getStanze().get(nome);
			if (buia == null) {
				buia = new StanzaBuia(nome, nomeAttrezzoLuminoso);
				this.labirinto.getStanze().put(nome, buia);
				this.ultimoAccesso = nome;
			}
			return this;
		}

		/**
		 * Metodo per l'aggiunta di una stanza con direzione bloccata al labirinto
		 * 
		 * @param String, nome della stanza che si vuole aggiungere
		 * @return this , LubirintoBuilder
		 */
		public LabirintoBuilder addStanzaBloccata(String nome, String nomeAttrezzoChiave, String direzioneBloccata) {
			Stanza bloccata = this.labirinto.getStanze().get(nome);
			if (bloccata == null) {
				bloccata = new StanzaBloccata(nome, nomeAttrezzoChiave, Direzione.valueOf(direzioneBloccata));
				this.labirinto.getStanze().put(nome, bloccata);
				this.ultimoAccesso = nome;
			}
			return this;
		}

		/**
		 * Metodo per l'aggiunta di un personaggio Cane nell'ultima stanza aggiunta
		 * 
		 * @param String, nome del personaggio che si vuole aggiungere
		 * @return this , LubirintoBuilder
		 */
		public LabirintoBuilder addPersonaggioCane(String nome, String cibo) {
			AbstractPersonaggio cane = this.labirinto.getPersonaggi().get(nome);
			if (cane == null) {
				cane = new Cane(nome, cibo);
				this.labirinto.getPersonaggi().put(nome, cane);
				this.labirinto.getStanze().get(this.ultimoAccesso).setPersonaggio(cane);
			}
			return this;
		}

		/**
		 * Metodo per l'aggiunta di un personaggio Cane nell'ultima stanza aggiunta
		 * 
		 * @param String, nome del personaggio che si vuole aggiungere
		 * @return this , LubirintoBuilder
		 */
		public LabirintoBuilder addPersonaggioStrega(String nome) {
			AbstractPersonaggio strega = this.labirinto.getPersonaggi().get(nome);
			if (strega == null) {
				strega = new Strega(nome);
				this.labirinto.getPersonaggi().put(nome, strega);
				this.labirinto.getStanze().get(this.ultimoAccesso).setPersonaggio(strega);
			}
			return this;
		}

		/**
		 * Metodo per l'aggiunta di un personaggio Cane nell'ultima stanza aggiunta
		 * 
		 * @param String, nome del personaggio che si vuole aggiungere
		 * @return this , LubirintoBuilder
		 */
		public LabirintoBuilder addPersonaggioMago(String nome, String nomeAttrezzo, int pesoAttrezzo) {
			AbstractPersonaggio mago = this.labirinto.getPersonaggi().get(nome);
			Attrezzo att = this.labirinto.getAttrezzi().get(nomeAttrezzo);
			if (mago == null && att == null) {
				att = new Attrezzo(nomeAttrezzo, pesoAttrezzo);
				mago = new Mago(nome, att);
				this.labirinto.getPersonaggi().put(nome, mago);
				this.labirinto.getStanze().get(this.ultimoAccesso).setPersonaggio(mago);
			}
			return this;
		}
	}

	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;
	private Map<String, Stanza> nome2stanza;
	private Map<String, Attrezzo> nome2attrezzo;
	private Map<String, AbstractPersonaggio> nome2personaggio;

	private Labirinto(String nomeFile) throws FileNotFoundException, InvalidFileFormatException {
		CaricatoreLabirinto caricaLab = new CaricatoreLabirinto(nomeFile);
		caricaLab.carica();
		this.stanzaIniziale = caricaLab.getStanzaIniziale();
		this.stanzaVincente = caricaLab.getStanzaVincente();
		this.nome2stanza=caricaLab.getStanze();
	}

	private Labirinto(StringReader nomeFile) throws FileNotFoundException, InvalidFileFormatException {
		CaricatoreLabirinto caricaLab = new CaricatoreLabirinto(nomeFile);
		caricaLab.carica();
		this.stanzaIniziale = caricaLab.getStanzaIniziale();
		this.stanzaVincente = caricaLab.getStanzaVincente();
		this.nome2stanza=caricaLab.getStanze();
		this.nome2attrezzo=caricaLab.getAttrezzi();
		this.nome2personaggio=caricaLab.getPersonaggi();
	}

	public static LabirintoBuilder newBuilder(String labirinto)
			throws FileNotFoundException, InvalidFileFormatException {
		return new LabirintoBuilder(labirinto);
	}

	public static LabirintoBuilder newBuilder(StringReader labirinto)
			throws FileNotFoundException, InvalidFileFormatException {
		return new LabirintoBuilder(labirinto);
	}

	public Stanza getStanzaIniziale() {
		return stanzaIniziale;
	}

	public void setStanzaIniziale(Stanza stanzaIniziale) {
		this.stanzaIniziale = stanzaIniziale;
	}

	public Stanza getStanzaVincente() {
		return stanzaVincente;
	}

	public void setStanzaVincente(Stanza stanza) {
		this.stanzaVincente = stanza;
	}

	public Map<String, Stanza> getStanze() {
		return this.nome2stanza;
	}

	public Map<String, Attrezzo> getAttrezzi() {
		return nome2attrezzo;
	}

	public Map<String, AbstractPersonaggio> getPersonaggi() {
		return nome2personaggio;
	}

}
