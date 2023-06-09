package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoSaluta extends AbstractComando {

	private String nome = "saluta";

	@Override
	public void esegui(Partita partita) {
		if (partita.getStanzaCorrente().getPersonaggio() != null) {
			partita.getIO().mostraMessaggio(partita.getStanzaCorrente().getPersonaggio().saluta());
		} else {
			partita.getIO().mostraMessaggio("Non c'è nessuno da salutare qui...");
		}
	}

	@Override
	public String getNome() {
		return this.nome;
	}

}
