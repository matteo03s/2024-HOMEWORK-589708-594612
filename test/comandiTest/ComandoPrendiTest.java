package comandiTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ambienti.Labirinto;
import ambienti.Stanza;
import attrezz.*;
import comandi.Comando;
import comandi.ComandoPrendi;
import diadia.IO;
import diadia.IOConsole;
import diadia.Partita;
import diadiaTest.*;

public class ComandoPrendiTest {

	Partita p;
	Labirinto monolocale;

	@Before
	public void SetUp () {
		p = new Partita ();
		monolocale = Labirinto.newBuilder()
				.addStanzaIniziale("start")
				.addAttrezzo("pala", 2)
				.getLabirinto();
		
	}
	
	/** prendi un oggetto consentito e conferma che lo prenda davvero */
	@Test
	public void test_PrendiSuccesso() {
		
		p = new Partita (monolocale);
		p.setIniziata();
		IO io = new IOConsole();
		
		Attrezzo oro = new Attrezzo ("oro", 2);
		Stanza corrente = p.getLabirinto().getStanzaCorrente();
		corrente.addAttrezzo(oro);
		
		ComandoPrendi prendiBene = new ComandoPrendi ("oro");
		prendiBene.esegui(p, io);
		assertEquals (oro.getNome(), p.getGiocatore().getBorsa().getAttrezzo("oro").getNome());
		
	}
	
	/** prendi un oggetto inesistente e conferma che non lo prenda*/
	@Test
	public void test_PrendiInesistente() {
		
		p = new Partita (monolocale);
		p.setIniziata();
		IO io = new IOConsole();
		
		Attrezzo oro = new Attrezzo ("oro", 2);
		Stanza corrente = p.getLabirinto().getStanzaCorrente();
		corrente.addAttrezzo(oro);
		
		ComandoPrendi prendiBene = new ComandoPrendi ("argento");
		prendiBene.esegui(p, io);
		assertFalse (p.getGiocatore().getBorsa().hasAttrezzo("argento"));	
	}
	
	/** prova a prendere quando è troppo presto e conferma che non prenda nulla */
	@Test
	public void test_PrendiTroppoPresto() {
		
		p = new Partita (monolocale);
		IO io = new IOConsole();
		
		Attrezzo oro = new Attrezzo ("oro", 2);
		Stanza corrente = p.getLabirinto().getStanzaCorrente();
		corrente.addAttrezzo(oro);
		
		ComandoPrendi prendiBene = new ComandoPrendi ("oro");
		prendiBene.esegui(p, io);
		
		assertFalse (p.getGiocatore().getBorsa().hasAttrezzo("oro"));
	}

	/** conferma che il nome ritornato è prendi */
	@Test
	public void test_GetNome () {
		Comando c = new ComandoPrendi ();
		assertEquals (c.getNome (), "prendi");	
	}
	
	/** conferma che il parametro restituito è lo stesso di quello passato col costruttore*/
	@Test
	public void test_GetParametroCostruttore () {
		Comando c = new ComandoPrendi ("oro");
		assertEquals (c.getParametro(), "oro");
	}
	
	/** conferma che il parametro restituito è lo stesso di quello passato col setter*/
	@Test
	public void test_GetParametroSettato () {
		Comando c = new ComandoPrendi ();
		c.setParametro("oro");
		assertEquals (c.getParametro(), "oro");
	}

}
