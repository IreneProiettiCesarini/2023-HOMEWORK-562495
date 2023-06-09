package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoInteragisci extends AbstractComando {

	private String nome = "interagisci";
	
	public String messaggio;

	@Override
	public void esegui(Partita partita) {
		AbstractPersonaggio character = partita.getStanzaCorrente().getPersonaggio();
		if (character != null) {
			this.messaggio = character.agisci(partita);
			System.out.println(this.messaggio);
		} else
			System.out.println("Con chi dovrei interagire?...");
	}
	
	@Override
	public String getNome() {
		return this.nome;
	}

}
