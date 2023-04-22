package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai implements Comando {

	private String nome = "vai";
	private String direzione;

	/**
	 * Implementazione di esegui per il Comando vai
	 */
	@Override
	public void esegui(Partita partita) {
		IO io = partita.getIO();
		Stanza corrente = partita.getStanzaCorrente();
		if (direzione == null) {
			io.mostraMessaggio("Dove vuoi andare?");
			io.mostraMessaggio(corrente.printDirezioni());
			return;
		}
		Stanza prossimaStanza = corrente.getStanzaAdiacente(direzione);
		if (prossimaStanza == null) {
			io.mostraMessaggio("Direzione inesistente");
			io.mostraMessaggio(corrente.printDirezioni());
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

	@Override
	public String getParametro() {
		return this.direzione;
	}

	@Override
	public void setParametro(String parametro) {
		this.direzione = parametro;
	}
}
