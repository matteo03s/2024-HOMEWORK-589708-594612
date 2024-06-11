package giocatore;

import attrezz.Attrezzo;
import diadia.Partita;

public class Cane extends AbstractPersonaggio {
	String cibo;
	
	public Cane(String nome, String presentazione) { super(nome, presentazione); }
	
	public Cane(String nome, String presentazione, String cibo) {
		super(nome, presentazione);
		this.cibo = cibo;
	}
	

	private static final String MESSAGGIO_PRESENTAZIONE = "WOOF WOOF";

	
	@Override
	public String agisci(Partita partita) {
		StringBuilder msg = new StringBuilder(MESSAGGIO_PRESENTAZIONE);
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu() - 1);
		msg.append(" sei stato morso! Hai perso un CFU, attento a chi saluti!");
		return msg.toString();
	}


	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		String msg;
		if (attrezzo.getNome().equals(this.cibo)) {
			msg = "al cane è piaciuto il tuo regalo, lo sta mangiando!";
			partita.getGiocatore().getBorsa().removeAttrezzo(attrezzo.getNome());
			partita.getLabirinto().getAttrezzi().remove(attrezzo);
			partita.getLabirinto().getStanzaCorrente().addAttrezzo(new Attrezzo ("osso", 1));
		}
		else {
			msg = " al cane non è piaciuto il tuo regalo, ti ha morso!";
			partita.getGiocatore().setCfu(partita.getGiocatore().getCfu() - 1);
		}
		return msg;
	}

}
