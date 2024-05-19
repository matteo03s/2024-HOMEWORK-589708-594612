/**
 * Una classe che serve ad eseguire il comando "guarda".
 *
 * @author  M.Saravo-L.Mattioli
 * @see Comando
 * @see IOConsole
 *
 */
package comandi;

import diadia.IO;
import diadia.Partita;
import diadiaTest.*;

public class ComandoGuarda implements Comando {

	String nome = "guarda";
	
	/** costruttore senza parametro */
	public ComandoGuarda () {
	}
	
	/** esecuzione comando 
	 * @param partita corrente
	 * @param console per inserimento/stampa
	 * */
	@Override
	public void esegui(Partita partita, IO console) {
		console.mostraMessaggio(partita.getLabirinto().getStanzaCorrente().getDescrizione());
		console.mostraMessaggio(partita.getGiocatore().toString());
		partita.setIniziata();
	}

	/** imposta parametro 
	 * @param parametro comando
	 * */
	@Override
	public void setParametro(String parametro) {
		
		// corpo vuoto, questo comando non ha parametri
	}

	/** restituisce nome comando 
	 * @return nome comando
	 * */
	@Override
	public String getNome() {
		return this.nome;
	}

	/** ottenere parametro 
	 * @return nome parametro
	 * */
	@Override
	public String getParametro() {
		//ritorno nullo, questo comando non ha parametri
		return null;
	}

}
