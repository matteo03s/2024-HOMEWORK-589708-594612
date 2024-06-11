package comandi;

import diadia.IO;
import diadia.Partita;
import giocatore.AbstractPersonaggio;

public class ComandoInteragisci extends AbstractComando {
	private static final String MESSAGGIO_CON_CHI =
			"Con chi dovrei interagire?...";
	private String messaggio;
	
	public ComandoInteragisci () { super ("interagisci"); }

	@Override
	public void esegui(Partita partita, IO console ) {
		AbstractPersonaggio personaggio;
		personaggio = partita.getLabirinto().getStanzaCorrente().getPersonaggio();
		if (personaggio!=null) {
			this.messaggio = personaggio.agisci(partita);
			console.mostraMessaggio(this.messaggio);
		} else console.mostraMessaggio(MESSAGGIO_CON_CHI);
	}
	public String getMessaggio() {
		return this.messaggio;
	}

}
