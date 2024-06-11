package comandi;

import java.util.Iterator;

import attrezz.Attrezzo;
import diadia.IO;
import diadia.Partita;

public class ComandoRegala extends AbstractComando {

	String parametro;

	public ComandoRegala () { super ("regala"); }
	public ComandoRegala (String parametro) {
		super ("regala");
		this.parametro = parametro;
	}

	@Override
	public void esegui(Partita partita, IO console) {
		Attrezzo dono = partita.getGiocatore().getBorsa().getAttrezzo(this.parametro);
		if (dono == null)
			console.mostraMessaggio("non hai " + this.parametro + " nella borsa, non puoi donarlo");
		else
			console.mostraMessaggio(partita.getLabirinto().getStanzaCorrente().getPersonaggio().riceviRegalo(dono, partita));	
	}

	@Override
	public void setParametro(String parametro) { this.parametro = parametro; }



	@Override
	public String getParametro() { return this.parametro; }

}
