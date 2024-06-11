package comandi;
import diadiaTest.*;

public interface FabbricaDiComandi {

	/** costruzione comando
	 * @param istruzione
	 * @return comando da eseguire
	 * @throws Exception 
	 * */
	public Comando costruisciComando (String istruzione) throws Exception;

}
