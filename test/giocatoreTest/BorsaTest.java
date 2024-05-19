package giocatoreTest;

/*
 * Test per la classe Borsa
 * @see Borsa 
 */

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;
import org.junit.Before;
import org.junit.Test;
import giocatore.*;
import attrezz.*;

public class BorsaTest {
	Borsa borsa = new Borsa();
	Borsa ordinata = new Borsa (25);
	Attrezzo piombo;
	Attrezzo ps;
	Attrezzo piuma;
	Attrezzo libro;

	@Before
	public void inserisci() {
		piombo = new Attrezzo ("piombo", 10);
		ps = new Attrezzo ("ps", 5);
		piuma = new Attrezzo ("piuma", 1);
		libro = new Attrezzo ("libro", 5);
		ordinata.addAttrezzo(piombo);
		ordinata.addAttrezzo(ps);
		ordinata.addAttrezzo(piuma);
		ordinata.addAttrezzo(libro);

	}


	/** aggiunta di un attrezzo alla borsa*/
	@Test
	public void test_AddAttrezzo () {
		Attrezzo a1=new Attrezzo("ad",2);
		assertTrue (borsa.addAttrezzo(a1));
	}

	/** aggiunta di troppo peso alla borsa */
	@Test
	public void test_AddAttrezzo_TroppoPeso () {
		Attrezzo a1=new Attrezzo("ad",10);
		Attrezzo a2 = new Attrezzo("ae",1);	
		assertTrue (borsa.addAttrezzo(a1));
		assertFalse (borsa.addAttrezzo(a2));
	}

	/** aggiunta di un attrezzo nullo alla borsa*/
	@Test
	public void test_AddAttrezzo_Null () {
		assertFalse(borsa.addAttrezzo(null));
	}


	/** test rimozione attrezzo dalla borsa*/
	@Test
	public void test_RemoveAttrezzo_Presente () {
		Attrezzo a1 = new Attrezzo ("a1", 5);
		borsa.addAttrezzo(a1);
		assertEquals(a1,borsa.removeAttrezzo(a1.getNome()));

	}

	/** test rimozione attrezzo non presente nella borsa */
	@Test
	public void test_RemoveAttrezzo_NonPresente () {
		Attrezzo a1 = new Attrezzo ("a1", 5);
		assertEquals(null,borsa.removeAttrezzo(a1.getNome()));
	}

	/** test rimozione attrezzo nullo dalla borsa*/
	@Test
	public void test_RemoveAttrezzo_Null () {
		assertEquals(null,borsa.removeAttrezzo(null));
	}

	/** test che verifica che non funzioni l'ordinamento 
	 * per peso di una borsa vuota */
	@Test
	public void test_GetContenutoOrdinatoPerPeso_Vuota () {
		assertEquals (Collections.EMPTY_LIST, borsa.getContenutoOrdinatoPerPeso());
	}

	/** test che verifica che funzioni l'ordinamento 
	 * per peso di una borsa non vuota ma sbagliato */
	@Test
	public void test_GetContenutoOrdinatoPerPeso_Sbagliato () {
		List <Attrezzo> expected1 = List.of(piombo, ps, libro, piuma);
		assertFalse	(ordinata.getContenutoOrdinatoPerPeso().equals(expected1));
	}

	/** test che verifica che funzioni l'ordinamento 
	 * per peso di una borsa non vuota giusta */
	@Test
	public void test_GetContenutoOrdinatoPerPeso () {
		List <Attrezzo> expected1 = List.of(piuma, ps, libro, piombo);
		List <Attrezzo> expected2 = List.of(piuma, libro, ps, piombo);
		assertTrue	(ordinata.getContenutoOrdinatoPerPeso().equals(expected1)
				|| ordinata.getContenutoOrdinatoPerPeso().equals(expected2));	
	}

	/** test che verifica che non funzioni l'ordinamento 
	 * per nome di una borsa vuota */
	@Test
	public void test_GetContenutoOrdinatoPerNome_Vuota () {
		assertEquals (Collections.EMPTY_SET, borsa.getContenutoOrdinatoPerNome());
	}

