package test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Test;
import org.junit.Before;
import org.junit.Test;
import ambienti.*;

/*
 * Test per la classe Labirinto
 * @see Labirinto
 */

public class LabirintoTest {

Labirinto l=new Labirinto();
Stanza s=new Stanza("Biblioteca");

	@Test
	public void get_stanzacorrente_constanzanull() {
	
		l.setStanzaCorrente(null);
		assertEquals(null,l.getStanzaCorrente());
	}
	@Test
	public void get_stanza_corrente() {
		
		l.setStanzaCorrente(s);
		assertEquals(s,l.getStanzaCorrente());
	}
	@Test 
	public void getstanzavincente() {
		l.setStanzaCorrente(s);
		assertEquals(l.getStanzaCorrente().getNome(),l.getStanzaVincente().getNome());
	}
	
	
}