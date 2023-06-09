package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagica extends Stanza {

	final static public int SOGLIA_MAGICA_DEFAULT = 3;

	private int contatoreAttrezziPosati;
	private int sogliaMagica;

	public StanzaMagica(String nome) {
		super(nome);
		this.sogliaMagica = SOGLIA_MAGICA_DEFAULT;
	}

	/*
	 * MGC
	 */
	public StanzaMagica(String nome, int sogliaMagica) {
		super(nome);
		this.sogliaMagica = sogliaMagica;
		this.contatoreAttrezziPosati = 0;
	}

	@Override
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.contatoreAttrezziPosati < this.sogliaMagica)
			this.contatoreAttrezziPosati++;
		else
			attrezzo = modificaAttrezzo(attrezzo);
		return super.addAttrezzo(attrezzo);
	}

	public Attrezzo modificaAttrezzo(Attrezzo attrezzo) {
		String nomeInverso = new StringBuilder(attrezzo.getNome()).reverse().toString();
		int pesoDoppio = attrezzo.getPeso() * 2;
		return new Attrezzo(nomeInverso, pesoDoppio);
	}

}
