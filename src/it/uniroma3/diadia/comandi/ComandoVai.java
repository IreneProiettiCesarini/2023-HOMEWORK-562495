package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai extends AbstractComando {

	private String nome = "vai";

	/**
	 * Implementazione di esegui per il Comando vai
	 */
	@Override
	public void esegui(Partita partita) {

		IO io = partita.getIO();
		Stanza corrente = partita.getStanzaCorrente();

		if (this.getParametro() == null) {
			io.mostraMessaggio("Dove vuoi andare?");
			io.mostraMessaggio(corrente.printDirezioni());
			return;
		}
		Stanza prossimaStanza = corrente.getStanzaAdiacente(Direzione.valueOf(this.getParametro()));
		if (prossimaStanza == null) {
			io.mostraMessaggio("Direzione non disponibile!");
			io.mostraMessaggio("Disponibili: " + corrente.printDirezioni());
		} else {
			partita.setStanzaCorrente(prossimaStanza);
			int cfuPrec = partita.getPlayer().getCfu();
			partita.getPlayer().setCfu(cfuPrec - 1);
		}
		io.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		io.mostraMessaggio(partita.getPlayer().toStringCfu());
	}

	@Override
	public String getNome() {
		return this.nome;
	}
}
