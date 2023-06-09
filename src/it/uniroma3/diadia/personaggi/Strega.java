package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends AbstractPersonaggio {

	private final static String MESSAGGIO_SALUTO_POSITIVO = "Sei stato così gentile a salutarmi che ho deciso di farti una sorpresa...";
	private final static String MESSAGGIO_SALUTO_NEGATIVO = "Avresti dovuto salutarmi prima di chiedere qualcosa"
			+ "vattene dalla mia vista!!";
	private final static String MESSAGGIO_REGALO_ACCETTATO = "AHAHAHAHAHAHAHAHAHAHAHAH";
	private final static String MESSAGGIO_REGALO_RIFIUTATO = "Ti conviene smammare al più presto!";

	public Strega(String nome) {
		super(nome);
	}

	@Override
	public String agisci(Partita partita) {
		if (!this.getCheckSaluto()) { // strega non salutata
			partita.setStanzaCorrente(partita.getStanzaCorrente().getStanzaAdiacenteMinimoAttrezzi());
			return MESSAGGIO_SALUTO_NEGATIVO;
		} else { // strega salutata
			partita.setStanzaCorrente(partita.getStanzaCorrente().getStanzaAdiacenteMassimoAttrezzi());
			return MESSAGGIO_SALUTO_POSITIVO;
		}
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		if (attrezzo == null || this.hasAttrezzo(attrezzo.getNome()))
			return MESSAGGIO_REGALO_RIFIUTATO;
		this.addAttrezzo(attrezzo);
		return MESSAGGIO_REGALO_ACCETTATO;
	}
	
	@Override
	public int hashCode() {
		return this.getClass().hashCode() + this.getNome().hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || o.getClass() != Strega.class)
			return false;
		final Strega that = (Strega) o;
		return this.getNome().equals(that.getNome());
	}

}
