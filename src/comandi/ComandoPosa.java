/**
 * Una classe che serve ad eseguire il comando "posa".
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

public class ComandoPosa extends AbstractComando {

	String nomeOggetto;

	/** costruttore con parametro inserito
	 * @param nome oggetto da posare
	 * */
	public ComandoPosa (String nomeOggetto) {
		super ("posa");
		this.nomeOggetto = nomeOggetto;
	}

	/** costruttore senza parametro */
	public ComandoPosa () { super ("posa");	}

	/** esecuzione comando 
	 * @param partita corrente
	 * @param console per inserimento/stampa
	 * */
	@Override
	public void esegui(Partita partita, IO console) {
		if (partita.getIniziata() == false) {
			console.mostraMessaggio("è ancora troppo presto, non puoi posare nulla");
			return;
		}
		//nome non specificato
		if (this.nomeOggetto == null) {
			console.mostraMessaggio("Cosa vuoi posare? Specifica un attrezzo");
			return;	
		}
		//se la borsa ha l'attrezzo si può procedere
		if (partita.getGiocatore().getBorsa().hasAttrezzo(this.nomeOggetto)) {
			Attrezzo a = partita.getGiocatore().getBorsa().getAttrezzo(this.nomeOggetto);
			//controlla se si può aggiungere l'oggetto alla stanza
			if (partita.getLabirinto().getStanzaCorrente().addAttrezzo(a)) {
				a = partita.getGiocatore().getBorsa().removeAttrezzo(this.nomeOggetto);
				if (partita.getLabirinto().getStanzaCorrente().getTipo().equals("MA1")) {
					console.mostraMessaggio("la stanza è diventata magica!");
					a = partita.getLabirinto().getStanzaCorrente().modificaAttrezzo(a);
					console.mostraMessaggio("l'attrezzo posato si è trasformato in: " + a.getNome() + " (" + a.getPeso() + "kg)");;
				}
				else if (partita.getLabirinto().getStanzaCorrente().getTipo().equals("MA2")) {
					a = partita.getLabirinto().getStanzaCorrente().modificaAttrezzo(a);
					console.mostraMessaggio("l'attrezzo posato si è trasformato in: " + a.getNome() + " (" + a.getPeso() + "kg)");;
				}
				console.mostraMessaggio ("hai posato " + a.getNome() + " nella stanza");
			}
			else
				partita.getGiocatore().getBorsa().addAttrezzo(a);	
		}
		else
			console.mostraMessaggio ("impossibile posare attrezzo nella stanza");

	}

	/** imposta nome oggetto da posare passato come parametro 
	 * @param parametro comando
	 * */
	@Override
	public void setParametro(String parametro) {
		this.nomeOggetto = parametro;
	}

	/** restituisce il nome dell'oggetto da posare passato come parametro
	 * @return nome parametro
	 * */
	@Override
	public String getParametro() {
		return this.nomeOggetto;
	}

}
