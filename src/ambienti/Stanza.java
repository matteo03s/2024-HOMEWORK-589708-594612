package ambienti;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import attrezz.Attrezzo;
import giocatore.AbstractPersonaggio;
import giocatore.Borsa;

/**
 * Classe Stanza - una stanza in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita e' associata ad una direzione.
 * 
 * @author docente di POO 
 * @see Attrezzo
 * @version base
 */

public class Stanza {

	static final private int NUMERO_MASSIMO_DIREZIONI = 4;

	private String nome;
	private List <Attrezzo> attrezzi;
	private int numeroAttrezzi;
	private Map <Direzione, Stanza> stanzeAdiacenti;
	//    private Stanza[] stanzeAdiacenti;
	private int numeroStanzeAdiacenti;
	//	private String[] direzioni;
	private String tipo = "S";
	private AbstractPersonaggio pers;


	/**
	 * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
	 * @param nome il nome della stanza
	 */
	public Stanza(String nome) {
		this.nome = nome;
		this.numeroStanzeAdiacenti = 0;
		this.numeroAttrezzi = 0;
		this.stanzeAdiacenti = new HashMap <> (NUMERO_MASSIMO_DIREZIONI);
		//        this.direzioni = new String[NUMERO_MASSIMO_DIREZIONI];
		//        this.stanzeAdiacenti = new Stanza[NUMERO_MASSIMO_DIREZIONI];
		this.attrezzi = new ArrayList<>();
		this.pers = null;
	}

	/** restituisce il tipo della stanza
	 * @return stringa tipo
	 * */
	public String getTipo () {
		return this.tipo;
	}

	/**
	 * Imposta una stanza adiacente.
	 *
	 * @param direzione direzione in cui sara' posta la stanza adiacente.
	 * @param stanza stanza adiacente nella direzione indicata dal primo parametro.
	 */
	public void impostaStanzaAdiacente(Direzione direzione, Stanza stanza) {
		this.stanzeAdiacenti.putIfAbsent(direzione, stanza);
		this.numeroStanzeAdiacenti++;
	}

	/**
	 * Restituisce la stanza adiacente nella direzione specificata
	 * @param direzione
	 * @return stanza stanza adiacente
	 */
	public Stanza getStanzaAdiacente(Direzione direzione) {
		if (direzione == null)
			return null;
		if (this.getTipo().equals("BL1"))
			return this;
		Stanza stanza = null;
		stanza = this.stanzeAdiacenti.get(direzione);
		return stanza;
	}

	/**
	 * Restituisce la nome della stanza.
	 * @return il nome della stanza
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Restituisce la descrizione della stanza.
	 * @return la descrizione della stanza
	 */
	public String getDescrizione() {
		return this.toString();
	}

	/**
	 * Restituisce la collezione di attrezzi presenti nella stanza.
	 * @return la collezione di attrezzi nella stanza.
	 */
	public List <Attrezzo> getAttrezzi() {
		return this.attrezzi;
	}

	/**
	 * Mette un attrezzo nella stanza.
	 * @param attrezzo l'attrezzo da mettere nella stanza.
	 * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (attrezzo == null)
			return false;
		this.attrezzi.add(attrezzo);
		this.numeroAttrezzi++;
		return true;
	}

	/** metodo che restituisce la mappa delle stanze adiacenti
	 * @return StanzeAdiacenti
	 */
	public Map <Direzione, Stanza> getMapStanzeAdiacenti () {
		return this.stanzeAdiacenti;
	}
	/**
	 * Restituisce una rappresentazione stringa di questa stanza,
	 * stampadone la descrizione, le uscite e gli eventuali attrezzi contenuti
	 * @return la rappresentazione stringa
	 */
	public String toString() {
		StringBuilder risultato = new StringBuilder();
		risultato.append(this.nome);
		risultato.append("\nUscite: ");
		risultato.append(this.stanzeAdiacenti.keySet());
		risultato.append("\nAttrezzi nella stanza: ");
		risultato.append(this.attrezzi);
		if (this.pers != null)
			risultato.append("\n" + this.pers.getDescrizione());
		return risultato.toString();
	}

	/**
	 * Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	 * @return true se l'attrezzo esiste nella stanza, false altrimenti.
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo) != null;
	}

	/**
	 * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza.
	 * 		   null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		Iterator <Attrezzo> iter = this.attrezzi.iterator();
		while (iter.hasNext()) {
			a = iter.next();
			if (a.getNome().equals(nomeAttrezzo))
				return a;
		}
		return null;
	}

	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * @param attrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	public boolean removeAttrezzo(Attrezzo attrezzo) {
		if (attrezzo == null || this.hasAttrezzo(attrezzo.getNome()) == false)
			return false;
		if (this.attrezzi.remove(attrezzo)) {
			this.numeroAttrezzi--;
			return true;
		}
		return false;
	}

	/** restituisce le direzioni possibili dalla stanza corrente 
	 * @return direzioni possibili dalla stanza corrente
	 * */
	public Set <Direzione> getDirezioni() {
		Set <Direzione> direzioni = this.stanzeAdiacenti.keySet();
		return direzioni;
	}

	/** funzione che modifica l'attrezzo 
	 * @param attrezzo l'attrezzo da modificare
	 * @return attrezzo l'attrezzo modificato
	 * */
	public Attrezzo modificaAttrezzo(Attrezzo attrezzo) {
		Attrezzo a = null;
		return a;
	}
	
	public String getChiave () {
		return null;
	}

	/** permette di aggiungere un personaggio alla stanza
	 * @param AbstractPersonaggio personaggio
	 * */
	public void SetPersonaggio (AbstractPersonaggio personaggio) {
		this.pers = personaggio;
	}
	
	/** restituisce il personaggio che si
	 * trova nella stanza, null altrimenti
	 * @return AbstractPersonaggio personaggio
	 * */
	public AbstractPersonaggio getPersonaggio () {
		return this.pers;
	}
	
	@Override
	public boolean equals (Object o) {
		Stanza that = (Stanza)o;	
		return this.getNome().equals(that.getNome());
	}

	public int getSoglia() {
		return 0;
	}

	public Direzione getDirBloccata() {
		return null;
	}



}