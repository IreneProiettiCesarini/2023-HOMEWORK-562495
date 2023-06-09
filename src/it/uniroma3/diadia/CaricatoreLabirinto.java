package it.uniroma3.diadia;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaBloccata;
import it.uniroma3.diadia.ambienti.StanzaBuia;
import it.uniroma3.diadia.ambienti.StanzaMagica;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;

public class CaricatoreLabirinto {

	// prefisso della singola riga che conterrà tutte le stanze del labrinto
	private static final String MARKER_STANZE = "Stanze:";

	private static final String MARKER_STANZA_INIZIALE = "Inizio:";

	private static final String MARKER_STANZA_FINALE = "Vincente:";
	
	private static final String MARKER_STANZE_MAGICHE = "Magiche:";

	private static final String MARKER_STANZE_BUIE = "Buie:";

	private static final String MARKER_STANZE_BLOCCATE = "Bloccate:";
	
	private static final String MARKER_PERSONAGGI_CANE = "Cani:";

	private static final String MARKER_PERSONAGGI_MAGO = "Maghi:";

	private static final String MARKER_PERSONAGGI_STREGA = "Streghe:";

	private static final String MARKER_ATTREZZI = "Attrezzi:";

	private static final String MARKER_USCITE = "Uscite:";

	/*
	 * Esempio di un possibile file di specifica di un labirinto (vedi
	 * POO-26-eccezioni-file.pdf)
	 * 
	 * Stanze: biblioteca, N10, N11 Inizio: N10 Vincente: N11 Attrezzi: martello 10
	 * biblioteca, pinza 2 N10 Uscite: biblioteca nord N10, biblioteca sud N11
	 * 
	 */
	private BufferedReader reader;

	private Map<String, Stanza> nome2stanza;
	private Map<String, Attrezzo> nome2attrezzo;
	private Map<String, AbstractPersonaggio> nome2personaggio;

	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;

	public CaricatoreLabirinto(String nomeFile) throws FileNotFoundException {
		this.nome2stanza = new HashMap<String, Stanza>();
		this.nome2attrezzo = new HashMap<String, Attrezzo>();
		this.nome2personaggio = new HashMap<String, AbstractPersonaggio>();
		// file da leggere salvato in un BufferReader
		this.reader = new LineNumberReader(new FileReader(nomeFile));
	}

	public CaricatoreLabirinto(StringReader reader) throws FileNotFoundException {
		this.nome2stanza = new HashMap<String, Stanza>();
		this.nome2attrezzo = new HashMap<String, Attrezzo>();
		this.nome2personaggio = new HashMap<String, AbstractPersonaggio>();
		this.reader = new LineNumberReader(reader);
	}

