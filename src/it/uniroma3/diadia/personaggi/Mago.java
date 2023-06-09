package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Mago extends AbstractPersonaggio {
	
	private final static String MESSAGGIO_REGALO_ACCETTATO = "Controlla gli attrezzi della stanza per scoprire il mio potere...";
	private final static String MESSAGGIO_REGALO_RIFIUTATO = "Ti conviene smammare al più presto!";
	private final static String MESSAGGIO_DONO = "Mi fai così tanta pena che ho deciso di donarti qualcosa...";
	private final static String MESSAGGIO_SCUSE = "Sei arrivat* tardi, muovi il culo la prossima volta!!! "
			+ "Non ho più nulla per te...";

	public Mago(String nome, Attrezzo attrezzo) {
		super(nome);
		this.addAttrezzo(attrezzo);
	}

	@Override
	public String agisci(Partita partita) {
		if(!this.getAttrezzi().isEmpty()) {
			Attrezzo dono=this.getAttrezzi().get(0);
			partita.getPlayer().getBorsa().addAttrezzo(dono);
			this.removeAttrezzo(dono.getNome());
			return MESSAGGIO_DONO;
		}else{
			return MESSAGGIO_SCUSE;
		}
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		if(attrezzo==null || this.hasAttrezzo(attrezzo.getNome()))
			return MESSAGGIO_REGALO_RIFIUTATO;
		partita.getStanzaCorrente().addAttrezzo(new Attrezzo(attrezzo.getNome(), attrezzo.getPeso()/2));
		return MESSAGGIO_REGALO_ACCETTATO;
	}
	
	@Override
	public int hashCode() {
		return this.getClass().hashCode() + this.getNome().hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || o.getClass() != Mago.class)
			return false;
		final Mago that = (Mago) o;
		return this.getNome().equals(that.getNome());
	}
}
