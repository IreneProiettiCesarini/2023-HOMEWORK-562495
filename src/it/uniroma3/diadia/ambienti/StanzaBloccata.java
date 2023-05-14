package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccata extends Stanza {

	private String nomeAttrezzoChiave;
	private String direzioneBloccata;

	public StanzaBloccata(String nome, String nomeAttrezzoChiave, String direzioneBloccata) {
		super(nome);
		this.nomeAttrezzoChiave = nomeAttrezzoChiave;
		this.direzioneBloccata = direzioneBloccata;
	}

	@Override
	public Stanza getStanzaAdiacente(String direzione) {
		if (direzione.equals(direzioneBloccata) && !super.hasAttrezzo(nomeAttrezzoChiave))
			return this;
		return super.getStanzaAdiacente(direzione);
	}

	@Override
	public String getDescrizione() {
		return this.toString();
	}

	@Override
	public String toString() {
		StringBuilder risultato = new StringBuilder();
		risultato.append(this.getNome());
		risultato.append("\nUscite: ");
		for (String k : this.getStanzeAdiacenti().keySet()) {
			risultato.append(k + "; ");
		}
		risultato.append("\nUscitaBloccata: ");
		risultato.append(direzioneBloccata + "; ");
		risultato.append("\nPer sloccare " + this.direzioneBloccata + " posa nella stanza: " + this.nomeAttrezzoChiave);
		risultato.append("\nAttrezzi nella stanza: ");
		for (Attrezzo j : this.getAttrezzi()) {
			risultato.append(j.toString() + " ");
		}
		return risultato.toString();
	}
}
