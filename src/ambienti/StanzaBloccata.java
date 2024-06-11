package ambienti;
import attrezz.Attrezzo;
import giocatore.Borsa;

public class StanzaBloccata extends Stanza {
	
	Direzione direzionebloccata;
	String chiave;
	String tipo = "BL0";

	/** costruttore con nome, direzione bloccata e chiave 
	 * @param nome nome della stanza
	 * @param direzionebloccata direzione bloccata
	 * @param chiave nome attrezzo che sblocca la direzione*/
	public StanzaBloccata(String nome,Direzione direzionebloccata,String chiave) {
		
		super(nome);
		this.direzionebloccata=direzionebloccata;
		this.chiave=chiave;
	}

	/** restituisce il tipo della stanza
	 * @return "BL0" se è libera, "BL1" se ha direzioni bloccate
	 * */
	@Override
	public String getTipo () {
		if (this.hasAttrezzo(chiave))
			this.tipo = "BL0";
		else
			this.tipo = "BL1";
		return this.tipo;
	}

	/** restituisce la stanza adiacente
	 * @param direzione
	 * @return la stanza se è possibile, altrimenti restituisce la stessa di partenza
	 * */
	@Override
	public Stanza getStanzaAdiacente(Direzione direzione) {

		if(super.hasAttrezzo(chiave)==false && direzione.toString().equalsIgnoreCase(direzionebloccata.toString())) {			
			return StanzaBloccata.this;
		}
		return super.getStanzaAdiacente(direzione);
	}

	/** metodo che restituisce le caratteristiche della stanza
	 * @return la rappresentazione stringa
	 *  */
	@Override
	public String toString() {
	/*	if(super.hasAttrezzo(chiave)==false)
			return (this.tipo + "\nla direzione " + this.direzionebloccata + 
					" è bloccata, servirebbe una " + this.chiave);
		else
		*/	return super.toString();
	}

	/** metodo che restituisce la descrizione della stanza
	 * @return la descrizione stringa
	 * */
	@Override
	public String getDescrizione() {
		return this.toString();
	}

	/** metodo che aggiunge l'attrezzo alla stanza
	 * (se l'attrezzo è quello "speciale" aggiorna il suo tipo in "BL0")
	 * @param attrezzo l'attrezzo da mettere nella stanza.
	 * @return true se si può aggiungere, false altrimenti
	 * */
	@Override
	public boolean addAttrezzo(Attrezzo attrezzo) {
		boolean aggiunto = false;

		if (super.addAttrezzo(attrezzo)) {
			aggiunto = true;
			if (attrezzo.getNome().equals(this.chiave))
				this.tipo = "BL0";
		}	
		return aggiunto;
	}


	/** metodo che rimuove l'attrezzo dalla stanza 
	 * (se l'attrezzo è quello "speciale" aggiorna il suo tipo in "BL1")
	 * @param attrezzo l'attrezzo da togliere dalla stanza.
	 * @return true se si può rimuovere, false altrimenti
	 */
	@Override
	public boolean removeAttrezzo(Attrezzo attrezzo) {

		boolean rimosso = false;

		if (super.removeAttrezzo(attrezzo)) {
			rimosso = true;
			if (attrezzo.getNome().equals(this.chiave))
				this.tipo = "BL1";
		}
		return rimosso;
	}
	
	@Override
	public String getChiave () {
		return this.chiave;
	}
	
	@Override
	public Direzione getDirBloccata() {
		return this.direzionebloccata;
	}
}
