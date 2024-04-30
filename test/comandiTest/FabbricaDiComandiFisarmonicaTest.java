package comandiTest;

import static org.junit.Assert.*;

import org.junit.Test;

import comandi.Comando;
import comandi.FabbricaDiComandiFisarmonica;
import diadia.IO;
import diadia.IOConsole;
import diadia.Partita;
import diadiaTest.*;

public class FabbricaDiComandiFisarmonicaTest {

	/** test comando vai */
	@Test
	public void test_Vai() {
		Comando c;
		FabbricaDiComandiFisarmonica factory = new FabbricaDiComandiFisarmonica ();
		c = factory.costruisciComando("vai");
		assertEquals (c.getNome(), "vai");
	}
		
	/** test comando prendi */
	@Test
	public void test_Prendi() {
		Comando c;
		FabbricaDiComandiFisarmonica factory = new FabbricaDiComandiFisarmonica ();
		c = factory.costruisciComando("prendi");
		assertEquals (c.getNome(), "prendi");
	}

	/** test comando posa */
	@Test
	public void test_Posa() {
		Comando c;
		FabbricaDiComandiFisarmonica factory = new FabbricaDiComandiFisarmonica ();
		c = factory.costruisciComando("posa");
		assertEquals (c.getNome(), "posa");
	}
	
	/** test comando guarda */
	@Test
	public void test_Guarda() {
		Comando c;
		FabbricaDiComandiFisarmonica factory = new FabbricaDiComandiFisarmonica ();
		c = factory.costruisciComando("guarda");
		assertEquals (c.getNome(), "guarda");
	}
	
	/** test comando sconosciuto */
	@Test
	public void test_Sconosciuto() {
		Comando c;
		FabbricaDiComandiFisarmonica factory = new FabbricaDiComandiFisarmonica ();
		c = factory.costruisciComando("");
		assertEquals (c.getNome(), "comando non valido");
	}
	
	/** test comando null */
/*	@Test
	public void test_Nullo() {
		Comando c;
		FabbricaDiComandiFisarmonica factory = new FabbricaDiComandiFisarmonica ();
		String nomeComando = null;
		c = factory.costruisciComando(nomeComando);
		assertEquals (c, null);
	}
*/	
	
	/** test comando aiuto */
	@Test
	public void test_Aiuto() {
		Comando c;
		FabbricaDiComandiFisarmonica factory = new FabbricaDiComandiFisarmonica ();
		c = factory.costruisciComando("aiuto");
		assertEquals (c.getNome(), "aiuto");
	}
	
	/** test comando aiuto parametro */
	@Test
	public void test_AiutoParametro() {
		Comando c;
		Partita p = new Partita ();
		IO io = new IOConsole ();
		FabbricaDiComandiFisarmonica factory = new FabbricaDiComandiFisarmonica ();
		c = factory.costruisciComando("aiuto ciao");
		c.esegui(p, io);
		
		assertEquals (c.getParametro(), null);
	}
	
	/** test comando fine */
	@Test
	public void test_Fine() {
		Comando c;
		FabbricaDiComandiFisarmonica factory = new FabbricaDiComandiFisarmonica ();
		c = factory.costruisciComando("fine");
		assertEquals (c.getNome(), "fine");
	}
	
	/** test comando fine che effettivamente finisca la partita */
	@Test
	public void test_FineFinita() {
		Comando c;
		Partita p = new Partita();
		IO io = new IOConsole ();
		FabbricaDiComandiFisarmonica factory = new FabbricaDiComandiFisarmonica ();
		c = factory.costruisciComando("fine");
		c.esegui(p, io);
		assertTrue (p.isFinita());
	}
	
	/** test comando fine parametro */
	@Test
	public void test_FineParametro() {
		Comando c;
		Partita p = new Partita ();
		IO io = new IOConsole ();
		FabbricaDiComandiFisarmonica factory = new FabbricaDiComandiFisarmonica ();
		c = factory.costruisciComando("fine ciao");
		c.esegui(p, io);
		
		assertEquals (c.getParametro(), null);
	}
	
	
	
}
