package ambienti;
import attrezz.Attrezzo;
import giocatore.Borsa;

public class StanzaMagica extends Stanza {
	final static private int SOGLIA_MAGICA_DEFAULT = 3;
	private int contatoreAttrezziPosati;
	private int sogliaMagica;
	private String tipo = "MA";

	/** costruttore con solo nome e con soglia impostata di default
	 * @param nome della stanza
	 * */
	public StanzaMagica(String nome) {
		this(nome, SOGLIA_MAGICA_DEFAULT);
	}

	/** costruttore con nome e soglia 
	 * @param nome della stanza
	 * @param soglia magica
	 * */
	public StanzaMagica(String nome, int soglia) {
		super(nome);
		this.contatoreAttrezziPosati = 0;
		this.sogliaMagica = soglia;
	}

	/** restituisce il tipo della stanza
	 * @return "MA0" se la "magia" non è attiva "MA1" altrimenti
	 * */
	@Override
	public String getTipo () {
		return this.tipo;
	}

	/** metodo che restituisce le caratteristiche della stanza
	 * @return la rappresentazione stringa
	 * */
	@Override
	public String toString() {
		if(super.getTipo().equals("MA1") || super.getTipo().equals("MA2"))
			return  ("\nla stanza è magica") + super.toString();
		else
			return super.toString();
	}

	/** metodo che restituisce la descrizione della stanza
	 * @return la descrizione stringa
	 * */
	@Override
	public String getDescrizione() {	
		return toString();
	}

	/** funzione che modifica l'attrezzo 
	 * @param attrezzo l'attrezzo da modificare
	 * @return attrezzo l'attrezzo modificato
	 * */
	@Override
	public Attrezzo modificaAttrezzo(Attrezzo attrezzo) {
		StringBuilder nomeInvertito;
		int pesoX2 = attrezzo.getPeso() * 2;
		nomeInvertito = new StringBuilder(attrezzo.getNome());
		nomeInvertito = nomeInvertito.reverse();
		attrezzo = new Attrezzo(nomeInvertito.toString(),
				pesoX2);
		return attrezzo;
	}

	/** funzione che vede se si può aggiungere un attrezzo alla stanza
	 * e aggiorna il tipo della stanza se inizia di essere magica
	 * @param attrezzo l'attrezzo da aggiungere
	 * @return true se si può aggiungere, false altrimenti
	 **/
	@Override
	public boolean addAttrezzo(Attrezzo attrezzo) {
		this.contatoreAttrezziPosati++;
		if (this.contatoreAttrezziPosati==this.sogliaMagica+1) {
			this.tipo = "MA1";
			attrezzo = this.modificaAttrezzo(attrezzo);
		}
		if (this.contatoreAttrezziPosati>this.sogliaMagica+1) {
			this.tipo = "MA2";
			attrezzo = this.modificaAttrezzo(attrezzo);
		}
		return super.addAttrezzo(attrezzo);
	}

	/** funzione che vede se si può rimuovere un attrezzo dalla stanza
	 * e aggiorna il tipo della stanza se smette di essere magica
	 * @param attrezzo l'attrezzo da rimuovere
	 * @return true se si può rimuovere, false altrimenti
	 **/
	@Override
	public boolean removeAttrezzo(Attrezzo attrezzo) {
		this.contatoreAttrezziPosati--;
		if(this.contatoreAttrezziPosati==(this.sogliaMagica))
			this.tipo = "MA0";
		if (this.contatoreAttrezziPosati<(this.sogliaMagica))
			this.tipo = "MA";

		return super.removeAttrezzo(attrezzo);
	}

	public boolean isMagica() {
		// TODO Auto-generated method stub
		return true; //((this.tipo.equals("MA1")) || (this.tipo.equals("MA2")));
	}

	public String getChiave () {
		return null;
	}
	@Override
	public int getSoglia() {
		return this.sogliaMagica;
	}

}
