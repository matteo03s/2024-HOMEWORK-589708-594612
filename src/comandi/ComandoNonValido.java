/**
 * Una classe che serve ad "eseguire" un comando non valido.
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

public class ComandoNonValido extends AbstractComando {

	
public ComandoNonValido () {
	super ("comando non valido");
		
	}
	/** esecuzione comando 
	 * @param partita corrente
	 * @param console per inserimento/stampa
	 * */
	@Override
	public void esegui(Partita partita, IO console) {		
			console.mostraMessaggio("comando non valido, riprova");
			return;	
	}


}
