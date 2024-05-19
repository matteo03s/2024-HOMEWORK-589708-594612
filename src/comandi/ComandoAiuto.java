/**
 * Una classe che serve ad eseguire il comando "aiuto".
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

public class ComandoAiuto implements Comando {
	
	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa", "borsa", "guarda"};
	String nome = "aiuto";


	/** esecuzione comando 
	 * @param partita corrente
	 * @param console per inserimento/stampa
	 * */
	@Override
	public void esegui(Partita partita, IO console) {
		for (int i = 0; i < this.elencoComandi.length; i++)
			console.mostraMessaggio(elencoComandi[i]);		
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
