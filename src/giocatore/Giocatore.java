package giocatore;

/*
* Classe giocatore - un giocatore in un gioco di ruolo.
* il giocatore gestisce la borsa.
* il giocatore gestisce i cfu.
* il giocatore è gestito dalla partita
* 
* @see Borsa
* @see Partita;
*
*/

public class Giocatore {

	static final private int CFU_INIZIALI = 20;
	private int cfu;
	Borsa borsa = new Borsa();
	
	/** costruttore */
	public Giocatore () {
		this.cfu = CFU_INIZIALI;
	}
	
	/** metodo che restituisce la borsa del giocatore
	 * @return borsa del giocatore
	 * */
	public Borsa getBorsa() {
		return this.borsa;
	}
	
	/** metodo che imposta la borsa del giocatore
	 * @param borsa da settare
	 * */
	public void setBorsa (Borsa borsa) {
		this.borsa = borsa;
	}
	
	/** metodo che restituisce i cfu del giocatore
	 * @return int cfu
	 * */
	public int getCfu() {
		return this.cfu;
	}

	/** metodo per impostare i cfu del giocatore
	 * @param int cfu da impostare
	 * */
	public void setCfu(int cfu) {
		this.cfu = cfu;		
	}
	
	/** metodo che restituisce la descrizione del giocatore
	 * @return descrizione stringa 
	 * */
	public String toString () {
		return ("il giocatore ha " + this.cfu + "CFU\n" + this.borsa.toString());
	}
}
