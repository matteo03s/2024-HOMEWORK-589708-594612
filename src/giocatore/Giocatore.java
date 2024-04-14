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
	

	public Giocatore () {
		this.cfu = CFU_INIZIALI;
	}
	
	public Borsa getBorsa() {
		return this.borsa;
	}
	
	public void setBorsa (Borsa borsa) {
		this.borsa = borsa;
	}
	
	public int getCfu() {
		return this.cfu;
	}

	public void setCfu(int cfu) {
		this.cfu = cfu;		
	}
}
