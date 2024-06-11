/**
 * Una classe che serve ad eseguire il comando "aiuto".
 *
 * @author  M.Saravo-L.Mattioli
 * @see Comando
 * @see IOConsole
 *
 */
package comandi;

import java.util.ArrayList;
import java.util.List;

import diadia.IO;
import diadia.Partita;
import diadiaTest.*;

public class ComandoAiuto extends AbstractComando  {
	
	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa", "borsa", "guarda", "saluta", "interagisci", "regala"};


	public ComandoAiuto () {
		super ("aiuto");
		
	}
	/** esecuzione comando 
	 * @param partita corrente
	 * @param console per inserimento/stampa
	 * */
	@Override
	public void esegui(Partita partita, IO console) {
	
				for (int i = 0; i < this.elencoComandi.length; i++)
			console.mostraMessaggio(elencoComandi[i]);		
	}

}
