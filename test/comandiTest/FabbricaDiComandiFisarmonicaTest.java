package comandiTest;

import static org.junit.Assert.*;

import org.junit.Test;

import comandi.AbstractComando;
import comandi.Comando;
import comandi.FabbricaDiComandiFisarmonica;
import diadia.IO;
import diadia.IOConsole;
import diadia.Partita;
import diadiaTest.*;

public class FabbricaDiComandiFisarmonicaTest {

	/** test comando vai 
	 * @throws Exception */
	@Test
	public void test_Vai() throws Exception {
		Comando c;
		FabbricaDiComandiFisarmonica factory = new FabbricaDiComandiFisarmonica ();
		c = factory.costruisciComando("vai");
		assertEquals (c.getNome(), "vai");
	}
		
	/** test comando prendi */
	@Test
	public void test_Prendi() throws Exception{
		Comando c;
		FabbricaDiComandiFisarmonica factory = new FabbricaDiComandiFisarmonica ();
		c = factory.costruisciComando("prendi");
		assertEquals (c.getNome(), "prendi");
	}

	/** test comando posa */
	@Test
	public void test_Posa() throws Exception{
		Comando c;
		FabbricaDiComandiFisarmonica factory = new FabbricaDiComandiFisarmonica ();
		c = factory.costruisciComando("posa");
		assertEquals (c.getNome(), "posa");
	}
	
	/** test comando guarda */
	@Test
	public void test_Guarda() throws Exception{
		Comando c;
		FabbricaDiComandiFisarmonica factory = new FabbricaDiComandiFisarmonica ();
		c = factory.costruisciComando("guarda");
		assertEquals (c.getNome(), "guarda");
	}
	
	/** test comando sconosciuto */
	@Test
	public void test_Sconosciuto() throws Exception{
		Comando c;
		FabbricaDiComandiFisarmonica factory = new FabbricaDiComandiFisarmonica ();
		c = factory.costruisciComando("efe");
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
	/** test comando saluta */
	@Test
	public void test_Saluta () throws Exception{
		Comando c;
		FabbricaDiComandiFisarmonica factory = new FabbricaDiComandiFisarmonica ();
		c = factory.costruisciComando("interagisci");
		assertEquals (c.getNome(), "interagisci");
	}
	/** test comando interagisci */
	@Test
	public void test_Interagisci () throws Exception{
		Comando c;
		FabbricaDiComandiFisarmonica factory = new FabbricaDiComandiFisarmonica ();
		c = factory.costruisciComando("saluta");
		assertEquals (c.getNome(), "saluta");
	}
	
	/** test comando regala */
	@Test
	public void test_Regala () throws Exception{
		Comando c;
		FabbricaDiComandiFisarmonica factory = new FabbricaDiComandiFisarmonica ();
		c = factory.costruisciComando("regala");
		assertEquals (c.getNome(), "regala");
	}
	
	/** test comando aiuto parametro */
	@Test
	public void test_AiutoParametro() throws Exception{
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
	public void test_Fine() throws Exception{
		Comando c;
		FabbricaDiComandiFisarmonica factory = new FabbricaDiComandiFisarmonica ();
		c = factory.costruisciComando("fine");
		assertEquals (c.getNome(), "fine");
	}
	
	/** test comando fine che effettivamente finisca la partita */
	@Test
	public void test_FineFinita() throws Exception{
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
	public void test_FineParametro() throws Exception{
		Comando c;
		Partita p = new Partita ();
		IO io = new IOConsole ();
		FabbricaDiComandiFisarmonica factory = new FabbricaDiComandiFisarmonica ();
		c = factory.costruisciComando("fine ciao");
		c.esegui(p, io);
		
		assertEquals (c.getParametro(), null);
	}
	
	
	
}
