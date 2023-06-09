package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoRegala extends AbstractComando {

	private String nome = "regala";

	@Override
	public void esegui(Partita partita) {
		IO io=partita.getIO();
		Attrezzo daRegalare=partita.getPlayer().getBorsa().getAttrezzo(this.getParametro());
		AbstractPersonaggio p =partita.getStanzaCorrente().getPersonaggio();
		
		if(daRegalare==null) {
			io.mostraMessaggio("La tua borsa non contiene questo attrezzo!");
			return;
		}else if(p==null) {
			io.mostraMessaggio("A chi dovrei regalare questo attrezzo??");
			return;
		}
		partita.getPlayer().getBorsa().removeAttrezzo(daRegalare.getNome());
		p.addAttrezzo(daRegalare);
	}
	
	@Override
	public String getNome() {
		return this.nome;
	}
}
