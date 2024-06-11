/**
 * Una classe che serve ad eseguire il comando "prendi".
 *
 * @author  M.Saravo-L.Mattioli
 * @see Comando
 * @see IOConsole
 *
 */
package comandi;

import attrezz.Attrezzo;
import diadia.IO;
import diadia.Partita;
import diadiaTest.*;

public class ComandoPrendi extends AbstractComando {

	String nomeOggetto;

	/** costruttore con parametro inserito
	 * @param nome oggetto da prendere
	 *  */
	public ComandoPrendi (String nomeOggetto) {
		super ("prendi");
		this.nomeOggetto = nomeOggetto;
	}

	/** costruttore senza parametro */
	public ComandoPrendi () {
		super ("prendi");
	}

	/** esecuzione comando 
	 * @param partita corrente
	 * @param console per inserimento/stampa
	 * */
	@Override
	public void esegui(Partita partita, IO console) {
		
		if (partita.getIniziata() == false) {
			console.mostraMessaggio("è ancora troppo presto, non puoi prendere nulla");
			return;
		}
		
		if (this.nomeOggetto == null) {
			console.mostraMessaggio("Cosa vuoi prendere? Specifica un attrezzo");
			return;	
		}
		if (partita.getLabirinto().getStanzaCorrente().hasAttrezzo(this.nomeOggetto)) {

			Attrezzo a = partita.getLabirinto().getStanzaCorrente().getAttrezzo(this.nomeOggetto);

			if (partita.getLabirinto().getStanzaCorrente().hasAttrezzo(a.getNome()))
				if (partita.getGiocatore().getBorsa().addAttrezzo(a)) {
					partita.getLabirinto().getStanzaCorrente().removeAttrezzo(a);
					console.mostraMessaggio ("hai preso " + this.nomeOggetto);
					if (partita.getLabirinto().getStanzaCorrente().getTipo().equals("BU1")) {
						console.mostraMessaggio("qui c'è un buio pesto");
					}
					else if (partita.getLabirinto().getStanzaCorrente().getTipo().equals("MA0"))
						console.mostraMessaggio("la stanza  non e' piu magica ");
				}
				else {
					console.mostraMessaggio ("impossibile prendere l'oggetto");
				}
		}		
		else {
			console.mostraMessaggio ("impossibile prendere l'oggetto");
		}

	}

	/** imposta il nome dell'oggetto da prendere passato come parametro
	 * @param parametro comando
	 * */
	@Override
	public void setParametro(String parametro) {
		this.nomeOggetto = parametro;
	}

	/** restituisce il nome dell'oggetto da prendere passato come parametro
	 * @return nome parametro
	 * */
	@Override
	public String getParametro() {
		return this.nomeOggetto;
	}

}
