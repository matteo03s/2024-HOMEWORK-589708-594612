/**
 * Una classe che serve ad eseguire il comando vai.
 *
 * @author  M.Saravo-L.Mattioli
 * @see Comando
 * @see IoConsole
 *
 */
package comandiTest;

import ambienti.*;
import comandi.Comando;
import comandi.ComandoVai;
import diadia.IO;
import diadia.IOConsole;
import diadia.Partita;
import diadiaTest.*;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ComandoVaiTest {
	Partita p;
	Labirinto monolocale;
	Labirinto bilocale;
	Labirinto trilocale;
	@Before
	public void SetUp () {
		p = new Partita ();
		monolocale = Labirinto.newBuilder()
				.addStanzaIniziale("start")
				.addAttrezzo("pala", 2)
				.getLabirinto();
		bilocale = Labirinto.newBuilder()
				.addStanzaIniziale("start")
				.addAttrezzo("pala", 2)
				.addStanzaVincente("biblioteca")
				.addAdiacenza("start", "biblioteca", Direzione.nord)
				.getLabirinto();
		trilocale = Labirinto.newBuilder()
				.addStanzaIniziale("start")
				.addAttrezzo("pala", 2)
				.addStanzaVincente("biblioteca")
				.addAdiacenza("start", "biblioteca", Direzione.nord)
				.addStanza("terza")
				.addAdiacenza("start", "terza", Direzione.est)
				.getLabirinto();
		
	}
	
	/** conferma che il nome ritornato è vai */
	@Test
	public void test_getNome () {
		Comando c = new ComandoVai ();
		assertEquals (c.getNome (), "vai");	
	}
	
	/** conferma che il parametro restituito è lo stesso di quello passato col costruttore*/
	@Test
	public void test_GetParametroCostruttore () {
		Comando c = new ComandoVai (Direzione.nord);
		assertEquals (c.getParametro(), Direzione.nord.toString());
	}
	
	/** conferma che il parametro restituito è lo stesso di quello passato col setter*/
	@Test
	public void test_GetParametroSettato () {
		Comando c = new ComandoVai ();
		c.setParametro("nord");
		assertEquals (c.getParametro(), "nord");
	}
	/** vai in una direzione consentita e conferma che la stanza è giusta */
	@Test
	public void test_DirezioneStanza() {
		
		p = new Partita (trilocale);
		IO io = new IOConsole();
		
		Stanza prossimaAspettata = p.getLabirinto().getStanzaCorrente().getStanzaAdiacente(Direzione.est);
		
		ComandoVai est = new ComandoVai (Direzione.est);
		est.esegui(p, io);
		Stanza prossimaEffettiva = p.getLabirinto().getStanzaCorrente();
				
		assertTrue (prossimaEffettiva.getDescrizione().equals(prossimaAspettata.getDescrizione()));
	}
	
	/** vai in una direzione vincente e conferma che si vinca */
	@Test
	public void test_DirezioneVincente() {
		p = new Partita (bilocale);
		IO io = new IOConsole();	
		ComandoVai nord = new ComandoVai (Direzione.nord);
		nord.esegui(p, io);	
		assertTrue (p.vinta());	
	}
	
	/** vai in una direzione non consentita e conferma che rimanga nella stessa stanza */
	@Test
	public void test_DirezioneInesistente() {
		
		p = new Partita (monolocale);
		IO io = new IOConsole();
		
		ComandoVai nord = new ComandoVai (Direzione.nord);
		nord.esegui(p, io);
		
		Stanza corrente = p.getLabirinto().getStanzaCorrente();
		assertEquals (corrente.getNome(), monolocale.getStanzaCorrente().getNome());
	}

	
	
}
