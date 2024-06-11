package giocatore;

import attrezz.Attrezzo;
import diadia.Partita;

public abstract class AbstractPersonaggio {
	private String nome;
	private String presentazione;
	private boolean haSalutato;

	public AbstractPersonaggio(String nome, String presentazione) {
		this.nome = nome;
		this.presentazione = presentazione;
		this.haSalutato = false;
	}
	
	/** ritorna il nome del personaggio
	 * @return String nome */
	public String getNome() {
		return this.nome;
	}
	
	/** ritorna se il giocatore ha salutato il personaggio
	 * @return boolean haSalutato */
	public boolean haSalutato() {
		return this.haSalutato;
	}
	
	public String saluta() {
		StringBuilder risposta =
				new StringBuilder("Ciao, io sono ");
		risposta.append(this.getNome()+", ");
		if (!haSalutato)
			risposta.append(this.presentazione);
		else
			risposta.append("ci siamo gia' presentati!");
		this.haSalutato = true;
		return risposta.toString();
	}
	
	/** stampa che personaggio si trova nella stanza */
	public String getDescrizione () {
		StringBuilder desc = new StringBuilder();
		if (this.getClass().equals(Mago.class))
			desc.append("qui c'è un mago");
		else if (this.getClass().equals(Cane.class))
			desc.append("qui c'è un cane");
		else if (this.getClass().equals(Strega.class))
			desc.append("qui c'è una strega");
		return desc.toString();
	}
	
	abstract public String riceviRegalo (Attrezzo attrezzo, Partita partita);
	abstract public String agisci(Partita partita);
	
	@Override
	public String toString() {
		return this.getNome();
	}
}