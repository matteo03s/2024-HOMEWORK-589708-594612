package diadiaTest;

import static org.junit.Assert.*;
import ambienti.*;
import diadia.Partita;

import org.junit.Before;
import org.junit.Test;

/*
 * Test per la classe Partita
 * @see Partita
 */

public class PartitaTest {
	Partita partita = new Partita ();


	//stanza vincente = partita vinta
	@Test
	public void test_Vinta_Giusta() {
		Stanza vincente = this.partita.getLabirinto().getStanzaVincente();
		this.partita.getLabirinto().setStanzaCorrente(vincente);
		assertTrue (this.partita.vinta());
	}
	
	//stanza non vincente = partita non vinta
	@Test
	public void test_Vinta_Sbagliata () {
		Stanza nonVincente = new Stanza ("perdente");
		this.partita.getLabirinto().setStanzaCorrente(nonVincente);
		assertFalse (this.partita.vinta());
	}

	//partita vinta = partita finita
	@Test
	public void test_Vinta_Finita() {
		Stanza vincente = this.partita.getLabirinto().getStanzaVincente();
		this.partita.getLabirinto().setStanzaCorrente(vincente);
		assertTrue (this.partita.isFinita());
	}

	//zero cfu = partita finita
	@Test
	public void test_CFU_Finita() {		
		this.partita.getGiocatore().setCfu(0);
		assertTrue (this.partita.isFinita());
	}
	
	//non zero cfu = non partita finita
	@Test
	public void test_CFU_NonFinita() {
		this.partita.getGiocatore().setCfu(10);
		assertFalse (this.partita.isFinita());
	}
	
	//partita finita = partita finita
	@Test
	public void test_Finita_Finita() {
		this.partita.setFinita();
		assertTrue (this.partita.isFinita());

	}
	
	
		

}
