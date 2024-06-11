package ambienti;
import attrezz.Attrezzo;
import diadiaTest.*;
import giocatore.Borsa;

public class StanzaBuia extends Stanza {
	private String parolachiave;
	private String tipo = "BU0";

	/** costruttore con nome e parolachiave 
	 * @param nome nome stanza
	 * @param parolachiave nome delll'oggetto che illumina la stanza*/
	public StanzaBuia(String nome,String parolachiave) {
		super(nome);
		this.parolachiave=parolachiave;
	}
	
	/** restituisce il tipo della stanza
	 * @return "BU0" se stanza illuminata "BU1" altrimenti
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
		if(super.hasAttrezzo(parolachiave)==false)
			return ("Qui c'è un buio pesto");
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
	
	/** metodo che aggiunge l'attrezzo alla stanza
	 * (se l'attrezzo è quello "speciale" aggiorna il suo tipo in "BU0")
	 * @param attrezzo l'attrezzo da mettere nella stanza.
	 * @return true se si può aggiungere, false altrimenti
	 * */
	@Override
	public boolean addAttrezzo(Attrezzo attrezzo) {
		boolean aggiunto = false;
		
		if (super.addAttrezzo(attrezzo)) {
			aggiunto = true;
			if (attrezzo.getNome().equals(this.parolachiave))
				this.tipo = "BU0";
		}	
		return aggiunto;
	}
	
	
	/** metodo che rimuove l'attrezzo dalla stanza 
	 * (se l'attrezzo è quello "speciale" aggiorna il suo tipo in "BU1")
	 * @param attrezzo l'attrezzo da togliere dalla stanza.
	 * @return true se si può rimuovere, false altrimenti
	 */
	@Override
	public boolean removeAttrezzo(Attrezzo attrezzo) {

		boolean rimosso = false;

		if (super.removeAttrezzo(attrezzo)) {
			rimosso = true;
			if (attrezzo.getNome().equals(this.parolachiave))
				this.tipo = "BU1";
		}
		return rimosso;
	}
	
	public String getChiave () {
		return this.parolachiave;
	}
	
}
