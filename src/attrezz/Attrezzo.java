package attrezz;
import ambienti.Stanza;

/**
 * Una semplice classe che modella un attrezzo.
 * Gli attrezzi possono trovarsi all'interno delle stanze
 * del labirinto.
 * Ogni attrezzo ha un nome ed un peso.
 *
 * @author  docente di POO
 * @see Stanza
 * @version base
 */
public class Attrezzo implements Comparable <Attrezzo>{

	private String nome;
	private int peso;

	/**
	 * Crea un attrezzo
	 * @param nome il nome che identifica l'attrezzo
	 * @param peso il peso dell'attrezzo
	 */
	public Attrezzo (String nome, int peso) {
		this.peso = peso;
		this.nome = nome;
	}

	/**
	 * Restituisce il nome identificatore dell'attrezzo
	 * @return il nome identificatore dell'attrezzo
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Restituisce il peso dell'attrezzo
	 * @return il peso dell'attrezzo
	 */
	public int getPeso() {
		return this.peso;
	}

	@Override
	public boolean equals (Object o) {
		Attrezzo a = (Attrezzo) o;
		return (this.getNome().equals(a.getNome()) && this.peso == a.getPeso());	
	}
	/**
	 * Restituisce una rappresentazione stringa di questo attrezzo
	 * @return la rappresentazione stringa
	 */
	public String toString() {
		return this.getNome()+" ("+this.getPeso()+"kg)";
	}

	/****/
	@Override
	public int hashCode () {
		return this.getNome().hashCode();
		
	}
	
	/** metodo in override che compara due attrezzi prima
	 * per peso, in parità di peso, per nome 
	 * @return un intero maggiore di 0 se il primo è più grande del secondo,
	 * un intero minore di 0 se il primo è più piccolo del secondo,
	 * 0 se sono uguali */
	@Override
	public int compareTo(Attrezzo that) {
		int cmp;
		cmp = this.getPeso() - that.getPeso();
		if (cmp == 0)
			cmp = this.getNome().compareTo(that.getNome());
		return cmp;
	}

}