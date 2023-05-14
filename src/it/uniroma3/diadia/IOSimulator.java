package it.uniroma3.diadia;

import java.util.HashMap;
import java.util.Map;

public class IOSimulator implements IO {

	private Map<String, String> corrispondenzeIO;
	private int indiceChiavi;

	public IOSimulator(String... input) {
		this.corrispondenzeIO = new HashMap<String, String>();
		this.indiceChiavi = 0;
		for(String it: input)
			this.corrispondenzeIO.put(it, null); // mappatura con valori null
	}

	@Override
	public void mostraMessaggio(String chiave) {
		this.corrispondenzeIO.get(chiave);
	}

	@Override
	public String leggiRiga() {
		if (this.corrispondenzeIO.isEmpty())
			return null;
		return (String) this.corrispondenzeIO.keySet().toArray()[indiceChiavi++];
	}

}
