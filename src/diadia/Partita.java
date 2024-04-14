package diadia;
import ambienti.*;
import attrezz.*;
import giocatore.*;

/**
 * Questa classe modella una partita del gioco
 *
 * @author  docente di POO
 * @see Stanza
 * @version base
 */

public class Partita {

	private boolean finita;
	
	public Partita(){
		this.finita = false;
		
	}

	//crea il giocatore e il labirinto
    Giocatore giocatore = new Giocatore ();
	Labirinto labirinto = new Labirinto ();
	
	
	/* ottiene labirinto corrente*/
	public Labirinto getLabirinto () {
		return this.labirinto;
	}
	
	/* ottiene giocatore*/
	public Giocatore getGiocatore () {
		return this.giocatore;
	}
	
	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * @return vero se partita vinta
	 */
	public boolean vinta() {
		return labirinto.getStanzaCorrente() == labirinto.getStanzaVincente();
	}

	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		return finita || vinta() || (giocatore.getCfu() == 0);
	}

	/**
	 * Imposta la partita come finita
	 *
	 */
	public void setFinita() {
		this.finita = true;
	}

		
}
