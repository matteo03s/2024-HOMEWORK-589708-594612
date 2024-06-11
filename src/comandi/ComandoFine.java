/**
 * Una classe che serve ad eseguire il comando "fine".
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

public class ComandoFine extends AbstractComando {

	
public ComandoFine () {
	super ("fine");
		
	}
	/** esecuzione comando 
	 * @param partita corrente
	 * @param console per inserimento/stampa
	 * */
	@Override
	public void esegui(Partita partita, IO console) {
		console.mostraMessaggio("Grazie di aver giocato!");
		partita.setFinita();
	}
	


}
