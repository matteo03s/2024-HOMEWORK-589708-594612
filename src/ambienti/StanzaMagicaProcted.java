package ambienti;

import attrezz.Attrezzo;

public class StanzaMagicaProcted extends StanzaProcted{
	
	final static private int SOGLIA_MAGICA_DEFAULT = 3;
	private int contatoreAttrezziPosati;
	private int sogliaMagica;
	public StanzaMagicaProcted(String nome) {
		this(nome, SOGLIA_MAGICA_DEFAULT);
	}
	public StanzaMagicaProcted(String nome, int soglia) {
		super(nome);
		this.contatoreAttrezziPosati = 0;
		this.sogliaMagica = soglia;
	}
	private Attrezzo modificaAttrezzo(Attrezzo attrezzo) {
		StringBuilder nomeInvertito;
		int pesoX2 = attrezzo.getPeso() * 2;
		nomeInvertito = new StringBuilder(attrezzo.getNome());
		nomeInvertito = nomeInvertito.reverse();
		attrezzo = new Attrezzo(nomeInvertito.toString(),
				pesoX2);
		return attrezzo;
	}
	public boolean addAttrezzo(Attrezzo attrezzo) {
		this.contatoreAttrezziPosati++;
		if (this.contatoreAttrezziPosati > this.sogliaMagica)
		attrezzo = this.modificaAttrezzo(attrezzo);
		if (this.numeroAttrezzi<this.attrezzi.length) {
		this.attrezzi[this.numeroAttrezzi] = attrezzo;
		this.numeroAttrezzi++;
		return true;
		}else return false;
		}
	
	public boolean removeAttrezzo(Attrezzo attrezzo) {
	this.contatoreAttrezziPosati--;
	if(this.contatoreAttrezziPosati==this.sogliaMagica)
		System.out.println("la stanza  non e' piu magica ");
		return super.removeAttrezzo(attrezzo);
	}
}
