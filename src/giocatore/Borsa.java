package giocatore;
import attrezz.*;
import diadia.Proprietà;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

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
	public final static int DEFAULT_PESO_MAX_BORSA = Proprietà.getPesoMax();
	//	private Map <String, Integer> attrezzi;
	private List <Attrezzo> attrezzi;
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
		this.attrezzi = new ArrayList<>();
		//		this.attrezzi = new HashMap <>();		//inizializzazione di una lista di attrezzi
		this.numeroAttrezzi = 0;
	}

	/** funzione che aggiunge un attrezzo alla borsa
	 * @param attrezzo da aggiungere
	 * @return true se si può aggiungere, false altrimenti
	 **/
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (attrezzo == null || this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;

		//		this.attrezzi.put(attrezzo.getNome(), attrezzo.getPeso());
		this.attrezzi.add(attrezzo);
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

		/*			if (this.attrezzi.containsKey(nomeAttrezzo)) {
			a = new Attrezzo (nomeAttrezzo, this.attrezzi.get(nomeAttrezzo));
			return a;
		}

		 */		Iterator <Attrezzo> iter = this.attrezzi.iterator();
		 while (iter.hasNext()) {
			 a = iter.next();
			 if (a.getNome().equals(nomeAttrezzo))
				 return a;
		 }

		 return null;
	}

	/** metodo che ottiene il peso della borsa
	 * @return peso borsa
	 * */
	public int getPeso() {
		int peso = 0;

		/*			for (Integer i : this.attrezzi.values())
			peso += i;

		 */		for (Attrezzo a : this.attrezzi)
			 peso += a.getPeso();
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

		a = this.getAttrezzo(nomeAttrezzo);

		//		if (this.attrezzi.remove(nomeAttrezzo, this.attrezzi.get(nomeAttrezzo))) {
		if (this.attrezzi.remove(a)) {
			this.numeroAttrezzi--;
			return a;
		}

		return null;
	}

	/** metodo che restituisce la descrizione della borsa
	 * @return descrizione stringa
	 * */
	public String toString() {
		StringBuilder s = new StringBuilder();
		if (!this.isEmpty()) {
			s.append("Contenuto borsa: " + this.attrezzi);
		} else
			s.append("Borsa vuota");
		return s.toString();
	}

	/** metodo che restituisce gli attrezzi presenti
	 * nella borsa
	 * @return Lista di attrezzi
	 */
	public List <Attrezzo> getContenuto () {
		return this.attrezzi;
	}

	/** metodo che restituisce gli attrezzi presenti
	 * nella borsa ordinata SOLO per peso 
	 * @return Lista di attrezzi
	 */
	public List <Attrezzo> getContenutoOrdinatoPerPeso () {
		List <Attrezzo> ordinata = new ArrayList <> (this.attrezzi);
		ComparatorePerPeso cmp = new ComparatorePerPeso();
		ordinata.sort(cmp);
		return ordinata;
	}

	/** metodo che restituisce gli attrezzi presenti
	 * nella borsa ordinata SOLO per nome
	 * @return SortedSet di attrezzi
	 */
	public Set <Attrezzo> getContenutoOrdinatoPerNome () {
		ComparatorePerNome cmp = new ComparatorePerNome();
		Set <Attrezzo> ordinata = new TreeSet <> (cmp);
		ordinata.addAll(this.attrezzi);

		return ordinata;
	}

	/** metodo che restituisce gli attrezzi presenti
	 * nella borsa raggruppati SOLO per peso 
	 * @return Lista di attrezzi
	 */
	public Map <Integer, Set <Attrezzo>> getContenutoRaggruppatoPerPeso () {
		Map <Integer, Set <Attrezzo>> gruppo = new HashMap <> ();
		Set <Attrezzo> temp;
		for (Attrezzo controllore : this.attrezzi) {
			temp = gruppo.get(controllore.getPeso());
			if (temp == null)
				temp = new HashSet <> ();
			temp.add(controllore);
			gruppo.put(controllore.getPeso(), temp);
		}
		return gruppo;
	}
	
	/** metodo che restituisce gli attrezzi presenti nella 
	 * borsa ordinata per peso e a parità di peso, per nome
	 * @return Lista di attrezzi
	 */
	public Set <Attrezzo> getSortedSetOrdinatoPerPeso () {
		Set <Attrezzo> ordinata = new TreeSet <> (this.attrezzi);
		return ordinata;
	}



}
