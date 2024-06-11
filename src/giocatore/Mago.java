package giocatore;

import diadia.Partita;
import attrezz.*;
import ambienti.*;

public class Mago extends AbstractPersonaggio {
	
	
	private static final String MESSAGGIO_DONO = "Sei un vero simpaticone, " +
			"con una mia magica azione, troverai un nuovo oggetto " +
			"per il tuo borsone!";
	private static final String MESSAGGIO_SCUSE = "Mi spiace, ma non ho piu' nulla...";
	
	private Attrezzo dono;
	
	/** costruttore con parametri
	 * @param nome String
	 * @param presentazione String
	 * @param attrezzo Attrezzo */
	public Mago(String nome, String presentazione, Attrezzo attrezzo) {
		super(nome, presentazione);
		this.dono = attrezzo;
	}
	
	@Override
	public String agisci(Partita partita) {
		String msg;
		if (this.dono!=null) {
			partita.getLabirinto().getStanzaCorrente().addAttrezzo(this.dono);
			this.dono = null;
			msg = MESSAGGIO_DONO;
		}
		else {
			msg = MESSAGGIO_SCUSE;
		}
		return msg;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		String msg;
		msg = ("grazie per questo regalo, sarò ben felice di restituirtelo alleggerito");
		partita.getLabirinto().getAttrezzi().remove(attrezzo);
		Attrezzo nuovo = new Attrezzo (attrezzo.getNome(), attrezzo.getPeso()/2);
		partita.getLabirinto().getStanzaCorrente().addAttrezzo(nuovo);
		partita.getGiocatore().getBorsa().removeAttrezzo(attrezzo.getNome());
		
		return msg;
	}
}