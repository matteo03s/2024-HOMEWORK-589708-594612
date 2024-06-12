package comandi;
import diadiaTest.*;

public interface FabbricaDiComandi {

	/** costruzione comando
	 * @param istruzione
	 * @return comando da eseguire
	 * */
	public Comando costruisciComando (String istruzione);

}
