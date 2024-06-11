package ambientiTest;

import static org.junit.Assert.*;

import org.junit.Test;
import ambienti.*;
import attrezz.*;

public class StanzaBloccataTest {
	StanzaBloccata stanza = new StanzaBloccata("stanza",Direzione.nord,"chiave");
	Attrezzo a1 = new Attrezzo("chiave", 2);
	Stanza vicina = new Stanza ("vicina");
	
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
	
	/** restituisce il tipo della stanza
	 * @return "BL0" se è libera, "BL1" se ha direzioni bloccate
	 * */
	@Test
	public void test_Tipo_Sbloccata() {
		stanza.addAttrezzo(a1);
		assertEquals("BL0",stanza.getTipo());
	}
	@Test
	public void test_Tipo_Bloccata() {
		stanza.addAttrezzo(a1);
		stanza.removeAttrezzo(a1);
		assertEquals("BL1",stanza.getTipo());
	}
	
	/** verifica che la direzione sia sbloccata */
	@Test
	public void test_GetStanzaAdiacente_Valido () {
		stanza.addAttrezzo(a1);
		stanza.impostaStanzaAdiacente(Direzione.nord, vicina);
		assertEquals (stanza.getStanzaAdiacente(Direzione.nord).getNome(), "vicina" );
	}
	
	/** verifica che la direzione sia bloccata */
	@Test
	public void test_GetStanzaAdiacente_NonValido () {
		stanza.impostaStanzaAdiacente(Direzione.nord, vicina);
		assertEquals (stanza.getStanzaAdiacente(Direzione.nord).getNome(), "stanza" );
	}
	
}