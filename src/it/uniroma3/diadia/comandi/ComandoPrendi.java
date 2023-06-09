package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public class ComandoPrendi extends AbstractComando {

	public final static int DEFAULT_PESO_MAX_BORSA = 10;

	private String nome = "prendi";

	@Override
	public void esegui(Partita partita) {

		IO io = partita.getIO();

		Stanza corrente = partita.getStanzaCorrente();
		Borsa borsa = partita.getPlayer().getBorsa();
		Attrezzo a = partita.getStanzaCorrente().getAttrezzo(this.getParametro());

		if (a == null) {
			io.mostraMessaggio("Specifica quale attrezzo vuoi raccogliere dalla stanza");
			return;
		} else if (borsa.getPeso() == DEFAULT_PESO_MAX_BORSA) {
			io.mostraMessaggio("La borsa ha raggiunto il suo peso massimo!");
			return;
		} else if (!corrente.hasAttrezzo(a.getNome())) {
			io.mostraMessaggio("La stanza non contiene l'attrezzo cercato!");
			return;
		}
		Attrezzo attrezzoDaPrendere = corrente.getAttrezzo(a.getNome());
		if (borsa.getPeso() + attrezzoDaPrendere.getPeso() > 10) {
			io.mostraMessaggio("Questo oggetto è troppo pesante per la tua borsa!");
			return;
		}
		borsa.addAttrezzo(attrezzoDaPrendere);
		corrente.removeAttrezzo(attrezzoDaPrendere);
		io.mostraMessaggio("Hai preso " + a + "!");
		io.mostraMessaggio(borsa.toString());
	}

	@Override
	public String getNome() {
		return this.nome;
	}
}
