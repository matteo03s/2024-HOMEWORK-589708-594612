package comandi;

import diadia.IO;
import diadia.Partita;
import diadiaTest.*;

public class ComandoNonValido implements Comando {

	String nome = "comando non valido";
	
	/** esecuzione comando 
	 * @param partita corrente
	 * @param console per inserimento/stampa
	 * */
	@Override
	public void esegui(Partita partita, IO console) {		
			console.mostraMessaggio("comando non valido, riprova");
			return;	
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
