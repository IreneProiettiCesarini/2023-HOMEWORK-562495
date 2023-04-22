package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public class ComandoPosa implements Comando {

	private String nome = "posa";
	private String nomeAttrezzo;

	@Override
	public void esegui(Partita partita) {

		IO io = partita.getIO();
		
		Stanza corrente = partita.getStanzaCorrente();
		Borsa borsa = partita.getPlayer().getBorsa();

		if (nomeAttrezzo == null) {
			io.mostraMessaggio("Specifica quale attrezzo vuoi posare dalla borsa");
			return;
		}
		if (!borsa.hasAttrezzo(nomeAttrezzo)) {
			io.mostraMessaggio("La borsa non contiene l'attrezzo cercato!");
			return;
		}
		if (corrente.getNumeroAttrezzi() == 10) {
			io.mostraMessaggio("La stanza non può contenere altri attrezzi!");
			return;
		}
		Attrezzo attrezzoDaPosare = borsa.getAttrezzo(nomeAttrezzo);
		corrente.addAttrezzo(attrezzoDaPosare);
		borsa.removeAttrezzo(nomeAttrezzo);
		io.mostraMessaggio("Hai posato " + nomeAttrezzo + "!");
		io.mostraMessaggio(borsa.toString());
	}

	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo=parametro;
	}

	@Override
	public String getNome() {
		return this.nome;
	}

	@Override
	public String getParametro() {
		return this.nomeAttrezzo;
	}

}
