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

	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new Attrezzo[10]; // speriamo bastino...
		this.numeroAttrezzi = 0;
	}

	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (attrezzo == null || this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		if (this.numeroAttrezzi >= 10)
			return false;
		this.attrezzi[this.numeroAttrezzi] = attrezzo;
		this.numeroAttrezzi++;

		return true;
	}

	public int getPesoMax() {
		return pesoMax;
	}

	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		for (int i = 0; i < this.numeroAttrezzi; i++)
			if (this.attrezzi[i].getNome().equals(nomeAttrezzo))
				a = attrezzi[i];
		return a;
	}

	public int getPeso() {
		int peso = 0;
		for (int i = 0; i < this.numeroAttrezzi; i++)
			peso += this.attrezzi[i].getPeso();
		return peso;
	}

	public boolean isEmpty() {
		return this.numeroAttrezzi == 0;
	}

	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo) != null;
	}

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
