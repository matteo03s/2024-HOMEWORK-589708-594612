package comandi;

import diadia.IO;
import diadia.Partita;

import java.util.Iterator;

import attrezz.Attrezzo;
import giocatore.Borsa;

public class ComandoBorsa implements Comando {
	String nome = "borsa";
	private String ordine;

	/** costruttore senza parametro */
	public ComandoBorsa () {	
	}

	/** costruttore con parametro */
	public ComandoBorsa (String parametro) {	
		this.ordine = parametro;
	}

	@Override
	public void esegui(Partita partita, IO console) {
		StringBuilder s = new StringBuilder();
		if (this.getParametro() == null)
			s.append(partita.getGiocatore().getBorsa().toString());
		else if (this.getParametro().equals("peso")) {
			s.append("[ ");
			for (Attrezzo i : partita.getGiocatore().getBorsa().getContenutoOrdinatoPerPeso())
				s.append(i.getNome() + ", ");
			s.deleteCharAt(s.toString().length()-2);
			s.append("]");
		}
		else if (this.getParametro().equals("nome")) {
			s.append("{ ");
			Iterator <Attrezzo> iter = partita.getGiocatore().getBorsa().getContenutoOrdinatoPerNome().iterator();
			while (iter.hasNext())
				s.append(iter.next().getNome() + ", ");
			s.deleteCharAt(s.toString().length()-2);
			s.append("}");
		}
		else if (this.getParametro().equals("gruppo")) {
			for (Integer i : partita.getGiocatore().getBorsa().getContenutoRaggruppatoPerPeso().keySet()) {
				s.append("(" + i + ", ");
				Iterator <Attrezzo> iter = partita.getGiocatore().getBorsa().getContenutoRaggruppatoPerPeso().get(i).iterator();
				s.append("{ ");
				while (iter.hasNext())
					s.append(iter.next().getNome() + ", ");
				s.deleteCharAt(s.toString().length()-2);
				s.append("}); ");
				s.deleteCharAt(s.toString().length()-2);
			}
		}
		else {
			console.mostraMessaggio("modalità sconosciuta");
			return;
		}

		console.mostraMessaggio(s.toString());
	}

	@Override
	public void setParametro(String parametro) {
		this.ordine = parametro;

	}

	@Override
	public String getNome() {
		return this.nome;
	}

	@Override
	public String getParametro() {
		return this.ordine;
	}


}
