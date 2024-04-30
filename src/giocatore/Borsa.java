package giocatore;
import attrezz.*;
import ambienti.*;

/**
 * Classe borsa - una borsa in un gioco di ruolo.
 * la borsa contiene gli attrezzi (max 10).
 * la borsa è gestita dal giocatore
 * 
 * @see Attrezzo
 * @see Giocatore
 * @version base
*/

public class Borsa {
	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private Attrezzo[] attrezzi;
	private int numeroAttrezzi;
	private int pesoMax;

	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}

	/** costruttore borsa con peso max
	 * @param peso massimo consentito
	 **/
	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new Attrezzo[10]; // speriamo bastino...
		this.numeroAttrezzi = 0;
	}

	/** funzione che aggiunge un attrezzo alla borsa
	 * @param attrezzo da aggiungere
	 * @return true se si può aggiungere, false altrimenti
	 **/
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (attrezzo == null || this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		if (this.numeroAttrezzi >= 10)
			return false;
		this.attrezzi[this.numeroAttrezzi] = attrezzo;
		this.numeroAttrezzi++;

		return true;
	}

	/** funzione che ritorna il peso massimo consentito
	 * @return peso massimo
	 **/
	public int getPesoMax() {
		return pesoMax;
	}

	/** metodo che ottiene un attrezzo ricevendo solo il nome
	 * @param nome attrezzo
	 * @return attrezzo
	 * */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		for (int i = 0; i < this.numeroAttrezzi; i++)
			if (this.attrezzi[i].getNome().equals(nomeAttrezzo))
				a = attrezzi[i];
		return a;
	}

	/** metodo che ottiene il peso della borsa
	 * @return peso borsa
	 * */
	public int getPeso() {
		int peso = 0;
		for (int i = 0; i < this.numeroAttrezzi; i++)
			peso += this.attrezzi[i].getPeso();
		return peso;
	}

	/** metodo che vede se la borsa è vuota
	 * @return true se la borsa è vuota, false altrimenti
	 * */
	public boolean isEmpty() {
		return this.numeroAttrezzi == 0;
	}

	/** metodo che vede se la borsa ha un attrezzo il cui nome viene passato
	 * @param nome attrezzo
	 * @return true se la borsa ha l'attrezzo, false altrimenti
	 * */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo) != null;
	}

	/** funzione che rimuove un attrezzo dalla borsa
	 * @param nome attrezzo da rimuovere
	 * @return attrezzo ceh si vuole rimuovere
	 **/
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		if (this.hasAttrezzo(nomeAttrezzo) == false)
			return null;
		Attrezzo a = null;
		//ricerca esistenza attrezzo
		boolean esiste = false;
		int posizioneAttrezzo = 0;
		for (int i = 0; i < this.attrezzi.length && !esiste; i++) {
			if (nomeAttrezzo.equalsIgnoreCase(this.attrezzi[i].getNome())) {
				esiste = true;
				posizioneAttrezzo = i;
				a = this.attrezzi[i];
			}
			
		}
		if (esiste) {
			this.numeroAttrezzi--;
			for (int j = posizioneAttrezzo; j<(this.attrezzi.length-1); j++) {
				this.attrezzi [j] = this.attrezzi[j+1];
			}
			this.attrezzi[9] = null;
		}
		return a;
	}

	/** metodo che restituisce la descrizione della borsa
	 * @return descrizione stringa
	 * */
	public String toString() {
		StringBuilder s = new StringBuilder();
		if (!this.isEmpty()) {
			s.append("Contenuto borsa (" + this.getPeso() + "kg/" + this.getPesoMax() + "kg): ");
			for (int i = 0; i < this.numeroAttrezzi; i++)
				s.append(attrezzi[i].toString() + " ");
		} else
			s.append("Borsa vuota");
		return s.toString();
	}
}
