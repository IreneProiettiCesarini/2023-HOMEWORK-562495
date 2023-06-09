package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoFine extends AbstractComando {

	public static final String MESSAGGIO_FINE = "Grazie per aver giocato!";
	
	private String nome = "fine";

	@Override
	public void esegui(Partita partita) {
		partita.setFinita();
		partita.getIO().mostraMessaggio("Partita conclusa!");
		partita.getIO().mostraMessaggio(MESSAGGIO_FINE); // si desidera smettere
	}
	
	@Override
	public String getNome() {
		return this.nome;
	}

}
