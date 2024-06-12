package ambienti;

import customException.*;
import giocatore.*;
import attrezz.Attrezzo;
import java.io.*;
import java.util.*;

public class CaricatoreLabirinto {

	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze */
	private static final String STANZE_MARKER = "Stanze:";             

	/* prefisso di una singola riga contenente il nome della stanza iniziale */
	private static final String STANZA_INIZIALE_MARKER = "Inizio:";    

	/* prefisso della riga contenente il nome stanza vincente */
	private static final String STANZA_VINCENTE_MARKER = "Vincente:";  

	/* prefisso della riga contenente le specifiche degli attrezzi da collocare nel formato <nomeAttrezzo> <peso> <nomeStanza> */
	private static final String ATTREZZI_MARKER = "Attrezzi:";

	/* prefisso della riga contenente le specifiche dei collegamenti tra stanza nel formato <nomeStanzaDa> <direzione> <nomeStanzaA> */
	private static final String USCITE_MARKER = "Uscite:";

	/* prefisso della riga contenente i nomi delle stanze buie */
	private static final String STANZE_BUIE_MARKER = "Buie:";  

	/* prefisso della riga contenente i nomi delle stanze magiche */
	private static final String STANZE_MAGICHE_MARKER = "Magiche:";  

	/* prefisso della riga contenente i nomi delle stanze bloccate */
	private static final String STANZE_BLOCCATE_MARKER = "Bloccate:";

	/* prefisso della riga contenente i personaggi */
	private static final String PERSONAGGI_MARKER = "Personaggi:";

	/* prefisso della riga contenente i maghi */
	private static final String MAGHI_MARKER = "Maghi:";

	/* prefisso della riga contenente i cani */
	private static final String CANI_MARKER = "Cani:";

	/* prefisso della riga contenente le streghe */
	private static final String STREGHE_MARKER = "Streghe:";

	/*
	 *  Esempio di un possibile file di specifica di un labirinto (vedi POO-26-eccezioni-file.pdf)

		Stanze: biblioteca, N10, N11
		Inizio: N10
		Vincente: N11
		Attrezzi: martello 10 biblioteca, pinza 2 N10
		Uscite: biblioteca nord N10, biblioteca sud N11
		Buie:
		Magiche:
		Bloccate:
		Maghi:
		Cani:
		Streghe:

	 */
	private LineNumberReader reader;
	private Scanner scelta;

	private Map<String, Stanza> nome2stanza;
	private Set <Attrezzo> attrezzi;
	private List <AbstractPersonaggio> personaggi;

	private Labirinto.LabirintoBuilder labBuild;

	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;
public Labirinto b;

	public CaricatoreLabirinto(String nomeFile) throws FileNotFoundException {
		
		this.labBuild =Labirinto.newBuilder();

		this.nome2stanza = new HashMap<String,Stanza>();
		this.attrezzi = new HashSet <Attrezzo> ();
		this.personaggi = new ArrayList <AbstractPersonaggio> ();

		this.reader = new LineNumberReader(new FileReader(nomeFile));
		this.scelta = new Scanner (new FileReader(nomeFile));
	}

	public Labirinto getLab () {
		return this.labBuild.getLabirinto();
	}

	public void carica() throws FormatoFileNonValidoException {
		try {

			this.leggiECreaStanze();	
			this.leggiECreaStanzeBuie();
			this.leggiECreaStanzeMagiche();
			this.leggiECreaStanzeBloccate();
			this.leggiInizialeEvincente();

			this.leggiECollocaAttrezzi();
			this.leggiECollocaMaghi();
			this.leggiECollocaCani();
			this.leggiECollocaStreghe();

			this.leggiEImpostaUscite();

			this.creaLab();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}
	private void creaLab () {
		this.labBuild.addStanze (this.nome2stanza);
		this.labBuild.addStanzaIniziale(this.getStanzaIniziale());
		this.labBuild.addStanzaVincente(this.getStanzaVincente());
	}

	public List <AbstractPersonaggio> getPersonaggi () { return this.personaggi; }
	public Set <Attrezzo> getAttrezzi () { return this.attrezzi; }

	private String leggiRigaCheCominciaPer(String marker) throws FormatoFileNonValidoException {
		try {
			String riga = this.reader.readLine();
			check(riga.startsWith(marker),"era attesa una riga che cominciasse per " + marker);
			try {
				return riga.substring(marker.length() + 1);
			} catch (StringIndexOutOfBoundsException sioobe) {
				return null;
			}
		} catch (IOException e) {
			throw new FormatoFileNonValidoException(e.getMessage());
		}
	}

	private void leggiECreaStanze() throws FormatoFileNonValidoException  {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_MARKER);
		for(String nomeStanza : separaStringheAlleVirgole(nomiStanze)) {
			Stanza stanza = new Stanza(nomeStanza);
			this.nome2stanza.put(nomeStanza, stanza);
			//			this.labBuild.addStanza(nomeStanza);
		}
	}

