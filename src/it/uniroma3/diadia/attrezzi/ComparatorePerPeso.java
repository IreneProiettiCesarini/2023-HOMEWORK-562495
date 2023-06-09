package it.uniroma3.diadia.attrezzi;

import java.util.Comparator;

public class ComparatorePerPeso implements Comparator<Attrezzo> {

	@Override
	public int compare(Attrezzo arg0, Attrezzo arg1) {
		if (arg0.getPeso() < arg1.getPeso()) // ordinamento per peso
			return -1;
		if (arg0.getPeso() > arg1.getPeso())
			return 1;
		return (arg0.getNome()).compareTo(arg1.getNome()); // ordinamento alfabetico in caso di equità
	}

}
