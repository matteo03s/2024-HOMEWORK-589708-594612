package ambientiTest;

import static org.junit.Assert.*;

import org.junit.Test;
import ambienti.*;
import attrezz.*;

public class StanzaMagicaTest {
	StanzaMagica stanza=new StanzaMagica("stanza",2);
	Attrezzo a1=new Attrezzo("chiave",2);
	Attrezzo a2=new Attrezzo("torta",2);
	Attrezzo a3=new Attrezzo("martello",2);
	

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
	
	/** verifica che la funzione che modifica l'attrezzo funzioni correttamente */
	@Test
	public void test_ModificaAttrezzo() {
		stanza.addAttrezzo(a1);
		StringBuilder nomeInvertito;
		nomeInvertito = new StringBuilder("chiave");
		nomeInvertito = nomeInvertito.reverse();
		assertEquals (nomeInvertito.toString(),stanza.modificaAttrezzo(a1).getNome());
		assertEquals (a1.getPeso()*2, stanza.modificaAttrezzo(a1).getPeso());
	}
	
	@Test
	/** restituisce il tipo della stanza
	 * @return "MA0" se la "magia" non è attiva "MA1" altrimenti
	 * */
	public void la_stanza_è_magica() {
		stanza.addAttrezzo(a1);
		stanza.addAttrezzo(a2);
		stanza.addAttrezzo(a3);
		assertEquals("MA1",stanza.getTipo());
	}
	@Test
	public void la_stanza_non_è_magica() {
		stanza.addAttrezzo(a1);
		
		assertEquals("MA0",stanza.getTipo());
	}
}