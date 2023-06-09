package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public abstract class AbstractComando implements Comando {

	private String nome = "AbstractComando";
	private String parametro;

	abstract public void esegui(Partita partita);

	@Override
	public void setParametro(String parametro) {
		this.parametro=parametro;
	}

	@Override
	public String getParametro() {
		return this.parametro;
	}
	
	//per il testing
	@Override
	public String getNome() {
		return this.nome;
	}

}
