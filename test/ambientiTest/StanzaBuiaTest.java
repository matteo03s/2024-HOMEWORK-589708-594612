package ambientiTest;

import static org.junit.Assert.*;


import org.junit.Test;
import ambienti.*;
import attrezz.*;

public class StanzaBuiaTest {
	StanzaBuia stanza=new StanzaBuia("stanza","torcia");
	Attrezzo a1=new Attrezzo("torcia",2);
	Attrezzo a2=new Attrezzo("torci",2);
	
	/** verifica che non si possa aggiungere un attrezzo nullo */
	@Test
	public void test_AddAttrezzo_Null () {		
		assertFalse(stanza.addAttrezzo(null));
	}

	/** verifica che si possa aggiungere un attrezzo valido */
	@Test
	public void test_AddAttrezzo_Valido() {	
		assertTrue(stanza.addAttrezzo(a1));
	}
	
	/** verifica che non si possa rimuovere un attrezzo che non è nella stanza */
	@Test
	public void test_RemoveAttrezzo_NonValido() {
		assertFalse(stanza.removeAttrezzo(a1));
	}

	/** verifica che si possa rimuovere un attrezzo che è nella stanza */
	@Test
	public void test_RemoveAttrezzo_Valido() {
		stanza.addAttrezzo(a1);
		assertTrue(stanza.removeAttrezzo(a1));
	}
	
	/** test che verifica se in assenza dell'oggetto speciale la descrizione sia modificata */
	@Test
	public void test_GetDescrizione_SenzaOggettoSpeciale() {
		assertEquals("Qui c'è un buio pesto",stanza.getDescrizione());
	}
	
	/** test che verifica se in presenza dell'oggetto speciale la descrizione non sia modificata */
	@Test
	public void test_GetDescrizione_ConOggettoSpeciale() {
		stanza.addAttrezzo(a1);
		assertNotEquals("Qui c'è un buio pesto",stanza.getDescrizione());
	}
	
	/** restituisce il tipo della stanza
	 * @return "BU0" se stanza illuminata "BU1" altrimenti
	 * */
	@Test 
	public void test_Tipo_NonBuia() {
		stanza.addAttrezzo(a1);		
		assertEquals("BU0",stanza.getTipo());
	}
	@Test 
	public void test_Tipo_Buia() {
		stanza.addAttrezzo(a1);
		stanza.removeAttrezzo(a1);
		assertEquals("BU1",stanza.getTipo());
	}
}