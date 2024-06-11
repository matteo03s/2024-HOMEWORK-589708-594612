package ambientiTest;

import ambienti.*;
import attrezz.*;

import static org.junit.Assert.*;

import org.junit.Test;



/*
 * Test per la classe Labirinto
 * @see Labirinto
 */

public class LabirintoTest {
	public Labirinto l = Labirinto.newLab();
	public Stanza s = new Stanza("Biblioteca");;

	/** testare se la stanza corrente funziona correttamente*/
	@Test
	public void test_GetStanzaCorrente() {		
		l.setStanzaCorrente(s);
		assertEquals (s,l.getStanzaCorrente());
	}
	
	/** testare la stanza corrente se questa è null */
	@Test
	public void test_GetStanzaCorrente_Null () {
		l.setStanzaCorrente(null);
		assertEquals(null,l.getStanzaCorrente());
	}	
	
	/** testa se la stanza vincente è quella giusta */
	@Test 
	public void test_GetStanzaVincente() {
		l.setStanzaCorrente(s);
		l.setStanzaVincente(s);
		assertEquals(l.getStanzaCorrente().getNome(),l.getStanzaVincente().getNome());
	}
	
	/** testa se l'aggiunta degli attrezzi funziona */
	@Test
	public void test_ContieneAttrezzi () {
		Attrezzo a = new Attrezzo ("a1", 2);
		l.setNuoviAttrezzi(a);	
		assertTrue (l.contieneAttrezzo(a));
	}
	
	/** testa se contiene oggetti non inseriti*/
	@Test
	public void test_ContieneAttrezzi_NonPresenti () {
		Attrezzo a1 = new Attrezzo ("a1", 2);
		Attrezzo a2 = new Attrezzo ("a2", 2);
		l.setNuoviAttrezzi(a2);	
		assertFalse (l.contieneAttrezzo(a1));
	}
	
	/** testa aggiunta attrezzo null */
	@Test
	public void test_ContieneAttrezzi_Null () {
		l.setNuoviAttrezzi(null);	
		assertTrue (l.contieneAttrezzo(null));
	}
	
	/** testa se può esistere più di un oggetto uguale */
	@Test
	public void test_ContieneAttrezzi_duplicati () {
		Attrezzo a1 = new Attrezzo ("a1", 2);
		assertTrue (l.setNuoviAttrezzi(a1));
		assertFalse (l.setNuoviAttrezzi(a1));
		assertTrue (l.contieneAttrezzo(a1));
	}
	


}