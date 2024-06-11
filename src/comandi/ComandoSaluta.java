package comandi;

import diadia.IO;
import diadia.Partita;
import giocatore.AbstractPersonaggio;

public class ComandoSaluta extends AbstractComando {
	private static final String MESSAGGIO_CON_CHI =
			"Chi dovrei salutare?...";
	private String messaggio;
	
	public ComandoSaluta () { super ("saluta"); }

	@Override
	public void esegui(Partita partita, IO console ) {
		AbstractPersonaggio personaggio;
		personaggio = partita.getLabirinto().getStanzaCorrente().getPersonaggio();
		if (personaggio!=null) {
			this.messaggio = personaggio.saluta();
			console.mostraMessaggio(this.messaggio);
		} else console.mostraMessaggio(MESSAGGIO_CON_CHI);
	}
	
	public String getMessaggio() {
		return this.messaggio;
	}

}
