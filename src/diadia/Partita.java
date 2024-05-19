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
	private boolean iniziata;
	private Labirinto labirinto;
	
	public Partita(){
		this.iniziata = false;
		this.finita = false;
	}
	
	public Partita(Labirinto lab){
		this.iniziata = false;
		this.finita = false;
		this.labirinto = lab;
	}

	//crea il giocatore e il labirinto
    Giocatore giocatore = new Giocatore ();
//	Labirinto labirinto = new Labirinto ();
	
	
	/** ottiene labirinto corrente*/
	public Labirinto getLabirinto () {
		return this.labirinto;
	}
	
	/** imposta labirinto corrente*/
	public void setLabirinto (Labirinto lab) {
		this.labirinto = lab;
	}
	
	/** ottiene giocatore*/
	public Giocatore getGiocatore () {
		return this.giocatore;
	}

	/** imposta la partita come iniziata */
	public void setIniziata () {
		this.iniziata = true;
	}
	
	/** vede se la partita è iniziata
	 * @return true se è iniziata, false altrimenti
	 */
	public boolean getIniziata () {
		return this.iniziata;
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
		return finita || vinta() || (!giocatoreIsVivo());
	}

	/**
	 * Imposta la partita come finita
	 */
	public void setFinita() {
		this.finita = true;
	}
	
	/**
	 * vede se il giocatore è ancora vivo
	 * @return true se è vivo, false altrimenti
	 */
	public boolean giocatoreIsVivo() {
		boolean vivo = true;
		if (this.giocatore.getCfu() == 0)
			vivo = false;
		return vivo;
	}

		
}
