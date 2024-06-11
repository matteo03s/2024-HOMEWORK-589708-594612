package giocatore;

import java.util.Iterator;

import ambienti.Stanza;
import attrezz.Attrezzo;
import diadia.Partita;

public class Strega extends AbstractPersonaggio {

	private static final String MSG_BRAVO = "siccome mi hai salutato, ti manderò nella stanza con più attrezzi, buona fortuna";
	private static final String MSG_MALE = "siccome non mi hai salutato, ti spedirò nella stanza con meno attrezzi, maledetto!";
	public Strega(String nome, String presentazione) { super(nome, presentazione); }

	@Override
	public String agisci(Partita partita) {
		StringBuilder msg = new StringBuilder();
		Iterator <Stanza> iter = partita.getLabirinto().getStanzaCorrente().getMapStanzeAdiacenti().values().iterator();
		Stanza dest = null;
		Stanza tmp = null;
		if (super.haSalutato()) {
			msg.append(MSG_BRAVO);

			while (iter.hasNext()) {
				tmp = iter.next();
				if (dest == null)
					dest = tmp;
				else
					if (tmp.getAttrezzi().size() >= dest.getAttrezzi().size())
						dest = tmp;		
			}
		}
		else {
			msg.append(MSG_MALE);
			while (iter.hasNext()) {
				tmp = iter.next();
				if (dest == null)
					dest = tmp;
				else
					if (tmp.getAttrezzi().size() < dest.getAttrezzi().size())
						dest = tmp;		
			}
		}
		partita.getLabirinto().setStanzaCorrente(dest);		


		return msg.toString();
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		String msg;
		msg = ("grazie per questo regalo, sciocco viaggiatore, non lo riavrai più ahah");
		partita.getLabirinto().getAttrezzi().remove(attrezzo);
		partita.getGiocatore().getBorsa().removeAttrezzo(attrezzo.getNome());
		return msg;
	}

}
