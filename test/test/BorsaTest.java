package test;

/*
 * Test per la classe Borsa
 * @see Borsa 
 */

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;
import org.junit.Test;
import giocatore.*;
import attrezz.*;

public class BorsaTest {
	Borsa borsa=new Borsa();
	Attrezzo a1=new Attrezzo("ad",2);
	Attrezzo a2=new Attrezzo("ao",2);
	Attrezzo a3=new Attrezzo("aop",2);
	Attrezzo a4=new Attrezzo("a8o",2);
	Attrezzo a5=new Attrezzo("ayu",2);
	Attrezzo a6=new Attrezzo("aipoo",2);
	Attrezzo a7=new Attrezzo("aio9",2);
	Attrezzo a8=new Attrezzo("aio88",2);
	Attrezzo a9=new Attrezzo("aio99",2);
	Attrezzo a10=new Attrezzo("aio1",2);
	Attrezzo a11=new Attrezzo("aio17",2);
	@Before
	public void inserisci() {
		borsa.addAttrezzo(a1);
		borsa.addAttrezzo(a2);
		borsa.addAttrezzo(a3);
		borsa.addAttrezzo(a4);
		borsa.addAttrezzo(a5);
		borsa.addAttrezzo(a6);
		borsa.addAttrezzo(a7);
		borsa.addAttrezzo(a8);
		borsa.addAttrezzo(a9);
		borsa.addAttrezzo(a10);
	}
	@Test
	public void aggiungiattrezzonull() {
		assertFalse(borsa.addAttrezzo(null));
	}

	@Test
	public void aggiungiattrezzostanzapiena () {

		assertFalse(borsa.addAttrezzo(a11));
	}
	@Test
	public void removeattrezzoesiste() {
		assertEquals(a1,borsa.removeAttrezzo(a1.getNome()));
	}
	@Test
	public void removeattrezzononesiste() {
		assertEquals(null,borsa.removeAttrezzo(a11.getNome()));
	}
	@Test
	public void removeattrezzonull() {
		assertEquals(null,borsa.removeAttrezzo(null));
	}


}
