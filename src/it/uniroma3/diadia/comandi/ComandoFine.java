package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoFine implements Comando {

	public static final String MESSAGGIO_FINE = "Grazie per aver giocato!";
	private String nome = "fine";

	@Override
	public void esegui(Partita partita) {
		partita.getIO().mostraMessaggio("Partita conclusa!");
		partita.getIO().mostraMessaggio(MESSAGGIO_FINE); // si desidera smettere
	}

	@Override
	public void setParametro(String parametro) {
		return;
	}

	@Override
	public String getNome() {
		return this.nome;
	}

	@Override
	public String getParametro() {
		return null;
	}

}
