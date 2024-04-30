package comandi;

import ambienti.*;
import diadia.IO;
import diadia.Partita;
import diadiaTest.*;

public class ComandoVai implements Comando {

	private String direzione;
	String nome = "vai";
	
	/** costruttore con parametro inserito 
	 * @param direzione
	 * */
	public ComandoVai (String direzione) {
		this.direzione = direzione;
	}

	/* costruttore senza parametro */
	public ComandoVai () {
	}

	/** esecuzione comando 
	 * @param partita corrente
	 * @param console per inserimento/stampa
	 * */
	@Override
	public void esegui(Partita partita, IO console) {
		Stanza corrente = partita.getLabirinto().getStanzaCorrente();
		Stanza prossima = null;
		if (this.direzione == null) {
			console.mostraMessaggio("Dove vuoi andare? Specifica una direzione");
			return;	
		}
		prossima = corrente.getStanzaAdiacente(this.direzione);
		if (prossima == null) {
			console.mostraMessaggio("Direzione inesistente");
			return;
		}
				
		partita.getLabirinto().setStanzaCorrente(prossima);		
		console.mostraMessaggio(partita.getLabirinto().getStanzaCorrente().getDescrizione());
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
		partita.setIniziata();

	}

	/** imposta la direzione passata come parametro
	 * @param parametro comando
	 * */
	@Override
	public void setParametro(String parametro) {
		this.direzione = parametro;
	}

	/** restituisce nome comando 
	 * @return nome "vai"
	 * */
	@Override
	public String getNome() {
		return this.nome;
	}

	/** restituisce il nome della direzione passata come parametro
	 * @return nome parametro
	 * */
	@Override
	public String getParametro() {
		return this.direzione;
	}

}
