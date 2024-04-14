package diadia;
import ambienti.*;
import attrezz.*;
import giocatore.*;

import java.util.Scanner;

import ambienti.Stanza;
import attrezz.Attrezzo;
import giocatore.Borsa;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	
	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";
	
	public int iniziata = 0;
	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa"};

	private Partita partita;

	public DiaDia() {
		this.partita = new Partita();
		
	}

	public void gioca(IOConsole console) {
		String istruzione; 
		console.mostraMessaggio(MESSAGGIO_BENVENUTO);
		do		
			istruzione = console.leggiRiga();
		while (!processaIstruzione(istruzione, console));
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione, IOConsole console) {
		Comando comandoDaEseguire = new Comando(istruzione);
		
		if (comandoDaEseguire.getNome() == (null)) { 
			console.mostraMessaggio("Comando sconosciuto");
		}
		else {
			if (comandoDaEseguire.getNome().equals("fine")) {
				this.fine(console); 
				return true;
			} else if (comandoDaEseguire.getNome().equals("vai"))
				this.vai(comandoDaEseguire.getParametro(), console);
			else if (comandoDaEseguire.getNome().equals("aiuto"))
				this.aiuto(console);
			else if (comandoDaEseguire.getNome().equals("prendi") && this.iniziata != 0 && comandoDaEseguire.getParametro() != null)
				this.prendi(comandoDaEseguire.getParametro(), console);
			else if (comandoDaEseguire.getNome().equals("borsa"))
				this.stampaBorsa(console);
			else if (comandoDaEseguire.getNome().equals("posa") && this.iniziata != 0 && comandoDaEseguire.getParametro() != null)
				this.posa(comandoDaEseguire.getParametro(), console);
			else
				console.mostraMessaggio("Comando sconosciuto");
			}
		if (this.partita.vinta()) {
			console.mostraMessaggio("Hai vinto!");
			return true;
		} else
			return false;
	}   

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto(IOConsole console) {
		for(int i=0; i< elencoComandi.length; i++) 
			console.mostraMessaggio(elencoComandi[i]+" ");
		console.mostraMessaggio("");
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione, IOConsole console) {
		this.iniziata++;
		if(direzione==null)
			console.mostraMessaggio("Dove vuoi andare ?");
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.labirinto.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			console.mostraMessaggio("Direzione inesistente");
		else {
			this.partita.labirinto.setStanzaCorrente(prossimaStanza);
			int cfu = this.partita.giocatore.getCfu();
			this.partita.giocatore.setCfu(cfu--);
		}
		console.mostraMessaggio(partita.labirinto.getStanzaCorrente().getDescrizione());
	}
	
	private void stampaBorsa (IOConsole console) {
		
		console.mostraMessaggio(this.partita.giocatore.getBorsa().toString());
	}
	
	
	/*
	 * comando "prendi"
	 */
	private void prendi (String nomeAttrezzo, IOConsole console) {
		if (this.partita.labirinto.getStanzaCorrente().hasAttrezzo(nomeAttrezzo)) {
			
			Attrezzo a = this.partita.labirinto.getStanzaCorrente().getAttrezzo(nomeAttrezzo);

			if (this.partita.labirinto.getStanzaCorrente().removeAttrezzo(a))
				if (this.partita.giocatore.getBorsa().addAttrezzo(a))
					console.mostraMessaggio ("hai preso " + nomeAttrezzo);
				else
					this.partita.labirinto.getStanzaCorrente().addAttrezzo(a);
		}		
		else {
			console.mostraMessaggio ("impossibile prendere l'oggetto");
		}	
	}
	
	/*
	 * comando "posa"
	 */
	private void posa (String nomeAttrezzo, IOConsole console) {
		if (this.partita.giocatore.getBorsa().hasAttrezzo(nomeAttrezzo)) {
			Attrezzo a = this.partita.giocatore.getBorsa().getAttrezzo(nomeAttrezzo);
				
			if (this.partita.labirinto.getStanzaCorrente().addAttrezzo(a)) {
				a = this.partita.giocatore.getBorsa().removeAttrezzo(nomeAttrezzo);
				
				console.mostraMessaggio ("hai posato " + nomeAttrezzo + " nella stanza");
			}
			else
				this.partita.giocatore.getBorsa().addAttrezzo(a);	
		}
		else
			console.mostraMessaggio ("impossibile posare attrezzo nella stanza");
	}
	
	/**
	 * Comando "Fine".
	 */
	private void fine(IOConsole console) {
		console.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
	}

	public static void main(String[] argc) {
		IOConsole console = new IOConsole();
		DiaDia gioco = new DiaDia();
		gioco.gioca(console);
	}
}