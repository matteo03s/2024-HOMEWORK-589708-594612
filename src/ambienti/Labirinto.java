package ambienti;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import attrezz.Attrezzo;
import customException.FormatoFileNonValidoException;
import giocatore.AbstractPersonaggio;
import giocatore.Cane;
import giocatore.Mago;
import giocatore.Strega;

/**
 * Classe Labirinto - un labirinto in un gioco di ruolo.
 * il labirinto gestisce le stanze.
 * il labirinto è gestito dalla partita
 *
 * @author  L.Mattioli-M.Saravo
 * @see Stanza
 * @see Partita
 * 
 */

public class Labirinto {
	
	private Stanza stanzaIniziale;	
	private Stanza stanzaCorrente;
	private Stanza stanzaVincente;
	private Set <Attrezzo> attrezzi;
	public List <AbstractPersonaggio> pers;
	CaricatoreLabirinto c;
	
	private Labirinto () {
		this.attrezzi = new HashSet <Attrezzo> ();
		this.pers = new ArrayList <AbstractPersonaggio> ();
	}
	
	private Labirinto (String nomeFile) throws FormatoFileNonValidoException {
		try {
			this.c = new CaricatoreLabirinto(nomeFile);
		} catch (FileNotFoundException e) {
			throw new FormatoFileNonValidoException("il file specificato non esiste");
		}
		this.attrezzi = new HashSet <Attrezzo> ();
		this.pers = new ArrayList <AbstractPersonaggio> ();
		this.c.carica();
		this.attrezzi.addAll(this.c.getAttrezzi());
		this.pers.addAll(this.c.getPersonaggi());

	}
	
	public Labirinto getLab () {
		return this.c.getLab();
	}
	public static Labirinto newLab () {
		return new Labirinto();
	}
	public static Labirinto newLab (String nomeFile) throws FormatoFileNonValidoException {
		return new Labirinto(nomeFile);
	}
	
//	public Labirinto getLab (String nomeFile) throws FormatoFileNonValidoException {
//		try {
//			this.c = new CaricatoreLabirinto(nomeFile);
//		} catch (FileNotFoundException e) {
//			throw new FormatoFileNonValidoException("il file specificato non esiste");
//		}
//		this.c.carica();
//		return this.c.getLab();
//	}

    /** serve per impostare novi attrezzi all'interno del labirinto */
    public boolean setNuoviAttrezzi (Attrezzo a) {
    	return this.attrezzi.add(a);
    }
    
    /** ritorna l'intero set di attrezzi nel labirinto */
    public Set <Attrezzo> getAttrezzi () {
    	return this.attrezzi;
    }
    
    /** vede se un attrezzo è presente nel labirinto */ 
    public boolean contieneAttrezzo (Attrezzo a) {
    	return this.attrezzi.contains(a);
    }
    
    
    /** restituisce la stanza iniziale del labirinto
     * @return stanza iniziale
     * */
    public Stanza getStanzaIniziale () {
    	return this.stanzaIniziale;
    }
    
    /** ottiene la stanza vincente del labirinto
     * @return stanza vincente
     * */
    public Stanza getStanzaVincente() {
		return this.stanzaVincente;
	}

    /** setta la stanza corrente
     * @param imposta la stanza iniziale
     * */
	public void setStanzaIniziale (Stanza stanzaIniziale) {
		this.stanzaIniziale = stanzaIniziale;
	}
	
	/** setta la stanza iniziale
     * @param imposta la stanza corrente
     * */
	public void setStanzaCorrente (Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;
	}
	
	/** setta la stanza vincente
     * @param imposta la stanza vincente
     * */
	public void setStanzaVincente (Stanza stanzaVincente) {
		this.stanzaVincente = stanzaVincente;
	}
	
	/** ottiene la stanza corrente 
	 *@return stanza corrente
	 **/
	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}
	
	/** serve per aggiornare nuovi personaggi all'interno del labirinto */
    public boolean setNuoviPersonaggi (AbstractPersonaggio p) {
    	return this.pers.add(p);
    }
	/** ritorna la lista dei personaggi
	 * @return List personaggi 
	 * */
	public List <AbstractPersonaggio> getPersonaggi () {
		return this.pers;
	}
	// metodo per accedere al costruttore privato  di labirinto
			public static LabirintoBuilder newBuilder() {
				return new Labirinto().new LabirintoBuilder();
			}
