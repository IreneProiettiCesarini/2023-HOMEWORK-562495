package it.uniroma3.diadia.ambienti;

public class StanzaBuia extends Stanza {

	public static final String MESSAGGIO_STANZA_BUIA = "Qui c'è un buio pesto!";
	private static final String NOME_ATTREZZO_LUMINOSO_DEFAULT = "lanterna";
	private String nomeAttrezzoLuminoso;

	public StanzaBuia(String nome) {
		super(nome);
		this.nomeAttrezzoLuminoso = NOME_ATTREZZO_LUMINOSO_DEFAULT;
	}

	public StanzaBuia(String nome, String nomeAttrezzoLuminoso) {
		super(nome);
		this.nomeAttrezzoLuminoso = nomeAttrezzoLuminoso;
	}

	public String getNomeAttrezzoLuce() {
		return this.nomeAttrezzoLuminoso;
	}

	@Override
	public String getDescrizione() {
		if (super.hasAttrezzo(nomeAttrezzoLuminoso))
			return super.getDescrizione();
		else
			return MESSAGGIO_STANZA_BUIA;
	}

}
