package comandi;
import diadia.IO;
import diadia.Partita;
import diadiaTest.*;

public interface Comando {

	
	/** esecuzione comando 
	 * @param partita corrente
	 * @param console per inserimento/stampa
	 * */
	public void esegui (Partita partita, IO console);
	
	/** imposta parametro 
	 * @param parametro comando
	 * */
	public void setParametro (String parametro);
	
	/** restituisce nome comando 
	 * @return nome comando
	 * */
	public String getNome ();
	
	/** ottenere parametro 
	 * @return nome parametro
	 * */
	public String getParametro ();
}