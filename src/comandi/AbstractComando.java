package comandi;

import java.util.ArrayList;
import java.util.List;

import diadia.IO;
import diadia.Partita;

public abstract class AbstractComando implements Comando {
	private String nome;
	
	public AbstractComando (String nome) { this.nome = nome; }
	
	/** esecuzione comando 
	 * @param partita corrente
	 * @param console per inserimento/stampa
	 * */
	public abstract void esegui (Partita partita, IO console);
	
	/** restituisce nome comando 
	 * @return nome comando
	 * */
	@Override
	public String getNome() { return this.nome;	}
	
	/** imposta parametro (null, senza parametro)
	 * @param parametro comando
	 * */
	@Override
	public void setParametro(String parametro) { }

	/** ottenere parametro (null, senza parametro)
	 * @return nome parametro
	 * */
	@Override
	public String getParametro() { return null;	}
	
	
}
