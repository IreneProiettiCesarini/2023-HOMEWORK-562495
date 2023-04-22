package it.uniroma3.diadia.ambienti;

public class StanzaBuia extends Stanza {

	public static final String DESCRIZIONE_STANZA_BUIA = "Qui c'è un buio pesto!";
	private String nomeAttrezzoLuminoso;

	public StanzaBuia(String nome, String nomeAttrezzoLuminoso) {
		super(nome);
		this.nomeAttrezzoLuminoso = nomeAttrezzoLuminoso;
	}

	@Override
	public String getDescrizione() {
		if (!super.hasAttrezzo(nomeAttrezzoLuminoso))
			return DESCRIZIONE_STANZA_BUIA;
		return super.getDescrizione();
	}

}
