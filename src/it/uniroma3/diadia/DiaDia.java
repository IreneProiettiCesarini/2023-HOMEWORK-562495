package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandi;
import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;

public class DiaDia {

	public static final String MESSAGGIO_BENVENUTO = ""
			+ "Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n"
			+ "Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"
			+ "I locali sono popolati da strani personaggi, " + "alcuni amici, altri... chissa!\n"
			+ "Ci sono attrezzi che potrebbero servirti nell'impresa:\n"
			+ "puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n"
			+ "o regalarli se pensi che possano ingraziarti qualcuno.\n\n"
			+ "Per conoscere le istruzioni usa il comando 'aiuto'.";

	private Partita partita;
	private IO io;

	public DiaDia(IO io) {
		this.io = io;
		this.partita = new Partita(io, new LabirintoBuilder());
	}

	public DiaDia(IO io, Labirinto labirinto) {
		this.io = io;
		this.partita = new Partita(io, labirinto);
	}

	public void gioca() {
		String istruzione;

		this.io.mostraMessaggio(MESSAGGIO_BENVENUTO);
		do
			istruzione = this.io.leggiRiga();
		while (!processaIstruzione(istruzione));
	}

	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire;
		FabbricaDiComandi factory = new FabbricaDiComandiFisarmonica();
		comandoDaEseguire = factory.costruisciComando(istruzione, this.io);
		comandoDaEseguire.esegui(this.partita);

		if (this.partita.vinta()) {
			io.mostraMessaggio("Hai vinto!");
		}
		if (this.partita.getPlayer().getCfu() == 0) {
			io.mostraMessaggio("CFU terminati!");
		}

		return this.partita.isFinita();

	}

	public static void main(String[] argc) {
		IO io = new IOConsole();
		Labirinto labInit = new LabirintoBuilder().addStanzaIniziale("Atrio").addAttrezzo("osso", 2)
				.addStanza("AulaN11").addAttrezzo("spada", 5).addStanza("AulaN10").addAttrezzo("dolcetto", 1)
				.addStanza("Laboratorio Campus").addAttrezzo("stecchino", 1).addStanza("Biblioteca")
				.addStanzaVincente("Biblioteca").addAdiacenza("Atrio", "Biblioteca", "nord")
				.addAdiacenza("Atrio", "AulaN10", "sud").addAdiacenza("Atrio", "AulaN11", "est")
				.addAdiacenza("Atrio", "Laboratorio Campus", "ovest")
				.addAdiacenza("AulaN11", "Laboratorio Campus", "est").addAdiacenza("AulaN11", "Atrio", "ovest")
				.addAdiacenza("AulaN10", "Atrio", "nord").addAdiacenza("AulaN10", "AulaN11", "est")
				.addAdiacenza("AulaN10", "Laboratorio Campus", "ovest")
				.addAdiacenza("Laboratorio Campus", "Atrio", "est")
				.addAdiacenza("Laboratorio Campus", "AulaN11", "ovest").addAdiacenza("Biblioteca", "Atrio", "Sud")
				.getLabirinto();
		DiaDia gioco = new DiaDia(io, labInit);
		gioco.gioca();
	}

}
