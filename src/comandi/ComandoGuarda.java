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

public class ComandoGuarda extends AbstractComando {
	
	/** costruttore senza parametro */
	public ComandoGuarda () {
		super ("guarda");
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
	
//	public String getNome() { return this.nome;	}
	
}
