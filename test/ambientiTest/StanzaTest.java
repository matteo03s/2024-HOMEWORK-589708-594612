package ambientiTest;

import static org.junit.Assert.*;
import org.junit.Test;

import ambienti.Direzione;
import ambienti.Stanza;

import org.junit.Before;
import org.junit.Test;
import attrezz.*;
import diadiaTest.*;
import giocatore.*;

import org.junit.Test;

/*
 * Test per la classe Stanza
 * @see Stanza 
 */

public class StanzaTest {

	public Stanza stanza;
	Attrezzo a1 = new Attrezzo ("a1", 1);
	Attrezzo a2 = new Attrezzo ("a2", 1);
	Attrezzo a3 = new Attrezzo ("a3", 1);
	Attrezzo a4 = new Attrezzo ("a4", 1);
	Attrezzo a5 = new Attrezzo ("a5", 1);
	Attrezzo a6 = new Attrezzo ("a6", 1);
	Attrezzo a7 = new Attrezzo ("a7", 1);
	Attrezzo a8 = new Attrezzo ("a8", 1);
	Attrezzo a9 = new Attrezzo ("a9", 1);
	Attrezzo a10 = new Attrezzo ("a10", 1);
	
	/** aggiunta di un attrezzo normale */
	@Test
	public void test_AddAttrezzo() {
		this.stanza = new Stanza ("vuota");
		assertTrue (this.stanza.addAttrezzo(a1));
	}
	
	/** aggiunta di un attrezzo null */
	@Test
	public void test_AddAttrezzo_Null () {
		this.stanza = new Stanza ("nulla");
		a1 = null;
		assertFalse (this.stanza.addAttrezzo(a1));
	}
	
	/** rimozione di un attrezzo normale */
	@Test
	public void test_RemoveAttrezzo() {
		this.stanza = new Stanza ("con a1");
		this.stanza.addAttrezzo(a1);
		assertTrue (this.stanza.removeAttrezzo(a1));
	}
	
	/** rimozione di un attrezzo null */
	@Test
	public void test_RemoveAttrezzo_Null() {
		this.stanza = new Stanza ("con a1 nullo");
		this.stanza.addAttrezzo(a1);
		a1 = null;
		assertFalse (this.stanza.removeAttrezzo(a1));
	}
	
	/** rimozione di un attrezzo non presente */
	@Test
	public void test_RemoveAttrezzo_NonPresente() {
		this.stanza = new Stanza ("con a1");
		this.stanza.addAttrezzo(a1);
		assertFalse (this.stanza.removeAttrezzo(a2));
	}
	
	/** test getStanzaAdiacente */
	@Test
	public void test_getStanzaAdiacente () {
		this.stanza = new Stanza ("partenza");
		Stanza vicina = new Stanza ("stanza_adiacente");
		
		this.stanza.impostaStanzaAdiacente(Direzione.sud, vicina);
		assertEquals (vicina.getNome(), this.stanza.getStanzaAdiacente(Direzione.sud).getNome());
	}

	/** test getStanzaAdiacente direzione nulla */
	@Test
	public void test_getStanzaAdiacente_null () {
		this.stanza = new Stanza ("partenza");
		Stanza vicina = new Stanza ("stanza_adiacente");
		this.stanza.impostaStanzaAdiacente(Direzione.sud, vicina);
		
		assertEquals (null, this.stanza.getStanzaAdiacente(Direzione.nord));	
	}
		
}