	/** test che verifica che funzioni l'ordinamento 
	 * per nome di una borsa non vuota sbagliata*/
	@Test
	public void test_GetContenutoOrdinatoPerNome_Sbagliata () {
		List <Attrezzo> expected = List.of(ps, piuma, libro, piombo);
		Iterator <Attrezzo> iter = ordinata.getContenutoOrdinatoPerNome().iterator();
		Iterator <Attrezzo> controller = expected.iterator();
		while (iter.hasNext())
			assertNotEquals (iter.next(), controller.next());
	}
	
	/** test che verifica che funzioni l'ordinamento 
	 * per nome di una borsa non vuota giusta*/
	@Test
	public void test_GetContenutoOrdinatoPerNome_Funzionante () {
		List <Attrezzo> expected = List.of(libro, piombo, piuma, ps);
		Iterator <Attrezzo> iter = ordinata.getContenutoOrdinatoPerNome().iterator();
		Iterator <Attrezzo> controller = expected.iterator();
		while (iter.hasNext())
			assertEquals (iter.next(), controller.next());
	}
	
	/** test che verifica che non funzioni l'ordinamento 
	 * per peso e per nome di una borsa vuota */
	@Test
	public void test_GetSortedSetOrdinatoPerPeso_Vuota () {
		assertEquals (Collections.EMPTY_SET, borsa.getSortedSetOrdinatoPerPeso());
	}

	/** test che verifica che funzioni l'ordinamento 
	 * per peso di una borsa non vuota ma sbagliato */
	@Test
	public void test_GetSortedSetOrdinatoPerPeso_Sbagliato () {
		List <Attrezzo> expected = List.of(piombo, piuma, libro, ps);
		Iterator <Attrezzo> iter = ordinata.getSortedSetOrdinatoPerPeso().iterator();
		Iterator <Attrezzo> controller = expected.iterator();
		while (iter.hasNext())
			assertNotEquals (iter.next(), controller.next());
	}

	/** test che verifica che funzioni l'ordinamento 
	 * per peso di una borsa non vuota giusta */
	@Test
	public void test_GetSortedSetOrdinatoPerPeso_Funzionante () {
		List <Attrezzo> expected = List.of(piuma, libro, ps, piombo);
		Iterator <Attrezzo> iter = ordinata.getSortedSetOrdinatoPerPeso().iterator();
		Iterator <Attrezzo> controller = expected.iterator();

		while (iter.hasNext())
			assertEquals (iter.next(), controller.next());
	}
	
	/** test che verifica che non funzioni l'ordinamento 
	 * per peso e per nome di una borsa vuota */
	@Test
	public void test_getContenutoRaggruppatoPerPeso_Vuota () {
		assertEquals (Collections.EMPTY_MAP, borsa.getContenutoRaggruppatoPerPeso());
		
	}

	/** test che verifica che funzioni l'ordinamento 
	 * per peso di una borsa non vuota ma sbagliato */
	@Test
	public void test_getContenutoRaggruppatoPerPeso_Sbagliato () {
		Map <Integer, Set <Attrezzo>> expected1 = new HashMap <> ();
		expected1.put(1, Set.of(piombo));
		expected1.put(10, Set.of(piuma));
		expected1.put(3, Set.of(libro, ps));
		assertNotEquals(ordinata.getContenutoRaggruppatoPerPeso(), expected1);
	}

	/** test che verifica che funzioni l'ordinamento 
	 * per peso di una borsa non vuota giusta */
	@Test
	public void test_getContenutoRaggruppatoPerPeso_Funzionante () {
		Map <Integer, Set <Attrezzo>> expected1 = new HashMap <> ();
		expected1.put(10, Set.of(piombo));
		expected1.put(1, Set.of(piuma));
		expected1.put(5, Set.of(libro, ps));
		assertEquals(ordinata.getContenutoRaggruppatoPerPeso(), expected1);
	}
}
