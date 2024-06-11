package diadia;
import ambienti.*;
import attrezz.*;
import comandi.Comando;
import comandi.FabbricaDiComandiFisarmonica;
import comandiTest.*;
import customException.FormatoFileNonValidoException;
import giocatore.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;



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
	
	private Partita partita;
	private IO console;
	static public Properties prop;
	
	public DiaDia(IO io) {
		this.partita = new Partita();
		this.console = io;
	}
	
	/** costruttore con labirinto personalizzato*/
	public DiaDia(IO io, Labirinto lab) {
		this.partita = new Partita(lab);
		this.console = io;
		prop = this.leggiProp();
		
	}

	private Properties leggiProp() {
		FileReader fileProp = null;
		try {
			fileProp = new FileReader ("diadia.properties");
		} catch (FileNotFoundException e) {
			System.out.println("bro non va");
		}
		Properties prop = new Properties();
		try {
			prop.load(fileProp);
		} catch (IOException e) {
			System.out.println("che succede fra");
		}
		return null;
	}

	public void gioca() throws Exception {
		String istruzione; 
		 this.console.mostraMessaggio(MESSAGGIO_BENVENUTO);
		do		
			istruzione = this.console.leggiRiga();
		while (!processaIstruzione(istruzione));
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 * @throws Exception 
	 */
	private boolean processaIstruzione(String istruzione) throws Exception {
		Comando comandoDaEseguire;
		FabbricaDiComandiFisarmonica factory = new FabbricaDiComandiFisarmonica ();
		
		comandoDaEseguire = factory.costruisciComando(istruzione);
		comandoDaEseguire.esegui(this.partita, this.console);
		
		if (this.partita.vinta())
			this.console.mostraMessaggio("Hai vinto!");
		
		if (!this.partita.giocatoreIsVivo())
			this.console.mostraMessaggio("Hai esaurito i CFU");
		
		return this.partita.isFinita();	
	}   

	public static void main(String[] argc) throws Exception {
		IO io= new IOConsole();		
		Labirinto lab = Labirinto.newLab("Lab1.txt");
		lab = lab.getLab();
		try {
			DiaDia gioco = new DiaDia(io, lab);
			gioco.gioca();
			} finally { io.chiudiScanner(); }
	}
}