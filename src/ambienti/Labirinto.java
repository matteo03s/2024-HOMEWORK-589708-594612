package ambienti;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import attrezz.Attrezzo;

/**
 * Classe Labirinto - un labirinto in un gioco di ruolo.
 * il labirinto gestisce le stanze.
 * il labirinto è gestito dalla partita
 *
 * @author  L.Mattioli-M.Saravo
 * @see Stanza
 * @see Partita
 * 
 */

public class Labirinto {
	
	private Stanza stanzaIniziale;	
	private Stanza stanzaCorrente;
	private Stanza stanzaVincente;
	private Set <Attrezzo> attrezzi;
	
	public Labirinto () {
		this.attrezzi = new HashSet<Attrezzo>();
//		creaStanze();	
	}
	
	
	/**
     * Crea tutte le stanze e le porte di collegamento
     */
    private void creaStanze() {

		/* crea gli attrezzi */
    	Attrezzo lanterna = new Attrezzo("lanterna",3);
		Attrezzo osso = new Attrezzo("osso",1);
		Attrezzo martello = new Attrezzo("martello",4);
		Attrezzo torcia = new Attrezzo("torcia",2);
		Attrezzo chiave = new Attrezzo("chiave",1);

		this.attrezzi.add(lanterna);
		this.attrezzi.add(osso);
		this.attrezzi.add(martello);
		this.attrezzi.add(torcia);
		this.attrezzi.add(chiave);
		
		/* crea stanze del labirinto */
		Stanza atrio = new Stanza("Atrio");
		Stanza aulaN11 = new Stanza("Aula N11");
		Stanza aulaN10 = new Stanza("Aula N10");
		Stanza laboratorio = new Stanza("Laboratorio Campus");
		Stanza biblioteca = new Stanza("Biblioteca");
		StanzaMagica giardino = new StanzaMagica("giardino",2);
		StanzaBuia ripostiglio = new StanzaBuia("ripostiglio","torcia");
		StanzaBloccata aulaN15 = new StanzaBloccata("aulaN15","nord","chiave");
		/* collega le stanze */
		atrio.impostaStanzaAdiacente("nord", biblioteca);
		atrio.impostaStanzaAdiacente("est", aulaN11);
		atrio.impostaStanzaAdiacente("sud", aulaN10);
		atrio.impostaStanzaAdiacente("ovest", laboratorio);
		aulaN11.impostaStanzaAdiacente("est", laboratorio);
		aulaN11.impostaStanzaAdiacente("ovest", atrio);
		aulaN10.impostaStanzaAdiacente("nord", atrio);
		aulaN10.impostaStanzaAdiacente("est", aulaN11);
		aulaN10.impostaStanzaAdiacente("ovest", laboratorio);
		laboratorio.impostaStanzaAdiacente("est", atrio);
		laboratorio.impostaStanzaAdiacente("ovest", aulaN11);
		biblioteca.impostaStanzaAdiacente("sud", atrio);
		laboratorio.impostaStanzaAdiacente("nord", giardino);
		giardino.impostaStanzaAdiacente("sud", laboratorio);
		giardino.impostaStanzaAdiacente("est", ripostiglio);
		ripostiglio.impostaStanzaAdiacente("ovest", giardino);
		giardino.impostaStanzaAdiacente("ovest", aulaN15);
		aulaN15.impostaStanzaAdiacente("est", giardino);
		aulaN15.impostaStanzaAdiacente("nord", aulaN11);
		aulaN11.impostaStanzaAdiacente("sud",aulaN15 );
		
        /* pone gli attrezzi nelle stanze */
		Iterator <Attrezzo> iter = this.attrezzi.iterator();
		aulaN10.addAttrezzo(iter.next());
		atrio.addAttrezzo(iter.next());
		aulaN11.addAttrezzo(iter.next());
		ripostiglio.addAttrezzo(iter.next());
		aulaN15.addAttrezzo(iter.next());

		// il gioco comincia nell'atrio
        stanzaCorrente = atrio;
        stanzaIniziale = stanzaCorrente;
		stanzaVincente = biblioteca;
		
    }
	
    /** serve per impostare novi attrezzi all'interno del labirinto */
    public boolean setNuoviAttrezzi (Attrezzo a) {
    	return this.attrezzi.add(a);
    }
    
    /** ritorna l'intero set di attrezzi nel labirinto */
    public Set <Attrezzo> getAttrezzi () {
    	return this.attrezzi;
    }
    
    /** vede se un attrezzo è presente nel labirinto */ 
    public boolean contieneAttrezzo (Attrezzo a) {
    	return this.attrezzi.contains(a);
    }
    
    
    /** restituisce la stanza iniziale del labirinto
     * @return stanza iniziale
     * */
    public Stanza getStanzaIniziale () {
    	return this.stanzaIniziale;
    }
    
    /** ottiene la stanza vincente del labirinto
     * @return stanza vincente
     * */
    public Stanza getStanzaVincente() {
		return this.stanzaVincente;
	}

    /** setta la stanza corrente
     * @param imposta la stanza iniziale
     * */
	public void setStanzaIniziale (Stanza stanzaIniziale) {
		this.stanzaIniziale = stanzaIniziale;
	}
	
	/** setta la stanza iniziale
     * @param imposta la stanza corrente
     * */
	public void setStanzaCorrente (Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;
	}
	
	/** setta la stanza vincente
     * @param imposta la stanza vincente
     * */
	public void setStanzaVincente (Stanza stanzaVincente) {
		this.stanzaVincente = stanzaVincente;
	}
	
	/** ottiene la stanza corrente 
	 *@return stanza corrente
	 **/
	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}
	
}
