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

	@Before
	public void SetUp () {
		Partita p = new Partita ();
		Stanza corrente = p.getLabirinto().getStanzaCorrente();
		
	}
	
	/** vai in una direzione consentita e conferma che la stanza è giusta */
	@Test
	public void test_DirezioneStanza() {
		Partita p = new Partita ();
		IO io = new IOConsole();
		
		Stanza prossimaAspettata = p.getLabirinto().getStanzaCorrente().getStanzaAdiacente("sud");
		
		ComandoVai sud = new ComandoVai ("sud");
		sud.esegui(p, io);
		Stanza prossimaEffettiva = p.getLabirinto().getStanzaCorrente();
				
		assertTrue (prossimaEffettiva.getDescrizione().equals(prossimaAspettata.getDescrizione()));
	}
	
	/** vai in una direzione vincente e conferma che si vinca */
	@Test
	public void test_DirezioneVincente() {
		
		Partita p = new Partita ();
		IO io = new IOConsole();
		
		ComandoVai nord = new ComandoVai ("nord");
		nord.esegui(p, io);
		
		assertTrue (p.vinta());	
	}
	
	/** vai in una direzione non consentita e conferma che rimanga nella stessa stanza */
	@Test
	public void test_DirezioneInesistente() {
		
		Partita p = new Partita ();
		IO io = new IOConsole();
		
		Stanza stanzaChiusa = new Stanza("chiusa");
		p.getLabirinto().setStanzaCorrente(stanzaChiusa);
		
		ComandoVai nord = new ComandoVai ("nord");
		nord.esegui(p, io);
		
		Stanza corrente = p.getLabirinto().getStanzaCorrente();

		assertEquals (corrente.getNome(), stanzaChiusa.getNome());
	}

	/** conferma che il nome ritornato è vai */
	@Test
	public void test_GetNome () {
		Comando c = new ComandoVai ();
		assertEquals (c.getNome (), "vai");	
	}
	
	/** conferma che il parametro restituito è lo stesso di quello passato col costruttore*/
	@Test
	public void test_GetParametroCostruttore () {
		Comando c = new ComandoVai ("nord");
		assertEquals (c.getParametro(), "nord");
	}
	
	/** conferma che il parametro restituito è lo stesso di quello passato col setter*/
	@Test
	public void test_GetParametroSettato () {
		Comando c = new ComandoVai ();
		c.setParametro("nord");
		assertEquals (c.getParametro(), "nord");
	}
}