	public void carica() throws InvalidFileFormatException {
		try {
			this.leggiECreaStanze();
			this.leggiInizialeEvincente();
			this.leggiECreaStanzeMagiche();
			this.leggiECreaStanzeBuie();
			this.leggiECreaStanzeBloccate();
			this.leggiECreaMaghi();
			this.leggiECreaCani();
			this.leggiECreaStreghe();
			this.leggiECollocaAttrezzi();
			this.leggiEImpostaUscite();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

	}

	// forma-> Attrezzi: osso 5 N10
	private void leggiECollocaAttrezzi() throws InvalidFileFormatException {
		String riga = this.leggiRigaConMarkerIniziale(MARKER_ATTREZZI);
		for (String singleton : ottieniSingletonStringDellaRiga(riga)) {
			try (Scanner scan = new Scanner(singleton)) {
				while (scan.hasNext()) { // mancano i check sulla correttezza
					String nomeAtt = scan.next();
					String peso = scan.next();
					String stanzaRif = scan.next();
					if (!this.nome2stanza.containsKey(stanzaRif))
						throw new InvalidFileFormatException("stanza di riferimento non presente");
					Attrezzo att=new Attrezzo(nomeAtt, Integer.parseInt(peso));
					this.nome2attrezzo.put(nomeAtt, att);
					this.nome2stanza.get(stanzaRif).addAttrezzo(att);
				}
			}
		}
	}

	// forma-> Streghe: varana atrio
	private void leggiECreaStreghe() throws InvalidFileFormatException {
		String riga = this.leggiRigaConMarkerIniziale(MARKER_PERSONAGGI_STREGA);
		for (String singleton : ottieniSingletonStringDellaRiga(riga)) {
			try (Scanner scan = new Scanner(singleton)) {
				while (scan.hasNext()) { // mancano i check sulla correttezza
					String nomeStrega = scan.next();
					String stanzaRif = scan.next();
					if (!this.nome2stanza.containsKey(stanzaRif))
						throw new InvalidFileFormatException("stanza di riferimento non presente");
					AbstractPersonaggio s=new Strega(nomeStrega);
					this.nome2personaggio.put(nomeStrega, s);
					this.nome2stanza.get(stanzaRif).setPersonaggio(s);
				}
			}
		}
	}

	// forma-> Cani: pippo caramelle N11
	private void leggiECreaCani() throws InvalidFileFormatException {
		String riga = this.leggiRigaConMarkerIniziale(MARKER_PERSONAGGI_CANE);
		for (String singleton : ottieniSingletonStringDellaRiga(riga)) {
			try (Scanner scan = new Scanner(singleton)) {
				while (scan.hasNext()) { // mancano i check sulla correttezza
					String nomeCane = scan.next();
					String cibo = scan.next();
					String stanzaRif = scan.next();
					if (!this.nome2stanza.containsKey(stanzaRif))
						throw new InvalidFileFormatException("stanza di riferimento non presente");
					AbstractPersonaggio c=new Cane(nomeCane, cibo);
					this.nome2personaggio.put(nomeCane, c);
					this.nome2stanza.get(stanzaRif).setPersonaggio(c);
				}
			}
		}
	}

	// forma -> Maghi: merlino bacchetta 4 N10
	private void leggiECreaMaghi() throws InvalidFileFormatException {
		String riga = this.leggiRigaConMarkerIniziale(MARKER_PERSONAGGI_MAGO);
		for (String singleton : ottieniSingletonStringDellaRiga(riga)) {
			try (Scanner scan = new Scanner(singleton)) {
				while (scan.hasNext()) { // mancano i check sulla correttezza
					String nomeMago = scan.next();
					String nomeAtt = scan.next();
					String peso = scan.next();
					String stanzaRif = scan.next();
					if (!this.nome2stanza.containsKey(stanzaRif))
						throw new InvalidFileFormatException("stanza di riferimento non presente");
					Attrezzo att=new Attrezzo(nomeAtt, Integer.valueOf(peso));
					AbstractPersonaggio m=new Mago(nomeMago, att);
					this.nome2personaggio.put(nomeMago, m);
					this.nome2stanza.get(stanzaRif).setPersonaggio(m);
				}
			}
		}
	}

	// forma-> Uscite: atrio est N10
	private void leggiEImpostaUscite() throws InvalidFileFormatException {
		String riga = this.leggiRigaConMarkerIniziale(MARKER_USCITE);
		for (String singleton : ottieniSingletonStringDellaRiga(riga)) {
			try (Scanner scan = new Scanner(singleton)) {
				while (scan.hasNext()) { // mancano i check sulla correttezza
					String stanzaRif = scan.next();
					String dir = scan.next();
					String stanzaAdj = scan.next();
					setUscita(stanzaRif, dir, stanzaAdj);
				}
			}
		}
	}

	private void setUscita(String stanzaDa, String dir, String stanzaA) throws InvalidFileFormatException {
		if (!this.nome2stanza.containsKey(stanzaDa) || !this.nome2stanza.containsKey(stanzaA))
			throw new InvalidFileFormatException("una delle stanze non è contenuta nel lab");
		this.nome2stanza.get(stanzaDa).impostaStanzaAdiacente(Direzione.valueOf(dir), this.nome2stanza.get(stanzaA));
	}

	// forma -> Inizio: atrio - Vincente: biblioteca
	private void leggiInizialeEvincente() throws InvalidFileFormatException {
		String nomeStanzaIniziale = this.leggiRigaConMarkerIniziale(MARKER_STANZA_INIZIALE);
		if (!this.nome2stanza.containsKey(nomeStanzaIniziale))
			throw new InvalidFileFormatException("S.I. non contenuta");
		String nomeStanzaFinale = this.leggiRigaConMarkerIniziale(MARKER_STANZA_FINALE);
		if (!this.nome2stanza.containsKey(nomeStanzaFinale))
			throw new InvalidFileFormatException("S.F. non contenuta");
		this.stanzaIniziale = this.nome2stanza.get(nomeStanzaIniziale);
		this.stanzaVincente = this.nome2stanza.get(nomeStanzaFinale);
	}

	// forma -> Stanze: N10, atrio,biblioteca ,laboratorio, N11
	private void leggiECreaStanze() throws InvalidFileFormatException {
		// legge la riga del file che inizia per Stanze:
		String riga = this.leggiRigaConMarkerIniziale(MARKER_STANZE);
		for (String nomeStanza : ottieniSingletonStringDellaRiga(riga)) {
			Stanza stanza = new Stanza(nomeStanza);
			this.nome2stanza.put(nomeStanza, stanza);
		}
	}

	// forma-> Bloccate: atrio nord chiave
	private void leggiECreaStanzeBloccate() throws InvalidFileFormatException {
		String riga = this.leggiRigaConMarkerIniziale(MARKER_STANZE_BLOCCATE);
		for (String singleton : ottieniSingletonStringDellaRiga(riga)) {
			try (Scanner scan = new Scanner(singleton)) {
				String nomeStanza = scan.next();
				String dirBloccata = scan.next();
				String attrezzoSblocco = scan.next();
				Stanza stanza = new StanzaBloccata(nomeStanza, attrezzoSblocco, Direzione.valueOf(dirBloccata));
				this.nome2stanza.put(nomeStanza, stanza);
			}
		}
	}

	// forma-> N11 torcia
	private void leggiECreaStanzeBuie() throws InvalidFileFormatException {
		String riga = this.leggiRigaConMarkerIniziale(MARKER_STANZE_BUIE);
		for (String singleton : ottieniSingletonStringDellaRiga(riga)) {
			try (Scanner scan = new Scanner(singleton)) {
				while (scan.hasNext()) {
					String nomeStanza = scan.next();
					String attrezzoLuce = scan.next();
					Stanza stanza = new StanzaBuia(nomeStanza, attrezzoLuce);
					this.nome2stanza.put(nomeStanza, stanza);
				}
			}
		}
	}

	private void leggiECreaStanzeMagiche() throws InvalidFileFormatException {
		String riga = this.leggiRigaConMarkerIniziale(MARKER_STANZE_MAGICHE);
		for (String nomeStanza : ottieniSingletonStringDellaRiga(riga)) {
			Stanza stanza = new StanzaMagica(nomeStanza);
			this.nome2stanza.put(nomeStanza, stanza);
		}
	}

	private List<String> ottieniSingletonStringDellaRiga(String riga) {
		// lista di parole (nomi delle stanze) separate, rilevate sulla riga passata in
		// input
		List<String> out = new LinkedList<String>();
		Scanner scanner = new Scanner(riga);
		scanner.useDelimiter(",");
		try (Scanner scannerDiParole = scanner) {
			while (scannerDiParole.hasNext()) {
				// aggiunge le parole scannerizzate delimitate da ","
				out.add(scannerDiParole.next());
			}
		}
		return out;
	}

	private String leggiRigaConMarkerIniziale(String marker) throws InvalidFileFormatException {
		try {
			// legge un'intera riga del file
			String riga = this.reader.readLine();
			if (!riga.startsWith(marker))
				throw new InvalidFileFormatException("Error file syntax - expected line starts with " + marker);
			// ritorna l'intera stringa della riga privata dei caratteri del MARKER
			return riga.substring(marker.length());
		} catch (IOException e) {
			throw new InvalidFileFormatException(e.getMessage());
		}
	}

	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}

	public Stanza getStanzaVincente() {
		return this.stanzaVincente;
	}

	public Map<String, Stanza> getStanze() {
		return this.nome2stanza;
	}
	
	public Map<String, Attrezzo> getAttrezzi() {
		return this.nome2attrezzo;
	}
	
	public Map<String, AbstractPersonaggio> getPersonaggi() {
		return this.nome2personaggio;
	}
}
