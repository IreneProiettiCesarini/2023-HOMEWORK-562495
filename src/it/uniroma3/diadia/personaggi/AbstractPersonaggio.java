package it.uniroma3.diadia.personaggi;

import java.util.LinkedList;
import java.util.List;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public abstract class AbstractPersonaggio {

	private String nome;
	private Boolean checkSaluto;
	private List<Attrezzo> attrezzi;

	public AbstractPersonaggio(String nome) {
		this.nome = nome;
		this.checkSaluto = false;
		this.attrezzi = new LinkedList<Attrezzo>();
	}

	public String getNome() {
		return this.nome;
	}

	public Boolean getCheckSaluto() {
		return this.checkSaluto;
	}

	public List<Attrezzo> getAttrezzi() {
		return this.attrezzi;
	}

	public String saluta() {
		StringBuilder answ = new StringBuilder("Ciao bell*, so ");
		if (this.checkSaluto) {
			answ.append("già chi sei rincoglionit*!");
			return answ.toString();
		}
		answ.append(this.getNome() + ".");
		this.checkSaluto = true;
		return answ.toString();
	}

	public void addAttrezzo(Attrezzo attrezzo) {
		if (attrezzo == null || this.attrezzi.contains(attrezzo))
			return;
		this.attrezzi.add(attrezzo);
	}
	
	public boolean removeAttrezzo(String nomeAttrezzo) {
		if(nomeAttrezzo==null)
			return false;
		for(Attrezzo a: this.attrezzi) {
			if(a.getNome().equals(nomeAttrezzo)) {
				this.attrezzi.remove(a);
				return true;
			}
		}
		return false;
	}

	public boolean hasAttrezzo(String nomeAttrezzo) {
		if (this.attrezzi.isEmpty())
			return false;
		for (Attrezzo a : this.attrezzi) {
			if (a.getNome().equals(nomeAttrezzo))
				return true;
		}
		return false;
	}

	abstract public String agisci(Partita partita);

	abstract public String riceviRegalo(Attrezzo attrezzo, Partita partita);

	@Override
	public String toString() {
		return this.getNome();
	}

}
