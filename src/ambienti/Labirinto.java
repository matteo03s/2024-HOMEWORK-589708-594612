package ambienti;
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
		
	private Stanza stanzaCorrente;
	private Stanza stanzaVincente;
	
	public Labirinto () {
		creaStanze();
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
		Attrezzo Chiave = new Attrezzo("chiave",1);
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
		aulaN10.addAttrezzo(lanterna);
		atrio.addAttrezzo(osso);
		aulaN11.addAttrezzo(martello);
		ripostiglio.addAttrezzo(torcia);
		aulaN15.addAttrezzo(Chiave);
		// il gioco comincia nell'atrio
        stanzaCorrente = atrio;  
		stanzaVincente = biblioteca;
    }
	
    /** ottiene la stanza vincente del labirinto
     * @return stanza vincente
     * */
    public Stanza getStanzaVincente() {
		return stanzaVincente;
	}

    /** setta la stanza corrente
     * @param imposta la stanza corrente
     * */
	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;
	}
	
	/** ottiene la stanza corrente 
	 *@return stanza corrente
	 **/
	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}
	
}