//	public static LabirintoBuilder 
//	newBuilder(){
//		return new Labirinto().new LabirintoBuilder();
//	}
	//classe annidata
	public class LabirintoBuilder {
		
		private Map <String, Stanza> stanzeLabirinto = new HashMap <> ();
		private Labirinto lab = new Labirinto ();
		private Stanza ultimaStanza;

		public LabirintoBuilder addStanze (Map <String, Stanza> stanze) {
			Iterator <Stanza> iter = stanze.values().iterator();
			Stanza controllo = null;
			while (iter.hasNext()) {
				controllo = iter.next();
				if (controllo.getClass().equals(StanzaBuia.class))
					this.setStanzaBuia(controllo.getNome(), controllo.getChiave());
				else if (controllo.getClass().equals(StanzaMagica.class))
					this.setStanzaMagica(controllo.getNome(), controllo.getSoglia());
				else if (controllo.getClass().equals(StanzaBloccata.class))
					this.setStanzaBloccata(controllo.getNome(), controllo.getDirBloccata(), controllo.getChiave());
			}
			this.stanzeLabirinto = stanze;
			return this;
		}

		public LabirintoBuilder addStanzaIniziale (Stanza stanza) {
			this.lab.setStanzaIniziale(stanza);		
			this.lab.setStanzaCorrente (stanza);
			return this;
		}
		public LabirintoBuilder addStanzaVincente (Stanza stanza) {
			this.lab.setStanzaVincente(stanza);		
			return this;
		}
		
		/** metodo che aggiunge la stanza iniziale del labirinto
		 * @param nome della stanza da inserire */
		public LabirintoBuilder addStanzaIniziale (String nomeStanza) {
			Stanza creata = new Stanza (nomeStanza);
			stanzeLabirinto.put(nomeStanza, creata);
			this.lab.setStanzaIniziale (creata);
			this.lab.setStanzaCorrente (creata);
			this.ultimaStanza = creata;
			return this;
		}

		/** metodo che aggiunge la stanza vincente del labirinto 
		 * @param nome della stanza da inserire */
		public LabirintoBuilder addStanzaVincente (String nomeStanza) {
			Stanza creata = new Stanza (nomeStanza);
			stanzeLabirinto.put(nomeStanza, creata);
			this.lab.setStanzaVincente(creata);
			this.ultimaStanza = creata;
			return this;
		}

		/** metodo che aggiunge una stanza qualunque del labirinto
		 * @param nome della stanza da inserire */
		public LabirintoBuilder addStanza (String nomeStanza) {
			Stanza creata = new Stanza (nomeStanza);
			stanzeLabirinto.put(nomeStanza, creata);
			this.ultimaStanza = creata;
			return this;
		}
		
		/** metodo che aggiunge una stanza magica nel labirinto 
		 * @param nome della stanza magica da inserire */
		public LabirintoBuilder addStanzaMagica (String nomeStanzaMagica) {
			Stanza creata = new StanzaMagica (nomeStanzaMagica);
			stanzeLabirinto.put(nomeStanzaMagica, creata);
			this.ultimaStanza = creata;
			return this;
		}
		
		/** metodo che aggiunge una stanza magica nel labirinto 
		 * @param nomeStanzaMagica nome della stanza magica da inserire
		 * @param sogliaMagica int */
		public LabirintoBuilder addStanzaMagica (String nomeStanzaMagica, int sogliaMagica) {
			Stanza creata = new StanzaMagica (nomeStanzaMagica, sogliaMagica);
			stanzeLabirinto.put(nomeStanzaMagica, creata);
			this.ultimaStanza = creata;
			return this;
		}
		
		public LabirintoBuilder setStanzaMagica(String nomeStanzaMagica, int soglia) {
			StanzaMagica creata = new StanzaMagica (nomeStanzaMagica, soglia);
			stanzeLabirinto.put(nomeStanzaMagica, creata);
			return this;
		}
		
		/** metodo che aggiunge una stanza bloccata nel labirinto
		 * @param nomeStanzaBloccata nome della stanza bloccata
		 * @param direzioneBloccata String
		 * @param nomeChiave String*/
		public LabirintoBuilder addStanzaBloccata (String nomeStanzaBloccata, Direzione direzioneBloccata, String nomeChiave) {
			StanzaBloccata creata = new StanzaBloccata (nomeStanzaBloccata, direzioneBloccata, nomeChiave);
			stanzeLabirinto.put(nomeStanzaBloccata, creata);
			this.ultimaStanza = creata;
			return this;
		}
		
		private LabirintoBuilder setStanzaBloccata(String nomeStanzaBloccata, Direzione dirBloccata, String chiave) {
			StanzaBloccata creata = new StanzaBloccata (nomeStanzaBloccata, dirBloccata, chiave);
			stanzeLabirinto.put(nomeStanzaBloccata, creata);
			return this;
		}
		
		/** metodo che aggiunge una stanza buia nel labirinto
		 * @param nomeStanzaBuia nome della stanza buia
		 * @param nomeLuce String*/
		public LabirintoBuilder addStanzaBuia (String nomeStanzaBuia, String nomeLuce) {
			StanzaBuia creata = new StanzaBuia (nomeStanzaBuia, nomeLuce);
			stanzeLabirinto.put(nomeStanzaBuia, creata);
			this.ultimaStanza = creata;
			return this;
		}
		
		/** metodo che imposta una stanza esistente come buia nel labirinto
		 * @param nomeStanzaBuia nome della stanza buia
		 * @param nomeLuce String*/
		public LabirintoBuilder setStanzaBuia (String nomeStanzaBuia, String nomeLuce) {
			StanzaBuia creata = new StanzaBuia (nomeStanzaBuia, nomeLuce);
			stanzeLabirinto.put(nomeStanzaBuia, creata);
			return this;
		}


		/** metodo che aggiunge un attrezzo all'ultima stanza creata
		 * @param attrezzo da aggiungere */
		public LabirintoBuilder addAttrezzo (Attrezzo a) {
			if (this.lab.setNuoviAttrezzi(a))
				this.ultimaStanza.addAttrezzo(a);
			return this;
		}


		/** metodo che aggiunge un attrezzo all'ultima stanza creata
		 * @param nomeAttrezzo String nome attrezzo da aggiungere
		 * @param pesoAttrezzo int peso attrezzo da aggiungere */
		public LabirintoBuilder addAttrezzo (String nomeAttrezzo, int pesoAttrezzo) {
			Attrezzo a = new Attrezzo (nomeAttrezzo, pesoAttrezzo);
			if (this.lab.setNuoviAttrezzi(a))
				this.ultimaStanza.addAttrezzo(a);	
			return this;
		}
		
		/** metodo che aggiunge un personaggio all'ultima stanza creata
		 * @param AbstractPersonaggio personaggio da aggiungere */
		public LabirintoBuilder setPersonaggio (AbstractPersonaggio p) {
			this.ultimaStanza.SetPersonaggio(p);
			return this;
		}
		
		/** metodo che aggiunge un mago all'ultima stanza creata
		 * @param String nome
		 * @param String presentazione
		 * @param Attrezzo dono
		 * */
		public LabirintoBuilder setMago (String nome, String presentazione, Attrezzo dono) {
			AbstractPersonaggio p = new Mago (nome, presentazione, dono);
			this.ultimaStanza.SetPersonaggio(p);
			return this;
		}
		
		/** metodo che aggiunge una strega all'ultima stanza creata
		 * @param String nome
		 * @param String presentazione
		 * */
		public LabirintoBuilder setStrega (String nome, String presentazione) {
			AbstractPersonaggio p = new Strega (nome, presentazione);
			this.ultimaStanza.SetPersonaggio(p);
			return this;
		}
		
		/** metodo che aggiunge un mago all'ultima stanza creata
		 * @param String nome
		 * @param String presentazione
		 * @param String nome dono
		 * @param int peso dono
		 * */
		public LabirintoBuilder setMago (String nome, String presentazione, String nomeDono, int pesoDono) {
			Attrezzo dono = new Attrezzo (nomeDono, pesoDono);
			AbstractPersonaggio p = new Mago (nome, presentazione, dono);
			this.ultimaStanza.SetPersonaggio(p);
			return this;
		}
		
		/** metodo che aggiunge un cane all'ultima stanza creata
		 * @param String nome
		 * @param String presentazione
		 * */
		public LabirintoBuilder setCane (String nome, String presentazione) {
			AbstractPersonaggio p = new Cane (nome, presentazione);
			this.ultimaStanza.SetPersonaggio(p);
			return this;
		}


		/** metodo che imposta un'adiacenza tra due stanza passate 
		 * @param nomeStanzaPartenza String
		 * @param nomeStanzaArrivo String
		 * @param direzione String */
		public LabirintoBuilder addAdiacenza (String nomeStanzaPartenza, String nomeStanzaArrivo, Direzione direzione) {
			if (direzione.equals(Direzione.nord) || direzione.equals(Direzione.sud)
					|| direzione.equals(Direzione.est) || direzione.equals(Direzione.ovest)) {
				this.stanzeLabirinto.get(nomeStanzaPartenza).impostaStanzaAdiacente
				(direzione, this.stanzeLabirinto.get(nomeStanzaArrivo));
				this.stanzeLabirinto.get(nomeStanzaArrivo).impostaStanzaAdiacente
				(direzione.opposta(), this.stanzeLabirinto.get(nomeStanzaPartenza));
				return this;
			}
			return this;
		}

		/** metodo che restituisce la lista di stanze del labirinto 
		 * @return listaStanze Map */
		public Map <String, Stanza> getListaStanze () {
			return this.stanzeLabirinto;
		}

		/** metodo che restituisce il labirinto creato 
		 * @return oggetto di tipo labirinto */
		public Labirinto getLabirinto () {
			return this.lab;
		}
	}
	
}