	private void leggiECreaStanzeBuie () throws FormatoFileNonValidoException  {
		String stanzeBuie = this.leggiRigaCheCominciaPer(STANZE_BUIE_MARKER);
		if (stanzeBuie == null)
			return;
		for(String buia : separaStringheAlleVirgole(stanzeBuie)) {
			String nomeBuia = null;
			String luce = null; 
			try (Scanner scannerLinea = new Scanner(buia)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di una stanza buia."));
				nomeBuia = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("la luce di una stanza buia" + nomeBuia + "."));
				luce = scannerLinea.next();

			}
			this.nome2stanza.put(nomeBuia, new StanzaBuia (nomeBuia, luce));
		}
	}

	private void leggiECreaStanzeMagiche () throws FormatoFileNonValidoException  {
		String stanzeMagiche = this.leggiRigaCheCominciaPer(STANZE_MAGICHE_MARKER);
		if (stanzeMagiche == null)
			return;
		for(String magica : separaStringheAlleVirgole(stanzeMagiche)) {
			String nomeMagica = null;
			String sogliaStringa = null; 
			try (Scanner scannerLinea = new Scanner(magica)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di una stanza magica."));
				nomeMagica = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("la luce di una stanza magica" + nomeMagica + "."));
				sogliaStringa = scannerLinea.next();

			}
			int soglia;
			soglia = Integer.parseInt(sogliaStringa);
			this.nome2stanza.put(nomeMagica, new StanzaMagica (nomeMagica, soglia));
		}
	}

	private void leggiECreaStanzeBloccate () throws FormatoFileNonValidoException  {
		String stanzeBloccate = this.leggiRigaCheCominciaPer(STANZE_BLOCCATE_MARKER);
		if (stanzeBloccate == null)
			return;
		for(String bloccata : separaStringheAlleVirgole(stanzeBloccate)) {
			String nomeBloccata = null;
			Direzione dirBloccata = null;
			String chiave = null; 
			try (Scanner scannerLinea = new Scanner(bloccata)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di una stanza bloccata."));
				nomeBloccata = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("la direzione bloccata in:" + nomeBloccata + "."));
				dirBloccata = Direzione.valueOf(scannerLinea.next());
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della chiave con cui sbloccare la direzione: " + dirBloccata + " all'interno di " + nomeBloccata + "."));
				chiave = scannerLinea.next();
			}
			this.nome2stanza.put(nomeBloccata, new StanzaBloccata (nomeBloccata, dirBloccata, chiave));
		}
	}

	private void leggiECollocaMaghi () throws FormatoFileNonValidoException {
		String maghi = this.leggiRigaCheCominciaPer(MAGHI_MARKER);
		if (maghi == null)
			return;

		for(String mago : separaStringheAlleVirgole(maghi)) {
			String nomeMago = null;
			String nomeAttrezzo = null;
			String pesoAttrezzo = null;
			String nomeStanza = null;
			StringBuilder presentazione = new StringBuilder();
			try (Scanner scannerLinea = new Scanner(mago)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un mago."));
				nomeMago = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un attrezzo che dona il mago."));
				nomeAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo " + nomeAttrezzo + "."));
				pesoAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare il mago " + nomeMago + "."));
				nomeStanza = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("la presentazione del mago " + nomeMago + "."));
				while (scannerLinea.hasNext()) {
					presentazione.append(scannerLinea.next() + " ");
				}
				posizionaMago (nomeMago,nomeAttrezzo,pesoAttrezzo,nomeStanza,presentazione.toString());

			}
		}
	}

	private void posizionaMago(String nomeMago, String nomeAttrezzo, String pesoAttrezzo, String nomeStanza, String presentazione) {
		int peso;
		peso = Integer.parseInt(pesoAttrezzo);
		AbstractPersonaggio mago = new Mago(nomeMago, presentazione, new Attrezzo (nomeAttrezzo, peso));
		this.personaggi.add(mago);
		this.nome2stanza.get(nomeStanza).SetPersonaggio(mago);

	}

	private void leggiECollocaStreghe () throws FormatoFileNonValidoException {
		String streghe = this.leggiRigaCheCominciaPer(STREGHE_MARKER);
		if (streghe == null)
			return;

		for(String strega : separaStringheAlleVirgole(streghe)) {
			String nomeStrega = null;
			String nomeStanza = null;
			StringBuilder presentazione = new StringBuilder();
			try (Scanner scannerLinea = new Scanner(strega)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di una strega."));
				nomeStrega = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare la strega " + nomeStrega + "."));
				nomeStanza = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("la presentazione della strega " + nomeStrega + "."));
				while (scannerLinea.hasNext()) {
					presentazione.append(scannerLinea.next() + " ");
				}
				posizionaStrega (nomeStrega, nomeStanza, presentazione.toString());

			}
		}
	}

	private void posizionaStrega (String nomeStrega, String nomeStanza, String presentazione) {
		AbstractPersonaggio strega = new Strega (nomeStrega, presentazione);
		this.personaggi.add(strega);
		this.nome2stanza.get(nomeStanza).SetPersonaggio(strega);

	}

	private void leggiECollocaCani () throws FormatoFileNonValidoException {
		String cani = this.leggiRigaCheCominciaPer(CANI_MARKER);
		if (cani == null)
			return;

		for(String cane : separaStringheAlleVirgole(cani)) {
			String nomeCane = null;
			String nomeCibo = null;
			String nomeStanza = null;
			StringBuilder presentazione = new StringBuilder();
			try (Scanner scannerLinea = new Scanner(cane)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un cane."));
				nomeCane = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome del cibo preferito dal cane."));
				nomeCibo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare il cane " + nomeCane+ "."));
				nomeStanza = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("la presentazione del mago " + nomeCane + "."));
				while (scannerLinea.hasNext()) {
					presentazione.append(scannerLinea.next() + " ");
				}
				posizionaCane (nomeCane, nomeCibo, nomeStanza, presentazione.toString());

			}
		}
	}

	private void posizionaCane(String nomeCane, String nomeCibo, String nomeStanza, String presentazione) {
		AbstractPersonaggio cane = new Cane(nomeCane, presentazione, nomeCibo);
		this.personaggi.add(cane);
		this.nome2stanza.get(nomeStanza).SetPersonaggio(cane);

	}

	private List<String> separaStringheAlleVirgole(String string) {
		List<String> result = new LinkedList<>();
		Scanner scanner = new Scanner(string);
		scanner.useDelimiter(", ");
		try (Scanner scannerDiParole = scanner) {
			while (scannerDiParole.hasNext())
				result.add(scannerDiParole.next());
		}
		return result;
	}


	private void leggiInizialeEvincente() throws FormatoFileNonValidoException {
		String nomeStanzaIniziale = null;
		nomeStanzaIniziale = this.leggiRigaCheCominciaPer(STANZA_INIZIALE_MARKER);
		check(this.isStanzaValida(nomeStanzaIniziale), nomeStanzaIniziale +" non definita");
		String nomeStanzaVincente = this.leggiRigaCheCominciaPer(STANZA_VINCENTE_MARKER);
		check(this.isStanzaValida(nomeStanzaVincente), nomeStanzaVincente + " non definita");
		this.stanzaIniziale = this.nome2stanza.get(nomeStanzaIniziale);
		this.stanzaVincente = this.nome2stanza.get(nomeStanzaVincente);
	}

	private void leggiECollocaAttrezzi() throws FormatoFileNonValidoException {
		String specificheAttrezzi = this.leggiRigaCheCominciaPer(ATTREZZI_MARKER);
		if (specificheAttrezzi == null)
			return;

		for(String specificaAttrezzo : separaStringheAlleVirgole(specificheAttrezzi)) {
			String nomeAttrezzo = null;
			String pesoAttrezzo = null;
			String nomeStanza = null; 
			try (Scanner scannerLinea = new Scanner(specificaAttrezzo)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un attrezzo."));
				nomeAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo "+nomeAttrezzo+"."));
				pesoAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare l'attrezzo "+nomeAttrezzo+"."));
				nomeStanza = scannerLinea.next();
			}
			posaAttrezzo(nomeAttrezzo, pesoAttrezzo, nomeStanza);
		}
	}

	private void posaAttrezzo(String nomeAttrezzo, String pesoAttrezzo, String nomeStanza) throws FormatoFileNonValidoException {
		int peso;
		try {
			peso = Integer.parseInt(pesoAttrezzo);
			Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
			check(isStanzaValida(nomeStanza),"Attrezzo "+ nomeAttrezzo+" non collocabile: stanza " +nomeStanza+" inesistente");
			this.attrezzi.add(attrezzo);
			this.nome2stanza.get(nomeStanza).addAttrezzo(attrezzo);
		}
		catch (NumberFormatException e) {
			check(false, "Peso attrezzo "+nomeAttrezzo+" non valido");
		}
	}


	private boolean isStanzaValida(String nomeStanza) {
		return this.nome2stanza.containsKey(nomeStanza);
	}

	private void leggiEImpostaUscite() throws FormatoFileNonValidoException {
		String specificheUscite = this.leggiRigaCheCominciaPer(USCITE_MARKER);
		try (Scanner scannerDiLinea = new Scanner(specificheUscite)) {			

			while (scannerDiLinea.hasNext()) {
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("le uscite di una stanza."));
				String stanzaPartenza = scannerDiLinea.next();
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la direzione di una uscita della stanza "+stanzaPartenza));
				Direzione dir = Direzione.valueOf(scannerDiLinea.next());
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la destinazione di una uscita della stanza "+stanzaPartenza+" nella direzione "+dir));
				String stanzaDestinazione = scannerDiLinea.next();

				impostaUscita(stanzaPartenza, dir, stanzaDestinazione);
			}
		} 
	}


	private String msgTerminazionePrecoce(String msg) {
		return "Terminazione precoce del file prima di leggere "+msg;
	}

	private void impostaUscita(String stanzaDa, Direzione dir, String nomeA) throws FormatoFileNonValidoException {
		check(isStanzaValida(stanzaDa),"Stanza di partenza sconosciuta "+dir);
		if (nomeA.contains(","))
			nomeA = nomeA.substring(0, nomeA.length()-1);
		check(isStanzaValida(nomeA),"Stanza di destinazione sconosciuta "+ dir);
		Stanza partenzaDa = this.nome2stanza.get(stanzaDa);
		Stanza arrivoA = this.nome2stanza.get(nomeA);
		partenzaDa.impostaStanzaAdiacente(dir, arrivoA);
		arrivoA.impostaStanzaAdiacente(dir.opposta(), partenzaDa);

		//		this.labBuild.addAdiacenza(stanzaDa, nomeA, dir);
		//		this.labBuild.addAdiacenza(nomeA, stanzaDa, dirOpposta(dir));
	}

//	private String dirOpposta (String dir) {
//		if (dir.equals("nord"))
//			return "sud";
//		else if (dir.equals("sud"))
//			return "nord";
//		else if (dir.equals("est"))
//			return "ovest";
//		else if (dir.equals("ovest"))
//			return "est";
//		return null;
//	}

	final private void check(boolean condizioneCheDeveEsseraVera, String messaggioErrore) throws FormatoFileNonValidoException {
		if (!condizioneCheDeveEsseraVera)
			throw new FormatoFileNonValidoException("Formato file non valido [" + this.reader.getLineNumber() + "] "+messaggioErrore);		
	}

	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}

	public Stanza getStanzaVincente() {
		return this.stanzaVincente;
	}
}