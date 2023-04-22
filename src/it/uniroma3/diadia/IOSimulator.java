package it.uniroma3.diadia;

public class IOSimulator implements IO {

	private String inputUtente[];
	private int indiceProxInput;
	private String outputSistema[];
	private int indiceOutputCorr;

	public IOSimulator(String... inputDaInserire) {
		this.inputUtente = inputDaInserire;
		this.indiceProxInput = 0;
		this.outputSistema = new String[100];
		this.indiceOutputCorr = 0;
	}

	@Override
	public void mostraMessaggio(String messaggio) {
		this.outputSistema[this.indiceOutputCorr++] = messaggio;
	}

	@Override
	public String leggiRiga() {
		if (this.inputUtente.length==0) return null;
		return this.inputUtente[this.indiceProxInput++];
	}

}
