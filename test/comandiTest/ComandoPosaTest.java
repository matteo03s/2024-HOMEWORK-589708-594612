package comandiTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ambienti.Labirinto;
import ambienti.Stanza;
import attrezz.*;
import comandi.Comando;
import comandi.ComandoPosa;
import diadia.IO;
import diadia.IOConsole;
import diadia.Partita;
import diadiaTest.*;

public class ComandoPosaTest {

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
	
	/** posa un oggetto consentito e conferma che lo posi davvero */
	@Test
	public void test_PosaSuccesso() {
		
		p = new Partita (monolocale);
		p.setIniziata();
		IO io = new IOConsole();
		
		Attrezzo oro = new Attrezzo ("oro", 2);
		p.getGiocatore().getBorsa().addAttrezzo(oro);
		
		ComandoPosa posaBene = new ComandoPosa ("oro");
		posaBene.esegui(p, io);
		assertEquals (oro.getNome(), p.getLabirinto().getStanzaCorrente().getAttrezzo("oro").getNome());
		
	}
	
	/** posa un oggetto inesistente e conferma che non lo posi*/
	@Test
	public void test_PosaInesistente() {
		
		p = new Partita (monolocale);
		p.setIniziata();
		IO io = new IOConsole();
		
		Attrezzo oro = new Attrezzo ("oro", 2);
		p.getGiocatore().getBorsa().addAttrezzo(oro);
		
		ComandoPosa posaBene = new ComandoPosa ("argento");
		posaBene.esegui(p, io);
		assertFalse (p.getLabirinto().getStanzaCorrente().hasAttrezzo("argento"));	
	}
	
	/** prova a posare quando è troppo presto e conferma che non posi nulla */
	@Test
	public void test_PosaTroppoPresto() {
		
		p = new Partita (monolocale);
		IO io = new IOConsole();
		
		Attrezzo oro = new Attrezzo ("oro", 2);
		p.getGiocatore().getBorsa().addAttrezzo(oro);
		
		ComandoPosa posaBene = new ComandoPosa ("oro");
		posaBene.esegui(p, io);
		
		assertFalse (p.getLabirinto().getStanzaCorrente().hasAttrezzo("oro"));
	}

	/** conferma che il nome ritornato è posa */
	@Test
	public void test_GetNome () {
		Comando c = new ComandoPosa ();
		assertEquals (c.getNome (), "posa");	
	}
	
	/** conferma che il parametro restituito è lo stesso di quello passato col costruttore */
	@Test
	public void test_GetParametroCostruttore () {
		Comando c = new ComandoPosa ("oro");
		assertEquals (c.getParametro(), "oro");
	}
	
	/** conferma che il parametro restituito è lo stesso di quello passato col setter */
	@Test
	public void test_GetParametroSettato () {
		Comando c = new ComandoPosa ();
		c.setParametro("oro");
		assertEquals (c.getParametro(), "oro");
	}

}
