package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagicaProtected extends StanzaProtected {

	final static public int SOGLIA_MAGICA_DEFAULT = 3;

	private int contatoreAttrezziPosati;
	private int sogliaMagica;

	public StanzaMagicaProtected(String nome) {
		this(nome, SOGLIA_MAGICA_DEFAULT);
	}

	/*
	 * MGC
	 */
	public StanzaMagicaProtected(String nome, int sogliaMagica) {
		super(nome);
		this.sogliaMagica = sogliaMagica;
		this.contatoreAttrezziPosati = 0;
	}

	@Override
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.contatoreAttrezziPosati < this.sogliaMagica) {
			this.contatoreAttrezziPosati++;
		} else {
			attrezzo = modificaAttrezzo(attrezzo);
		}
		if (this.numeroAttrezzi < NUMERO_MASSIMO_ATTREZZI) {
			this.attrezzi[numeroAttrezzi] = attrezzo;
			this.numeroAttrezzi++;
			return true;
		} else {
			return false;
		}
	}

	public Attrezzo modificaAttrezzo(Attrezzo attrezzo) {
		String nomeInverso = new StringBuilder(attrezzo.getNome()).reverse().toString();
		return new Attrezzo(nomeInverso, attrezzo.getPeso() * 2);
	}
}
