/**
 * Una semplice classe che serve a creare un labirinto.
 *
 * @author  M.Saravo-L.Mattioli
 * @see Labirinto
 * @see Stanza
 *
 */
package ambienti;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import attrezz.Attrezzo;

public class LabirintoBuilder {

	private Map <String, Stanza> stanzeLabirinto = new HashMap <> ();
	private Labirinto lab = new Labirinto ();
	private Stanza ultimaStanza;


	/** metodo che aggiunge la stanza iniziale del labirinto
	 * @param nome della stanza da inserire */
	public LabirintoBuilder addStanzaIniziale (String nomeStanza) {
		Stanza creata = new Stanza (nomeStanza);
		stanzeLabirinto.put(nomeStanza, creata);
		this.lab.setStanzaIniziale (creata);
		this.lab.setStanzaCorrente(creata);
		this.ultimaStanza = creata;
		return this;
	}

	/** metodo che aggiunge la stanza vincente del labirinto 
	 * @param nome della stanza da inserire */
	public LabirintoBuilder addStanzaVincente (String nomeStanza) {
		Stanza creata = new Stanza (nomeStanza);
		stanzeLabirinto.put(nomeStanza, creata);
		this.lab.setStanzaVincente(creata);
		this.ultimaStanza = creata;
		return this;
	}

	/** metodo che aggiunge una stanza qualunque del labirinto
	 * @param nome della stanza da inserire */
	public LabirintoBuilder addStanza (String nomeStanza) {
		Stanza creata = new Stanza (nomeStanza);
		stanzeLabirinto.put(nomeStanza, creata);
		this.ultimaStanza = creata;
		return this;
	}
	
	/** metodo che aggiunge una stanza magica nel labirinto 
	 * @param nome della stanza magica da inserire */
	public LabirintoBuilder addStanzaMagica (String nomeStanzaMagica) {
		Stanza creata = new StanzaMagica (nomeStanzaMagica);
		stanzeLabirinto.put(nomeStanzaMagica, creata);
		this.ultimaStanza = creata;
		return this;
	}
	
	/** metodo che aggiunge una stanza magica nel labirinto 
	 * @param nomeStanzaMagica nome della stanza magica da inserire
	 * @param sogliaMagica int */
	public LabirintoBuilder addStanzaMagica (String nomeStanzaMagica, int sogliaMagica) {
		Stanza creata = new StanzaMagica (nomeStanzaMagica, sogliaMagica);
		stanzeLabirinto.put(nomeStanzaMagica, creata);
		this.ultimaStanza = creata;
		return this;
	}
	
	/** metodo che aggiunge una stanza bloccata nel labirinto
	 * @param nomeStanzaBloccata nome della stanza bloccata
	 * @param direzioneBloccata String
	 * @param nomeChiave String*/
	public LabirintoBuilder addStanzaBloccata (String nomeStanzaBloccata, String direzioneBloccata, String nomeChiave) {
		StanzaBloccata creata = new StanzaBloccata (nomeStanzaBloccata, direzioneBloccata, nomeChiave);
		stanzeLabirinto.put(nomeStanzaBloccata, creata);
		this.ultimaStanza = creata;
		return this;
	}
	
	/** metodo che aggiunge una stanza buia nel labirinto
	 * @param nomeStanzaBuia nome della stanza buia
	 * @param nomeLuce String*/
	public LabirintoBuilder addStanzaBuia (String nomeStanzaBuia, String nomeLuce) {
		StanzaBuia creata = new StanzaBuia (nomeStanzaBuia, nomeLuce);
		stanzeLabirinto.put(nomeStanzaBuia, creata);
		this.ultimaStanza = creata;
		return this;
	}

	/** metodo che aggiunge un attrezzo all'ultima stanza creata
	 * @param attrezzo da aggiungere */
	public LabirintoBuilder addAttrezzo (Attrezzo a) {
		if (this.lab.setNuoviAttrezzi(a))
			this.ultimaStanza.addAttrezzo(a);
		return this;
	}


	/** metodo che aggiunge un attrezzo all'ultima stanza creata
	 * @param nomeAttrezzo String nome attrezzo da aggiungere
	 * @param pesoAttrezzo int peso attrezzo da aggiungere */
	public LabirintoBuilder addAttrezzo (String nomeAttrezzo, int pesoAttrezzo) {
		Attrezzo a = new Attrezzo (nomeAttrezzo, pesoAttrezzo);
		if (this.lab.setNuoviAttrezzi(a))
			this.ultimaStanza.addAttrezzo(a);	
		return this;
	}


	/** metodo che imposta un'adiacenza tra due stanza passate 
	 * @param nomeStanzaPartenza String
	 * @param nomeStanzaArrivo String
	 * @param direzione String */
	public LabirintoBuilder addAdiacenza (String nomeStanzaPartenza, String nomeStanzaArrivo, String direzione) {
		if (direzione.equals("nord") || direzione.equals("sud")
				|| direzione.equals("est") || direzione.equals("ovest")) {
			this.stanzeLabirinto.get(nomeStanzaPartenza).impostaStanzaAdiacente
			(direzione, this.stanzeLabirinto.get(nomeStanzaArrivo));
			return this;
		}
		return this;
	}

	/** metodo che restituisce la lista di stanze del labirinto 
	 * @return listaStanze Map */
	public Map <String, Stanza> getListaStanze () {
		return this.stanzeLabirinto;
	}

	/** metodo che restituisce il labirinto creato 
	 * @return oggetto di tipo labirinto */
	public Labirinto getLabirinto () {
		return this.lab;
	}
}
