package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public class ComandoPosa extends AbstractComando {

	private String nome = "posa";

	@Override
	public void esegui(Partita partita) {

		IO io = partita.getIO();
		
		Stanza corrente = partita.getStanzaCorrente();
		Borsa borsa = partita.getPlayer().getBorsa();
		Attrezzo a=partita.getPlayer().getBorsa().getAttrezzo(this.getParametro());

		if (a == null) {
			io.mostraMessaggio("Specifica quale attrezzo vuoi posare dalla borsa");
			return;
		}
		if (!borsa.hasAttrezzo(a.getNome())) {
			io.mostraMessaggio("La borsa non contiene l'attrezzo cercato!");
			return;
		}
		if (corrente.getNumeroAttrezzi() == 10) {
			io.mostraMessaggio("La stanza non può contenere altri attrezzi!");
			return;
		}
		Attrezzo attrezzoDaPosare = borsa.getAttrezzo(a.getNome());
		corrente.addAttrezzo(attrezzoDaPosare);
		borsa.removeAttrezzo(a.getNome());
		io.mostraMessaggio("Hai posato " + a + "!");
		io.mostraMessaggio(borsa.toString());
	}

	@Override
	public String getNome() {
		return this.nome;
	}

}
