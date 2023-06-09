package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends AbstractPersonaggio {

	private final static String MESSAGGIO_MORSO = "Se ce tieni ai tuoi CFU levati dai piedi stupido umano!";
	private final static String MESSAGGIO_REGALO = "Pe sta volta t'è annata bene, mo vedi d'annattene";
	private String ciboPreferito;

	public Cane(String nome) {
		super(nome);
	}
	
	public Cane(String nome, String cibo) {
		super(nome);
		this.ciboPreferito=cibo;
	}

	@Override
	public String agisci(Partita partita) {
		partita.getPlayer().setCfu(partita.getPlayer().getCfu()-1);
		return MESSAGGIO_MORSO;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		if(attrezzo==null || !this.ciboPreferito.equals(attrezzo.getNome()) || this.ciboPreferito==null) {
			partita.getPlayer().setCfu(partita.getPlayer().getCfu()-1);
			return MESSAGGIO_MORSO;
		}
		if(this.getAttrezzi().isEmpty()) {
			return "Nun c'ho piu niente pe te, ma grazie der pensiero";
		}
		Attrezzo regaloDiCortesia=this.getAttrezzi().get(0);
		this.removeAttrezzo(regaloDiCortesia.getNome());
		partita.getStanzaCorrente().addAttrezzo(regaloDiCortesia);
		return MESSAGGIO_REGALO;
	}
	
	@Override
	public int hashCode() {
		return this.getClass().hashCode() + this.getNome().hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || o.getClass() != Cane.class)
			return false;
		final Cane that = (Cane) o;
		return this.getNome().equals(that.getNome());
	}
}
