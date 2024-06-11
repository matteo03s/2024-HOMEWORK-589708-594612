/**
 * Una classe che serve ad eseguire il comando "vai".
 *
 * @author  M.Saravo-L.Mattioli
 * @see Comando
 * @see IOConsole
 *
 */
package comandi;

import ambienti.*;
import diadia.IO;
import diadia.Partita;
import diadiaTest.*;

public class ComandoVai extends AbstractComando {

	private Direzione direzione;
	
	/** costruttore con parametro inserito 
	 * @param direzione
	 * */
	public ComandoVai (Direzione direzione) {
		super ("vai");
		this.direzione = direzione;
	}

	/* costruttore senza parametro */
	public ComandoVai () { super ("vai"); }

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
		else if (prossima.getNome().equals(corrente.getNome())) {
			console.mostraMessaggio("\nla direzione " + this.direzione + 
					" è bloccata, servirebbe qualcosa per sbloccarla");
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
		this.direzione = Direzione.valueOf(parametro);
	}


	/** restituisce il nome della direzione passata come parametro
	 * @return nome parametro
	 * */
	@Override
	public String getParametro() {
		return this.direzione.toString();
	}

}
